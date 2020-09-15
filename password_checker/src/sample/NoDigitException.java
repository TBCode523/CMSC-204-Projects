package sample;

public class NoDigitException extends RuntimeException {
    public final String message ="The password must contain at least one digit";
    @Override
    public String getMessage() {
        return message;
    }
}
