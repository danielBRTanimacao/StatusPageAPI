package daniel.exceptions.customs;

public class DuplicateKeyColumnException extends RuntimeException{
    public DuplicateKeyColumnException(String msg) {
        super(msg);
    }

    public DuplicateKeyColumnException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
