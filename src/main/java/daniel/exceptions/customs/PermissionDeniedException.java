package daniel.exceptions.customs;

public class PermissionDeniedException extends RuntimeException{
    public PermissionDeniedException(String msg) {
        super(msg);
    }

    public PermissionDeniedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
