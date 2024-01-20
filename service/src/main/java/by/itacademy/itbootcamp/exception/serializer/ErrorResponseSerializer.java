package by.itacademy.itbootcamp.exception.serializer;

import by.itacademy.itbootcamp.errors.ErrorResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.io.IOException;

public class ErrorResponseSerializer extends JsonObjectSerializer<ErrorResponse> {
    @Override
    protected void serializeObject(ErrorResponse value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStringField("logref",value.getLogref().name().toLowerCase());
        jgen.writeStringField("message", value.getMessage());
    }
}
