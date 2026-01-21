package at.cloudflight.kafkaexample.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
@RequiredArgsConstructor
public class OutputProducer {

    @Channel("example-output")
    Emitter<String> emitter;

    public void publish(String value) {
        emitter.send(value);
    }
}
