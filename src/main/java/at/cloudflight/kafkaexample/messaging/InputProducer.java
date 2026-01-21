package at.cloudflight.kafkaexample.messaging;

import at.cloudflight.kafkaexample.avro.AvroJson;
import at.cloudflight.kafkaexample.avro.InputEvent;
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

    public void publish(InputEvent event) {
        emitter.send(new ProducerRecord<>("example-input-v1", event.getId(), AvroJson.toJson(event)));
    }

    public void publish(String key, String value) {
        var event = InputEvent.newBuilder()
                .setId(key)
                .setValue(value)
                .setCreatedAtEpochMillis(System.currentTimeMillis())
                .build();
        publish(event);
    }
}
