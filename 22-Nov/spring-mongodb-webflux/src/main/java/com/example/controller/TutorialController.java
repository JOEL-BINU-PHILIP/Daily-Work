package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.model.Tutorial;
import com.example.service.TutorialService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tutorials")
@CrossOrigin(origins = "*")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @GetMapping
    public Flux<Tutorial> getAll(@RequestParam(required = false) String title) {
        if (title == null) return tutorialService.findAll();
        return tutorialService.findByTitle(title);
    }

    @GetMapping("/{id}")
    public Mono<Tutorial> getById(@PathVariable String id) {
        return tutorialService.findById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Tutorial> create(@RequestBody Tutorial tutorial) {
        tutorial.setPublished(false);
        return tutorialService.save(tutorial);
    }

    @PutMapping("/{id}")
    public Mono<Tutorial> update(@PathVariable String id, @RequestBody Tutorial tutorial) {
        return tutorialService.update(id, tutorial);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return tutorialService.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAll() {
        return tutorialService.deleteAll();
    }

    @GetMapping("/published")
    public Flux<Tutorial> getPublished() {
        return tutorialService.findPublished();
    }
}
