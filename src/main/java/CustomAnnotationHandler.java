import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomAnnotationHandler {

    public String checkValueAnnotaition() {
        try {
            if (Human.class.getDeclaredField("hobby").isAnnotationPresent(JsonValue.class)) {
                return Human.class.getDeclaredField("hobby").getAnnotation(JsonValue.class).name();
            } else return Human.class.getDeclaredField("hobby").getName();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String checkDateFormatAnnotaition(Human human) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            Field field = Human.class.getDeclaredField("birthDate");
            field.setAccessible(true);
            if (field.isAnnotationPresent(CustomDateFormat.class)) {
                return ((LocalDate) field.get(human)).format(formatter);
            } else return field.get(human).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
