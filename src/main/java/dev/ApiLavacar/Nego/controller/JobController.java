package dev.ApiLavacar.Nego.controller;

import dev.ApiLavacar.Nego.model.JobWash;
import dev.ApiLavacar.Nego.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owner/jobWash")
public class JobController {

    @Autowired
    private JobService  jobService;

    @PostMapping
    public ResponseEntity<JobWash> create(@RequestBody JobWash jobWash) {
        JobWash newJob = jobService.save(jobWash);
        return ResponseEntity.ok(newJob);
    }

    @GetMapping
    public ResponseEntity<List<JobWash>> listAll() {
        List<JobWash> jobs = jobService.listAll();
        return ResponseEntity.ok(jobs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobWash> update(@PathVariable Long id, @RequestBody JobWash data) {
        Optional<JobWash> existing = jobService.findById(id);

        if (existing.isPresent()) {
            JobWash job = existing.get();
            job.setName(data.getName());
            job.setPrice(data.getPrice());
            job.setDescription(data.getDescription());

            JobWash updated = jobService.save(job);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<JobWash> existing = jobService.findById(id);

        if (existing.isPresent()) {
            jobService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
