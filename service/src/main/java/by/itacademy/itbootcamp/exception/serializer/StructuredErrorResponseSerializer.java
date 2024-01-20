package by.itacademy.itbootcamp.exception.serializer;

import by.itacademy.itbootcamp.errors.StructuredErrorResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.io.IOException;

public class StructuredErrorResponseSerializer extends JsonObjectSerializer<StructuredErrorResponse> {
    @Override
    protected void serializeObject(StructuredErrorResponse value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStringField("logref", value.getLogref().name().toLowerCase());
        jgen.writeArrayFieldStart("errors");
        value.getErrors().entrySet().stream().forEach(error ->{
            try {
                jgen.writeStartObject();
                jgen.writeStringField("field", error.getKey());
                jgen.writeStringField("message", error.getValue());
                jgen.writeEndObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        jgen.writeEndArray();
    }
}
