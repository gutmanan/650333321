/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Yair Etzion
 */
public abstract class ValidatorManager {
    
    public static boolean isAlpha(String name) { 
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c) && c!=' ') {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    
    public static boolean onlyContainsNumbers(String text) {
        if (text.length()==0) return false;
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    } 
    
    public static boolean isAlphaCode(String name) { 
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isValidURL(String urlString){
        try {
            URL url = new URL(urlString);
            url.toURI();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    
    public static Date isValidDate(String inDate) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
          date = dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
          return date;
        }
    return date;
    
  }
    public static boolean checkFacebook(String s){
        return (s.contains("facebook"));
    }
    
    public static boolean checkGoogle(String s){
        return (s.contains("google.co.il/maps"));
    }
    
}
