package sample;

public class NoSpecialSymbolException extends RuntimeException {
    public final String message = "The passwords do not match\n";

    @Override
    public String getMessage() {
        return message;
    }
}
