package sample;

public class NoUpperAlphaException extends RuntimeException {
    public  String message = "The password must contain at least one uppercase alphabetic character";
    @Override
    public String getMessage() {
        return message;
    }
}
