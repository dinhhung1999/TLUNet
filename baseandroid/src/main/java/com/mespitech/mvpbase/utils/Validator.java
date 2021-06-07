package com.mespitech.mvpbase.utils;


import java.util.regex.Pattern;

public class Validator {
//    public static boolean email(String email) {
//        return email.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
//    }
    public static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    public static boolean hasSpace(String password){
        if(password.length()==password.trim().length())
            return true;
        else
            return false;
    }
    public static boolean hasLength(String password){
        if(password.length()>=8)
            return true;
        else
            return false;
    }
    public static boolean hasSymbol(String password){
        return !password.matches("[A-Za-z0-9]*");
    }
    public static boolean hasUpperCase(String password){
        return !password.equals(password.toLowerCase());
    }
    public static boolean hasLowerCase(String password){
        return !password.equals(password.toUpperCase());
    }
    public static boolean hasNumber(String password){
        String regex = "(.)*(\\d)(.)*";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(password).matches();
    }
}
