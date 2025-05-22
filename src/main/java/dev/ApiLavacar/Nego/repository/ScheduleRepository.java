package dev.ApiLavacar.Nego.repository;

import dev.ApiLavacar.Nego.model.Schedule;
import dev.ApiLavacar.Nego.model.JobWash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Conta quantos agendamentos existem para um JobWash em um determinado horário
    int countByJobWashAndDateTime(JobWash jobWash, LocalDateTime dateTime);

    // Buscar agendamentos por JobWash e horário
    List<Schedule> findByJobWashAndDateTime(JobWash jobWash, LocalDateTime dateTime);
}
