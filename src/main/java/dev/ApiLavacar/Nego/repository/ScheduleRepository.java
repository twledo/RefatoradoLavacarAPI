package dev.ApiLavacar.Nego.repository;

import dev.ApiLavacar.Nego.model.Schedule;
import dev.ApiLavacar.Nego.model.JobWash;
import dev.ApiLavacar.Nego.model.Hour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Conta quantos agendamentos existem para um JobWash em um determinado Hour
    int countByJobWashAndHour(JobWash jobWash, Hour hour);

    // Buscar agendamentos por JobWash e Hour
    List<Schedule> findByJobWashAndHour(JobWash jobWash, Hour hour);
}
