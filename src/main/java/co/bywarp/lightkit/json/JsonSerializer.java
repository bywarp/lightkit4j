package co.bywarp.lightkit.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonSerializer {

    /**
     *
     * This will take an Object, read all of the JsonKey annotations
     * and convert it to a JSONObject.
     *
     * @param object the object to be converted
     * @param <T> the generic type of the object
     * @return a JSONObject
     */
    public <T extends Object> JSONObject toJSON(T object) {
        Class<?> clazz = object.getClass();
        JSONObject jsonObject = new JSONObject();
        List<Field> fields = getAnnotatedFields(clazz);

        fields.forEach(field -> {
            JsonKey key = field.getAnnotation(JsonKey.class);
            String name = key.name();
            field.setAccessible(true);
            try {
                Object obj = field.get(object);
                if (!(key.type() == Void.class)) {
                    if (obj instanceof Iterable) {
                        JSONArray jsonArray = new JSONArray();
                        Iterable<?> iterable = (Iterable<?>)obj;
                        boolean isPrimitive = isPrimitive(key.type());
                        iterable.forEach(iter -> {
                            if (isPrimitive) {
                                jsonArray.put(iter);
                                return;
                            }
                            JSONObject iterObject = toJSON(iter);
                            jsonArray.put(iterObject);
                        });
                        jsonObject.put(name, jsonArray);
                        return;
                    }
                    jsonObject.put(name, toJSON(obj));
                    return;
                }
                jsonObject.put(name, obj);
            } catch (IllegalAccessException e) { e.printStackTrace(); }
            field.setAccessible(false);
        });

        return jsonObject;
    }

    /**
     *
     * This will use a JSONObject to fill a previously serialized Object.
     *
     * @param json the JSONObject
     * @param object the object to be filled
     * @param <T> the generic type of the object
     * @param <Type> the generic type of an array
     * @return an Object
     */
    public <T, Type> T fromJSON(JSONObject json, T object) {
        List<Field> fields = getAnnotatedFields(object.getClass());
        for (Field field : fields) {
            JsonKey key = field.getAnnotation(JsonKey.class);
            Object current = json.get(key.name());
            field.setAccessible(true);
            try {
                if (!(key.type() == Void.class)) {
                    if (current instanceof JSONArray) {
                        JSONArray array = (JSONArray)current;
                        List<Type> list = new ArrayList<>();
                        array.forEach(item -> {
                            if (!(item instanceof JSONObject)) {
                                list.add((Type)item);
                                return;
                            }
                            JSONObject jsonObject = (JSONObject)item;
                            try {
                                list.add((Type)fromJSON(jsonObject, key.type().newInstance()));
                            } catch (IllegalAccessException | InstantiationException e) { e.printStackTrace(); }
                        });
                        field.set(object, list);
                        continue;
                    }
                    Object extract = field.getType().newInstance();
                    field.set(object, fromJSON((JSONObject)current, extract));
                    continue;
                }
                field.set(object, current);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }

        return object;
    }

    private List<Field> getAnnotatedFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(JsonKey.class))
                .collect(Collectors.toList());
    }

    public boolean isPrimitive(Class<?> clazz) {
        String name = clazz.getName();
        return name.contains("String")
                || name.contains("Integer")
                || name.contains("Boolean")
                || name.contains("Byte")
                || name.contains("Long")
                || name.contains("Short")
                || name.contains("Character")
                || name.contains("Float")
                || name.contains("Double")
                || name.contains("Void"); //this one is weird
    }

}
