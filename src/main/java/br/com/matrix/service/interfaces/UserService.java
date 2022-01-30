package br.com.matrix.service.interfaces;

import br.com.matrix.exception.ApiException;
import br.com.matrix.resource.dto.UserDto;
import br.com.matrix.resource.dto.UserUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Page<UserDto> findAll(Pageable pageable);
    Optional<UserDto> findById(Long id);
    void create(UserDto entity);
    void update(UserUpdateDto userDto) throws ApiException;
    void delete(Long id);
}
