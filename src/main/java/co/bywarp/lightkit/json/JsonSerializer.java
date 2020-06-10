package co.bywarp.lightkit.json;

import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonSerializer {

    public <T extends Object> JSONObject toJSON(T object) {
        Class<?> clazz = object.getClass();
        JSONObject jsonObject = new JSONObject();
        List<Field> fields = getAnnotatedFields(clazz);

        fields.forEach(field -> {
            JsonKey key = field.getAnnotation(JsonKey.class);
            field.setAccessible(true);
            try {
                Object obj = field.get(object);
                if (key.embed()) {
                    jsonObject.put(key.name(), toJSON(obj));
                    return;
                }
                jsonObject.put(key.name(), obj);
            } catch (IllegalAccessException e) { e.printStackTrace(); }
            field.setAccessible(false);
        });

        return jsonObject;
    }

    public <T> T fromJSON(JSONObject json, Class<T> object) {
        Class<?> clazz = object.getClass();
        List<Field> fields = getAnnotatedFields(clazz);
        T instant = null;
        try {
            instant = object.newInstance();
        } catch (InstantiationException | IllegalAccessException e) { e.printStackTrace(); }

        for (Field field : fields) {
            JsonKey key = field.getAnnotation(JsonKey.class);
            Object current = json.get(key.name());
            field.setAccessible(true);
            if (key.embed()) {
                current = fromJSON((JSONObject)current, object.getClass());
            }
            try {
                field.set(instant, current);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }

        return instant;
    }

    //deprecated for now
    @Deprecated
    public <T> T fromJSON(JSONObject json, T object) {
        Class<?> clazz = object.getClass();
        List<Field> fields = getAnnotatedFields(clazz);

        for (Field field : fields) {
            JsonKey key = field.getAnnotation(JsonKey.class);
            Object current = json.get(key.name());
            field.setAccessible(true);
            if (key.embed()) {
                current = fromJSON((JSONObject)current, field.getType());
            }
            try {
                field.set(object, current);
            } catch (IllegalAccessException e) {
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

}
