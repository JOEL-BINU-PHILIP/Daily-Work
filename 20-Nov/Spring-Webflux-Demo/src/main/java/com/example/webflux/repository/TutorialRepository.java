package com.example.webflux.repository;

import com.example.webflux.model.Tutorial;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface TutorialRepository extends R2dbcRepository<Tutorial, Integer> {

    Flux<Tutorial> findByTitleContaining(String title);

    Flux<Tutorial> findByPublished(boolean published);
}
