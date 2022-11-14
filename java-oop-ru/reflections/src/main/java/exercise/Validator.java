package exercise;

import java.lang.reflect.Field;
import java.util.*;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> notValidFields = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                if (f.isAnnotationPresent(NotNull.class) && f.get(address) == null) {
                    notValidFields.add(f.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return notValidFields;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> notValidFields = new LinkedHashMap<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                if (f.isAnnotationPresent(NotNull.class) && f.get(address) == null && f.isAnnotationPresent(MinLength.class)) {
                    notValidFields.put(f.getName(), List.of("can not be null", "length is less than " + f.getDeclaredAnnotation(MinLength.class).minLength()));
                } else if (f.isAnnotationPresent(NotNull.class) && f.get(address) == null && !f.isAnnotationPresent(MinLength.class)) {
                    notValidFields.put(f.getName(), List.of("can not be null"));
                } else if (f.isAnnotationPresent(NotNull.class) && f.isAnnotationPresent(MinLength.class)
                        && f.get(address).toString().length() <= f.getDeclaredAnnotation(MinLength.class).minLength()) {
                    notValidFields.put(f.getName(), List.of("length is less than " + f.getDeclaredAnnotation(MinLength.class).minLength()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return notValidFields;
    }
}
// END
