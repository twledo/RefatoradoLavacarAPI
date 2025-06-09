package dev.ApiLavacar.Nego.service;

import dev.ApiLavacar.Nego.model.JobWash;
import dev.ApiLavacar.Nego.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pelas operações relacionadas aos trabalhos de lavagem (JobWash).
 */
@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    /**
     * Retorna a lista de todos os trabalhos de lavagem cadastrados.
     *
     * @return uma lista com todos os JobWash existentes
     */
    public List<JobWash> listAll() {
        return jobRepository.findAll();
    }

    /**
     * Busca um trabalho de lavagem pelo seu ID.
     *
     * @param id o identificador do JobWash
     * @return um Optional contendo o JobWash caso encontrado, ou vazio se não existir
     */
    public Optional<JobWash> findById(Long id) {
        return jobRepository.findById(id);
    }

    /**
     * Salva ou atualiza um trabalho de lavagem no repositório.
     *
     * @param jobWash o objeto JobWash a ser salvo ou atualizado
     * @return o objeto JobWash persistido
     */
    public JobWash save(JobWash jobWash) {
        return jobRepository.save(jobWash);
    }

    /**
     * Remove um trabalho de lavagem pelo seu ID.
     *
     * @param id o identificador do JobWash a ser removido
     */
    public void delete(Long id) {
        jobRepository.deleteById(id);
    }
}
