/*
 * Copyright (c) 2020 Warp Studios
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package co.bywarp.lightkit.test.json;

import co.bywarp.lightkit.json.JsonSerializer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
