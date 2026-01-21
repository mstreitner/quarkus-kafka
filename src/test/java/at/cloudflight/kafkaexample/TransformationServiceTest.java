package at.cloudflight.kafkaexample;

import at.cloudflight.kafkaexample.service.TransformationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransformationServiceTest {

    @Test
    void transform_appendsSuffix() {
        TransformationService service = new TransformationService();
        assertEquals("hello-transformed", service.transform("hello"));
        assertEquals("BONJOUR-transformed", service.transform("BONJOUR"));
    }
}
