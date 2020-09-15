package sample;

public class LengthException extends  RuntimeException {
    public String message = "The password must be at least 6 characters long";
    @Override
    public String getMessage() {
        return message;
    }
}
