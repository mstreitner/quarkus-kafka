package at.cloudflight.kafkaexample.messaging;

import at.cloudflight.kafkaexample.avro.AvroJson;
import at.cloudflight.kafkaexample.avro.InputEvent;
import at.cloudflight.kafkaexample.service.TransformationService;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
@RequiredArgsConstructor
public class InputConsumer {

    private final TransformationService transformer;
    private final OutputProducer outputProducer;

    @Incoming("example-input")
    public void onMessage(String payload) {
        var input = AvroJson.fromJson(payload, InputEvent.class);
        Log.info("Received InputEvent id=" + input.getId() + " value=" + input.getValue());

        outputProducer.publish(transformer.transform(input));
    }
}
