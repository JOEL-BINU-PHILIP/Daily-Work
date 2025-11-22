package com.example.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.example.model.Tutorial;
import reactor.core.publisher.Flux;

public interface TutorialRepository extends ReactiveMongoRepository<Tutorial, String> {

    Flux<Tutorial> findByPublished(Boolean published);

    Flux<Tutorial> findByTitleContaining(String title);
}
