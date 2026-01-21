package at.cloudflight.kafkaexample.api;

import at.cloudflight.kafkaexample.messaging.InputProducer;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/publish")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class PublishResource {

    private final InputProducer inputProducer;

    @GET
    public Response publish(@QueryParam("value") String value) {
        if (value == null || value.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new PublishResponse(null, "Query param 'value' must not be blank"))
                    .build();
        }

        var key = java.util.UUID.randomUUID().toString();
        inputProducer.publish(key, value);
        return Response.ok(new PublishResponse(key, value)).build();
    }

    public record PublishResponse(String key, String value) {
    }
}
