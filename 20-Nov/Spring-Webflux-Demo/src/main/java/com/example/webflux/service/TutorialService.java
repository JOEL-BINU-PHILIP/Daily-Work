package com.example.webflux.service;

import com.example.webflux.model.Tutorial;
import com.example.webflux.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository repository;

    public Flux<Tutorial> findAll() {
        return repository.findAll();
    }

    public Mono<Tutorial> findById(int id) {
        return repository.findById(id);
    }

    public Mono<Tutorial> save(Tutorial tutorial) {
        return repository.save(tutorial);
    }

    public Mono<Tutorial> update(int id, Tutorial t) {
        return repository.findById(id)
                .flatMap(existing -> {
                    t.setId(id);
                    return repository.save(t);
                });
    }

    public Mono<Void> deleteById(int id) {
        return repository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }

    public Flux<Tutorial> findByPublished(boolean published) {
        return repository.findByPublished(published);
    }

    public Flux<Tutorial> findByTitleContaining(String title) {
        return repository.findByTitleContaining(title);
    }
}
