package at.cloudflight.kafkaexample.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransformationService {

    public String transform(String input) {
        return input + "-transformed";
    }
}

