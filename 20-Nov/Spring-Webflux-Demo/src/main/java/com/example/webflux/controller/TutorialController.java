package com.example.webflux.controller;

import com.example.webflux.model.Tutorial;
import com.example.webflux.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

    @Autowired
    private TutorialService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tutorial> getAll(@RequestParam(required = false) String title) {
        return (title == null) ? service.findAll() : service.findByTitleContaining(title);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Tutorial> getById(@PathVariable int id) {
        return service.findById(id);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Mono<Tutorial> create(@RequestBody Tutorial t) {
//        return service.save(new Tutorial(t.getTitle(), t.getDescription(), false));
//    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Tutorial> update(@PathVariable int id, @RequestBody Tutorial t) {
        return service.update(id, t);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable int id) {
        return service.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAll() {
        return service.deleteAll();
    }

    @GetMapping("/published")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tutorial> getPublished() {
        return service.findByPublished(true);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Tutorial> create(@RequestBody Tutorial t) {
        System.out.println("Received => " + t);
        return service.save(new Tutorial(t.getTitle(), t.getDescription(), false));
    }
}
