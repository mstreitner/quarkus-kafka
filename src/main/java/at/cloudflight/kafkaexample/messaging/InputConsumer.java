package at.cloudflight.kafkaexample.messaging;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import at.cloudflight.kafkaexample.service.TransformationService;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
@RequiredArgsConstructor
public class InputConsumer {

    private final TransformationService transformer;
    private final OutputProducer outputProducer;

    @Incoming("example-input")
    public void onMessage(String value) {
        Log.info("Received message: " + value);
        String transformed = transformer.transform(value);
        outputProducer.publish(transformed);
    }
}
