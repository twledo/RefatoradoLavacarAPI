package dev.ApiLavacar.Nego.service;

import dev.ApiLavacar.Nego.model.JobWash;
import dev.ApiLavacar.Nego.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<JobWash> listAll() {return jobRepository.findAll();}

    public Optional<JobWash> findById(Long id) {
        return jobRepository.findById(id);
    }

    public JobWash save(JobWash jobWash) {
        return jobRepository.save(jobWash);
    }

    public void delete(Long id) {
        jobRepository.deleteById(id);
    }
}
