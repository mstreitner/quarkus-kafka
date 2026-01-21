package at.cloudflight.kafkaexample.avro;

import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonDecoder;
import org.apache.avro.io.JsonEncoder;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Convenience helper so we can use generated Avro types without requiring an Avro serializer/schema registry
 * in the local setup.
 */
public final class AvroJson {

    private AvroJson() {
    }

    public static String toJson(SpecificRecord avroRecord) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JsonEncoder encoder = EncoderFactory.get().jsonEncoder(avroRecord.getSchema(), out);
            new SpecificDatumWriter<>(avroRecord.getSchema()).write(avroRecord, encoder);
            encoder.flush();
            return out.toString(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to serialize Avro record to JSON", e);
        }
    }

    public static <T extends SpecificRecord> T fromJson(String json, Class<T> recordClass) {
        try {
            T instance = recordClass.getDeclaredConstructor().newInstance();
            JsonDecoder decoder = DecoderFactory.get().jsonDecoder(instance.getSchema(), json);

            SpecificDatumReader<T> reader = new SpecificDatumReader<>(instance.getSchema());
            return reader.read(null, decoder);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to deserialize Avro record from JSON", e);
        }
    }
}
