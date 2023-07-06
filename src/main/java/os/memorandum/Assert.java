package os.memorandum;

public class Assert {


    public static void notNull(Object object, String objectName) {
        if (object == null) {
            throw new NullPointerException(objectName + " cannot be null.");
        }
    }

}
