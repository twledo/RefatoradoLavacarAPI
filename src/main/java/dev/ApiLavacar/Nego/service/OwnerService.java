package dev.ApiLavacar.Nego.service;

import dev.ApiLavacar.Nego.model.Owner;
import dev.ApiLavacar.Nego.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public Optional<Owner> authenticateOwner(String username, String password) {
        return ownerRepository.findByUsernameAndPassword(username, password);
    }
}
