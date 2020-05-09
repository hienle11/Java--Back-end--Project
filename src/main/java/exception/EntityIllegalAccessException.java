package exception;

public class EntityIllegalAccessException extends RuntimeException{
    public EntityIllegalAccessException() {
        super();
    }

    public EntityIllegalAccessException(String message) {
        super(message);
    }

    public EntityIllegalAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityIllegalAccessException(Throwable cause) {
        super(cause);
    }

    protected EntityIllegalAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
