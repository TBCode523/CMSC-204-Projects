package sample;

public class NoSpecialCharacterException extends RuntimeException {
    //private String pass;

    public final String message ="The password must contain at least one special character";
    @Override
    public String getMessage() {
        return message;
    }
}
