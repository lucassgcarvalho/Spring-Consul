package br.com.matrix.repository;

import br.com.matrix.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
    Optional<Gender> getByNameIgnoreCase(String name);
    void deleteByName(String name);
}
