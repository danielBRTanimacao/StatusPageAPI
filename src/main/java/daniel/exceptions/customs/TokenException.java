package daniel.exceptions.customs;

public class TokenException extends RuntimeException{
    public TokenException(String msg) {
        super(msg);
    }

    public TokenException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
