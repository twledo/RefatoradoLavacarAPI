package dev.ApiLavacar.Nego.model;

import dev.ApiLavacar.Nego.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class OwnerDetailsService implements UserDetailsService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco pelo username retornando um Optional<Owner>
        // Caso não encontre, lança exceção informando que usuário não foi encontrado
        Owner owner = ownerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Cria um objeto User (implementação do UserDetails) com o username e senha do owner
        // Collections.emptyList() indica que o usuário não tem permissões ou roles configurados
        return new User(owner.getUsername(), owner.getPassword(), Collections.emptyList());
    }
}
