package at.cloudflight.kafkaexample.messaging;

import at.cloudflight.kafkaexample.avro.AvroJson;
import at.cloudflight.kafkaexample.avro.OutputEvent;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
@RequiredArgsConstructor
public class OutputProducer {

    @Channel("example-output")
    Emitter<String> emitter;

    public void publish(OutputEvent event) {
        emitter.send(AvroJson.toJson(event));
    }
}
