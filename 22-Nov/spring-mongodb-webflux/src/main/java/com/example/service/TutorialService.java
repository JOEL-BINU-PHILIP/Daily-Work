package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Tutorial;
import com.example.repository.TutorialRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    public Flux<Tutorial> findAll() {
        return tutorialRepository.findAll();
    }

    public Mono<Tutorial> findById(String id) {
        return tutorialRepository.findById(id);
    }

    public Flux<Tutorial> findByTitle(String title) {
        return tutorialRepository.findByTitleContaining(title);
    }

    public Flux<Tutorial> findPublished() {
        return tutorialRepository.findByPublished(true);
    }

    public Mono<Tutorial> save(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    public Mono<Tutorial> update(String id, Tutorial tutorial) {
        return tutorialRepository.findById(id)
                .flatMap(existing -> {
                    tutorial.setId(id);
                    return tutorialRepository.save(tutorial);
                });
    }

    public Mono<Void> deleteById(String id) {
        return tutorialRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return tutorialRepository.deleteAll();
    }
}
