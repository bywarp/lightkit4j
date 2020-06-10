package co.bywarp.lightkit.test.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import co.bywarp.lightkit.json.JsonSerializer;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class ReflectionJsonSerializerTest {

    @Test
    public void serialize() {
        JsonSerializer serializer = new JsonSerializer();
        JSONObject output = serializer.toJSON(new ReflectionJsonObject());
        JSONObject actual = new JSONObject()
                .put("number", 10)
                .put("enabled", true)
                .put("embed", new JSONObject()
                    .put("name", "Bob")
                    .put("age", 37));

        assertEquals(output.toString(), actual.toString(), "The serialized object doesn't equal the desired output.");
    }


    @Test
    public void deserialize() {
        JsonSerializer serializer = new JsonSerializer();
        ReflectionJsonObject actual = new ReflectionJsonObject();

        JSONObject json = serializer.toJSON(actual);
        ReflectionJsonObject output = serializer.fromJSON(json, ReflectionJsonObject.class);

        assertEquals(output.toString(), actual.toString(), "The deserialized object doesn't equal the desired output.");
    }

}
