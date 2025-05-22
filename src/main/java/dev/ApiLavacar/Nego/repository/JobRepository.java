package dev.ApiLavacar.Nego.repository;

import dev.ApiLavacar.Nego.model.JobWash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobWash, Long> {
}
