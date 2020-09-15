package sample;

public class UnmatchedException extends RuntimeException {
    public String message = "Passwords do not match";

    @Override
    public String getMessage() {
        return message;
    }
}
