package os.memorandum;

public class Utils {


    @SafeVarargs
    public static <T> T nvl(T ... objects) {
        for (T obj : objects) {
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }
}
