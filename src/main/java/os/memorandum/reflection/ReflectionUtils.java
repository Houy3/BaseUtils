package os.memorandum.reflection;

import os.memorandum.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

public class ReflectionUtils {

    /** Получить класс дженерика (только для случаев, когда реализуешь дженерик интерфейс
     * или наследуешься от дженерик класса. Например: TestRepository implements Repository<Test>>) */
    public static <T> Class<T> getGenericParameterClass(Class<?> actualClass, int parameterIndex) {
        Assert.notNull(actualClass, "actualClass");

        try {
            return (Class<T>)((ParameterizedType)actualClass.getGenericSuperclass()).getActualTypeArguments()[parameterIndex];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ReflectionException("Parameter index is out of bounds.");
        } catch (Exception e) {
            throw new ReflectionException("Actual class is not extend generic class.");
        }
    }

    /** Создать объект класса (только при наличии пустого конструктора) */
    public static <T> T instanceOf(Class<T> clazz) {
        Assert.notNull(clazz, "clazz");

        try {
            Constructor<T> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException e) {
            throw new ReflectionException("An empty constructor for " + clazz.getName() + " is required.");
        } catch (IllegalAccessException e) {
            throw new ReflectionException(clazz.getName() + " is private.");
        } catch (InstantiationException e) {
            throw new ReflectionException("Object of " + clazz.getName() + " cannot be created.");
        }
    }

    /** Получить значение из поля по имени поля */
    public static Object getValue(Object object, String fieldName) {
        Assert.notNull(object, "object");
        Assert.notNull(fieldName, "fieldName");

        return getValue(object, getField(object.getClass(), fieldName));
    }

    /** Получить значение из поля */
    public static Object getValue(Object object, Field field) {
        Assert.notNull(object, "object");
        Assert.notNull(field, "field");

        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new ReflectionException("No access to field " + object.getClass().getName() + "." + field.getName());
        }
    }

    /** Посадить значение на поле по имени поля */
    public static void setValue(Object object, String fieldName, Object value) {
        Assert.notNull(object, "object");
        Assert.notNull(fieldName, "fieldName");
        Assert.notNull(value, "value");

        setValue(object, getField(object.getClass(), fieldName), value);
    }

    /** Посадить значение на поле */
    public static void setValue(Object object, Field field, Object value) {
        Assert.notNull(object, "object");
        Assert.notNull(field, "field");

        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw new ReflectionException("No access to field " + object.getClass().getName() + "." + field.getName());
        }
    }

    /** Посадить поле по имени */
    public static Field getField(Class<?> clazz, String fieldName) {
        Assert.notNull(clazz, "clazz");
        Assert.notNull(fieldName, "fieldName");

        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new ReflectionException("Field " + clazz.getName() + "." + fieldName + " not found.");
        }
    }

}