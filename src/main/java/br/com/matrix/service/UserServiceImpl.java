package br.com.matrix.service;

import br.com.matrix.configuration.PropertiesConfig;
import br.com.matrix.exception.ApiException;
import br.com.matrix.exception.BusinessException;
import br.com.matrix.exception.InternalServerErrorException;
import br.com.matrix.model.Gender;
import br.com.matrix.model.User;
import br.com.matrix.repository.UserRepository;
import br.com.matrix.resource.dto.UserDto;
import br.com.matrix.resource.dto.UserUpdateDto;
import br.com.matrix.service.interfaces.GenderService;
import br.com.matrix.service.interfaces.UserService;
import br.com.matrix.validation.functional.ValidationResult;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.matrix.validation.UserValidation.*;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PropertiesConfig propertiesConfig;
    private final GenderService genderService;
    private UserRepository userRepository;
    private ModelMapper modelMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, GenderService genderService, ModelMapper modelMapper, PropertiesConfig propertiesConfig) {
        this.userRepository = userRepository;
        this.genderService = genderService;
        this.modelMapper = modelMapper;
        this.propertiesConfig = propertiesConfig;
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(user -> modelMapper.map(user, UserDto.class));

    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return Optional.ofNullable(modelMapper.map(this.userRepository.findById(id).orElseThrow(() -> getUserByIdNotFoundException(id)), UserDto.class));
    }

    @Override
    @Transactional
    public void create(UserDto userDto) {
        User userEntity = modelMapper.map(userDto, User.class);
        userEntity.setGender(modelMapper.map(this.genderService.findByNameIgnoreCase(userDto.getGender().getName()).get(), Gender.class));
        userEntity.setCreatedAt(LocalDateTime.now());
        this.userRepository.save(userEntity);
    }

    @Override
    public void update(UserUpdateDto userUpdateDto) throws ApiException {
        try {
            User userFound = modelMapper.map(this.findById(userUpdateDto.getId()).get(), User.class);
            Optional.ofNullable(userUpdateDto.getGender())
                    .ifPresent(gender -> {
                        this.genderService.findByNameIgnoreCase(gender.getName())
                                .map(genderDto -> modelMapper.map(genderDto, Gender.class))
                                .ifPresent(userFound::setGender);
                    });
            Optional.ofNullable(userUpdateDto.getEmail())
                    .ifPresent(userFound::setEmail);

            Optional.ofNullable(userUpdateDto.getAge())
                    .ifPresent(userFound::setAge);

            Optional.ofNullable(userUpdateDto.getName())
                    .ifPresent(userFound::setName);

            userFound.setUpdatedAt(LocalDateTime.now());

            validateUserBeforeUpdate(userFound);

            this.userRepository.save(userFound);
        } catch (TransactionSystemException transactionSystemException) {
            logger.error(transactionSystemException.getMessage(), transactionSystemException);
            throw new InternalServerErrorException();
        }
    }

    /**
     * @throws BusinessException org.springframework.http.HttpStatus.BAD_REQUEST
     */
    private void validateUserBeforeUpdate(User userFound) throws BusinessException {
        List<String> errors = emailContainsAtSign()
                .and(nameIsNotEmpty())
                .and(emailNotNullOrEmpty())
                .and(emailContainsAtSign())
                .and(ageNotNullMinAndMax(propertiesConfig))
                .apply(userFound)
                .stream()
                    .filter(validationResult -> !validationResult.isValid())
                    .map(ValidationResult::getReason)
                    .map((Optional::get))
                    .collect(Collectors.toList());

        if (errors != null && !errors.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, errors.toString());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            this.userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.info(e.getMessage());
        }
    }

    private EntityNotFoundException getUserByIdNotFoundException(Long id) {
        return new EntityNotFoundException("User Not Found With ID ".concat(String.valueOf(id)));
    }
}
