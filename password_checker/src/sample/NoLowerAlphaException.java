package sample;

public class NoLowerAlphaException extends RuntimeException{
    public final String  message = "The password must contain at least one lower case alphabetic character";

    @Override
    public String getMessage() {
        return message;
    }
}
