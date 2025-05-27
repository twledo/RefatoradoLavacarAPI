package dev.ApiLavacar.Nego.service;

import dev.ApiLavacar.Nego.model.Wash;
import dev.ApiLavacar.Nego.repository.ScheduleRepository;
import dev.ApiLavacar.Nego.repository.WashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@Service
public class WashService {

    private static final Logger logger = LoggerFactory.getLogger(WashService.class);

    @Autowired
    private WashRepository washRepository;

    public List<Wash> getAllWashes() {
        try {
            return washRepository.findAll();
        } catch (Exception e) {
            logger.error("Erro ao buscar todas as lavagens: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public Wash scheduleWash(Wash wash) {
        try {
            return washRepository.save(wash);
        } catch (Exception e) {
            logger.error("Erro ao agendar lavagem: {}", e.getMessage());
            return null;
        }
    }

    public void delete(Long id) {
        try {
            washRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erro ao deletar lavagem com id {}: {}", id, e.getMessage());
        }
    }

    public void deleteAllWashes() {
        try {
            washRepository.deleteAll();
        } catch (Exception e) {
            logger.error("Erro ao deletar todas as lavagens: {}", e.getMessage());
        }
    }
}
