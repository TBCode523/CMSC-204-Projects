package sample;

public class WeakPasswordException extends  RuntimeException{
    public String message = "The password is OK but weak - it contains fewer than 10 characters";
    @Override
    public String getMessage() {
        return message;
    }
}
