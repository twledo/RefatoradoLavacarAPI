package dev.ApiLavacar.Nego.service;

import dev.ApiLavacar.Nego.model.Owner;
import dev.ApiLavacar.Nego.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Serviço responsável pela lógica de negócio relacionada ao Owner (Dono do Lavacar).
 */
@Service
public class OwnerService {

    private static final Logger logger = LoggerFactory.getLogger(OwnerService.class);

    @Autowired
    private OwnerRepository ownerRepository;

    /**
     * Autentica um Dono do Lavacar pelo username e password.
     *
     * @param username o nome de usuário do dono
     * @param password a senha do dono
     * @return um Optional contendo o Dono autenticado, ou vazio caso não encontrado ou erro ocorra
     */
    public Optional<Owner> authenticateOwner(String username, String password) {
        try {
            return ownerRepository.findByUsernameAndPassword(username, password);
        } catch (Exception e) {
            logger.error("Erro ao autenticar owner com username '{}': {}", username, e.getMessage());
            return Optional.empty();
        }
    }
}
