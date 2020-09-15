package sample;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PasswordCheckerUtility {
  private static String message;

    public static boolean isValidPassword(String pass) throws LengthException, NoDigitException, NoUpperAlphaException, NoLowerAlphaException, NoSpecialSymbolException, InvalidSequenceException
    {

        try {


            if (isValidLength(pass) &&
                    hasUpperAlpha(pass) &&
                    hasLowerAlpha(pass) && hasDigit(pass) &&
                    hasSpecialChar(pass) &&
                    hasSameCharInSequence(pass))

                return true;
        } catch (RuntimeException e){
            message =e.getMessage();
            return false;
        }
        return false;
    }



    public static boolean hasBetweenSixAndNineChars(String pass){
        if(pass.length() >= 6 && pass.length() <= 9) return true;
        return false;
    }
    public static boolean IsWeakPassword(String pass){
        if(hasBetweenSixAndNineChars(pass)) return true;
        else return false;
    }
   public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalid = new ArrayList<String>();
        for(String s: passwords){
            if(!isValidPassword(s)) invalid.add(s + " -> " + message);
        }
        return invalid;
    }
    public static boolean hasUpperAlpha(String pass){
        for(int i = 0; i < pass.length(); i++){
            if(Character.isUpperCase(pass.charAt(i))) return true;
        }
        throw new NoUpperAlphaException();
    }
    public static boolean hasLowerAlpha(String pass){
        for(int i = 0; i < pass.length(); i++){
            if(Character.isLowerCase(pass.charAt(i))) return true;
        }
        throw new NoLowerAlphaException();
    }
    public static boolean hasDigit(String pass){
        for(int i = 0; i < pass.length(); i++){
            if(Character.isDigit(pass.charAt(i))) return true;
        }
        throw new NoDigitException();

    }
    public static boolean hasSpecialChar(String pass){

           if (!(pass.contains("@") || pass.contains("#")
                    || pass.contains("!") || pass.contains("~")
                    || pass.contains("$") || pass.contains("%")
                    || pass.contains("^") || pass.contains("&")
                    || pass.contains("*") || pass.contains("(")
                    || pass.contains(")") || pass.contains("-")
                    || pass.contains("+") || pass.contains("/")
                    || pass.contains(":") || pass.contains(".")
                    || pass.contains(", ") || pass.contains("<")
                    || pass.contains(">") || pass.contains("?")
                    || pass.contains("|"))) throw new NoSpecialCharacterException();

        return true;
    }
    public static boolean hasSameCharInSequence(String pass){
        System.out.println("Password is: " + pass);
        String s = "\\w{3}";

        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(pass);
        System.out.println("Attempting to find sequence!");
        for(int i = 0; i < pass.length(); i++) {
            if (matcher.find(i)) {
                String str = matcher.group();
                System.out.println("The found pattern is: " + str);
                // System.out.print(str.substring(0,1) + str.substring(1,2) + str.substring(2,3);
                if (str.substring(0, 1).equals(str.substring(1, 2)) && str.substring(0, 1).equals(str.substring(2, 3))) {
                    throw new InvalidSequenceException();
                }
            }
        }
        return true;

    }

    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    public static boolean isValidLength(String pass) {
        if(pass.length() < 6) throw new LengthException();
        return true;
    }
    public static boolean isWeakPassword(String pass){
        return !hasBetweenSixAndNineChars(pass) && isValidPassword(pass);
    }

    public static void comparePasswords(String password, String passwordConfirm) {
        if(!password.equals(passwordConfirm)) throw new UnmatchedException();
    }


}
