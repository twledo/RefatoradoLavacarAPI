package dev.ApiLavacar.Nego.controller;

import dev.ApiLavacar.Nego.dto.HourDTO;
import dev.ApiLavacar.Nego.dto.ScheduleDTO;
import dev.ApiLavacar.Nego.model.JobWash;
import dev.ApiLavacar.Nego.service.HourService;
import dev.ApiLavacar.Nego.service.JobService;
import dev.ApiLavacar.Nego.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller público para acesso a horários, serviços e agendamentos.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/public")
public class PublicServiceController {

    @Autowired
    private HourService hourService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Lista todos os serviços de lavagem disponíveis.
     * @return lista de serviços
     */
    @GetMapping("/jobs")
    public ResponseEntity<List<JobWash>> listAll() {
        List<JobWash> jobs = jobService.listAll();
        return ResponseEntity.ok(jobs);
    }

    /**
     * Adiciona um novo agendamento de lavagem.
     * @param scheduleDTO dados do agendamento
     * @return agendamento criado ou mensagem de erro
     */
    @PostMapping("/addSchedule")
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        try {
            var savedSchedule = scheduleService.addScheduleWash(scheduleDTO);
            return ResponseEntity.ok(savedSchedule);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    /**
     * Lista todos os horários disponíveis.
     * @return lista de horários
     */
    @GetMapping("/getHours")
    public List<HourDTO> getAllHours() {
        return hourService.getAllHours();
    }
}