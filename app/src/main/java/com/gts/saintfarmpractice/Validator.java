package com.gts.saintfarmpractice;

import android.content.Context;

public class Validator {
    private static final String TAG = Validator.class.getSimpleName();
    private static final String NUMBER_PATTERN = "\\p{Digit}+";
    private static final String NAME_PATTERN = "^[a-zA-Z ]*$";
    private static final String MOBILE_PATTERN = "[0-9]{10}";
    private static final String USERNAME_PATTERN = "[a-zA-Z0-9@.]*$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
    private static final String DATE_PATTERN = "(0?[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)";
    private static final int MINOR_CHILD_MAX_AGE = 18;

    /*
     * validate first name is empty.
     * */
    public static String isValidFirstName(Context context, String firstName) {
        String message = "";
        if (firstName == null || (firstName.trim()).equals("")) {
            message = "Enter First Name";
        }
        return message;
    }

    /*
     * validate last name is empty and Can be merge with First Name
     * */
    public static String isValidLastName(Context context, String lastName) {
        String message = "";
        if (lastName == null || (lastName.trim()).equals("")) {
            message = "Enter Last Name";
        }
        return message;
    }

    /*
     * validate email id by using @android email pattern
     * */
    public static String isValidEmail(Context context, String email) {
        String message = "";
        if (email == null || (email.trim()).equals("") || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            message = "Enter Email Address";
        }
        return message;
    }

    /*
     * validate zip code by using mobile @pattern
     * */
    public static String isValidMobile(Context context, String mobile, boolean isCheckEmpty, boolean isCheckMaxLen) {
        String message = "";
        if (mobile == null || (mobile.trim()).equals("")) {
            if (isCheckEmpty) {
                message = "Enter Mobile Number";
            }
        } else if (isCheckMaxLen && !mobile.matches(MOBILE_PATTERN)) {
            message = "Mobile Length Invalid";
        }
        return message;
    }

    /*
     * validate address1 is empty
     * */
    public static String isValidAddress1(Context context, String address1) {
        String message = "";
        if (address1 == null || (address1.trim()).equals("")) {
            message = "Enter Address";
        }
        return message;
    }

    /*
     * validate House No is empty
     * */
    public static String isValidHouseNoFlatNo(Context context, String address1) {
        String message = "";
        if (address1 == null || (address1.trim()).equals("")) {
            message = "Enter House/Flat/Block No.";
        }
        return message;
    }

    /*
     * validate address2 is empty
     * */
    public static String isValidAddress2(Context context, String address2) {
        String message = "";
        if (address2 == null || (address2.trim()).equals("")) {
            message = "Enter Address";
        }
        return message;
    }

    /*
     * validate first name is empty.
     * */
    public static String isValidPassword(Context context, String password) {
        String message = "";
        if (password == null || (password.trim()).equals("")) {
            message = "Enter Password";
        }
        return message;
    }

    /*
     * validate password is empty,length validation and password @pattern
     * */
    public static String isValidNewPassword(Context context,String password) {
        String message = "";
        if (password == null || (password.trim()).equals("")) {
            // Password Empty
            // 1 latter Capital
            message = "Enter Valid Password";
//        } else if (password.trim().length() < 8) {
//            message = context.getResources().getString(R.string.password_length_invalid);
//        } else if (password.trim().length() > 15) {
//            message = context.getResources().getString(R.string.password_length_valid_maximum);
//        } else if (!password.matches(PASSWORD_PATTERN)) {
//            message = context.getResources().getString(R.string.password_pattern_invalid);
        }
        return message;
    }

    /*
     * validate password is empty,length validation and password @pattern
     * */
    public static String isValidConfirmPassword(Context context,String password,String confirmPassword) {
        String message = "";
        if (confirmPassword == null || (confirmPassword.trim()).equals("")) {
            // Password Empty
            // 1 latter Capital
            message = "Confirm Password";
        } else if (!(password.trim()).equalsIgnoreCase(confirmPassword.trim())) {
            message = "New Password & Confirm did not match";
//        } else if (password.trim().length() > 15) {
//            message = context.getResources().getString(R.string.password_length_valid_maximum);
//        } else if (!password.matches(PASSWORD_PATTERN)) {
//            message = context.getResources().getString(R.string.password_pattern_invalid);
        }
        return message;
    }

    /*
     * validate Referance Mobile Number is empty,length validation and password @pattern
     * */
    public static String isValidRefMobNum(Context context,String password) {
        String message = "";
        if (password == null || (password.trim()).equals("")) {
            // Password Empty
            // 1 latter Capital
            message = "Enter Mobile No.";
//        } else if (password.trim().length() < 8) {
//            message = context.getResources().getString(R.string.password_length_invalid);
//        } else if (password.trim().length() > 15) {
//            message = context.getResources().getString(R.string.password_length_valid_maximum);
//        } else if (!password.matches(PASSWORD_PATTERN)) {
//            message = context.getResources().getString(R.string.password_pattern_invalid);
        }
        return message;
    }
}
