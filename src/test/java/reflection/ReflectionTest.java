package reflection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import os.memorandum.reflection.ReflectionUtils;

public class ReflectionTest {

    @Test
    void getGenericParameterClass_success()
    {
        Class<?> clazz = ReflectionUtils.getGenericParameterClass(Repository.class, 0);

        Assertions.assertEquals(TestClass.class, clazz);
    }

    @Test
    void instanceOf_success()
    {
        Class<TestClass> clazz = TestClass.class;
        Object object = ReflectionUtils.instanceOf(clazz);

        Assertions.assertNotNull(object);
        Assertions.assertEquals(TestClass.class, object.getClass());
    }

}
