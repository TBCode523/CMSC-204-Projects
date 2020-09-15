package sample;

public class InvalidSequenceException extends RuntimeException {
    public String message = "The password cannot contain more than two of the same character in sequence";
    @Override
    public String getMessage() {
        return message;
    }
}
