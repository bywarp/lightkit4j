package co.bywarp.lightkit.test.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import co.bywarp.lightkit.json.JsonSerializer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ReflectionJsonSerializerTest {

    @Test
    public void serialize() {
        JsonSerializer serializer = new JsonSerializer();
        ReflectionJsonObject reflectionJsonObject = new ReflectionJsonObject(10,
                true,
                new ReflectionJsonObject.ReflectionJsonObject2(),
                new ArrayList<ReflectionJsonObject.ReflectionJsonObject2>() {{
                    add(new ReflectionJsonObject.ReflectionJsonObject2());
                    add(new ReflectionJsonObject.ReflectionJsonObject2());
                }},
                new ArrayList<String>() {{
                    add("test");
                    add("test2");
                }});
        JSONObject output = serializer.toJSON(reflectionJsonObject);

        JSONObject embed = new JSONObject()
                .put("name", "Bob")
                .put("age", 37);
        JSONObject actual = new JSONObject()
                .put("number", 10)
                .put("enabled", true)
                .put("strings", new JSONArray()
                    .put("test")
                    .put("test2"))
                .put("list", new JSONArray()
                    .put(embed)
                    .put(embed))
                .put("embed", embed);

        assertEquals(actual.toString(), output.toString(), "The serialized object doesn't equal the desired output.");
    }


    @Test
    public void deserialize() {
        JsonSerializer serializer = new JsonSerializer();
        ReflectionJsonObject actual = new ReflectionJsonObject(10,
                true,
                new ReflectionJsonObject.ReflectionJsonObject2(),
                new ArrayList<ReflectionJsonObject.ReflectionJsonObject2>() {{
                    add(new ReflectionJsonObject.ReflectionJsonObject2());
                    add(new ReflectionJsonObject.ReflectionJsonObject2());
                }},
                new ArrayList<String>() {{
                    add("test");
                    add("test2");
                }});

        JSONObject json = serializer.toJSON(actual);
        ReflectionJsonObject output = serializer.fromJSON(json, new ReflectionJsonObject());

        assertEquals(actual.toString(), output.toString(), "The deserialized object doesn't equal the desired output.");
    }

}
