package br.com.matrix.service.interfaces;

import br.com.matrix.resource.dto.GenderDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GenderService {
    List<GenderDto> findAll(Pageable pageable);
    Optional<GenderDto> findByNameIgnoreCase(String name);
    void create(GenderDto entity);
    void update(String gender, GenderDto entity);
    void delete(String name);
}
