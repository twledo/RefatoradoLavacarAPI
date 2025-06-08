package dev.ApiLavacar.Nego.repository;

import dev.ApiLavacar.Nego.dto.HourDTO;
import dev.ApiLavacar.Nego.model.Hour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoursRepository extends JpaRepository<Hour, Long> {
}
