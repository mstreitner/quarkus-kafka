package at.cloudflight.kafkaexample.service;

import at.cloudflight.kafkaexample.avro.InputEvent;
import at.cloudflight.kafkaexample.avro.OutputEvent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransformationService {

    public OutputEvent transform(InputEvent input) {
        String transformed = input.getValue() + "-transformed";

        return OutputEvent.newBuilder()
                .setId(input.getId())
                .setOriginalValue(input.getValue())
                .setTransformedValue(transformed)
                .setProcessedAtEpochMillis(System.currentTimeMillis())
                .build();
    }
}
