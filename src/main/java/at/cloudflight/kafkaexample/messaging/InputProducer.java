package at.cloudflight.kafkaexample.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
@RequiredArgsConstructor
public class InputProducer {

    @Channel("example-input-producer")
    Emitter<ProducerRecord<String, String>> emitter;

    public void publish(String key, String value) {
        emitter.send(new ProducerRecord<>("example-input-v1", key, value));
    }
}
