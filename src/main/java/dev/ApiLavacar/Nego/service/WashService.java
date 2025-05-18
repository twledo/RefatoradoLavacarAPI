package dev.ApiLavacar.Nego.service;

import dev.ApiLavacar.Nego.model.Wash;
import dev.ApiLavacar.Nego.repository.WashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WashService {

    @Autowired
    private WashRepository washRepository;

    public List<Wash> getAllWashes() {return washRepository.findAll();}

    public Wash scheduleWash(Wash wash) {return washRepository.save(wash);}

    public void delete(Long id) {washRepository.deleteById(id);}
}
