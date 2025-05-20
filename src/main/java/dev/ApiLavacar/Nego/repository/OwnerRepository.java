package dev.ApiLavacar.Nego.repository;

import dev.ApiLavacar.Nego.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByUsernameAndPassword(String username, String password);

    Optional<Owner> findByUsername(String username);
}
