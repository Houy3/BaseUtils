package os.memorandum;

import java.util.Arrays;
import java.util.Objects;

public class ArrayUtils {

    public static boolean isEmpty(Object[] array) {
        return array == null || Arrays.stream(array).noneMatch(Objects::nonNull);
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }
}
