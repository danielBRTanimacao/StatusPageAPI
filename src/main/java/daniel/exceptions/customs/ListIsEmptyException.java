package daniel.exceptions.customs;

public class ListIsEmptyException extends RuntimeException{
    public ListIsEmptyException(String msg) {
        super(msg);
    }

    public ListIsEmptyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
