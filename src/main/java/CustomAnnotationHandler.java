import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CustomAnnotationHandler {

    public Map<String, String> checkForAnnotations(Object object) {
        Map<String, String> objectMap = new HashMap<>();
        try {
            Class<?> objectClass = object.getClass();
            for (Field field : objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                String key = (field.isAnnotationPresent(JsonValue.class) ? field.getAnnotation(JsonValue.class).name()
                        : field.getName());
                String value = (field.isAnnotationPresent(CustomDateFormat.class) && field.getType().equals(LocalDate.class)
                        ? ((LocalDate) field.get(object)).format(DateTimeFormatter.ofPattern(field.getAnnotation(CustomDateFormat.class).format()))
                        : field.get(object).toString());
                objectMap.put(key, value);
            }
            return objectMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}