package com.tienda.utils;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
public class DateManipulator {
    public static boolean isValidDateFormat(String dateString) {
        Pattern pattern = Pattern.compile("^\\d{4}/(0?[1-9]|1[0-2])/\\d{1,2}$");
        return pattern.matcher(dateString).matches();
    }

    public static String dateFormate(String originalDate){
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = originalFormat.parse(originalDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        SimpleDateFormat desiredFormat = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = desiredFormat.format(date);
        return formattedDate;
    }

    public static String actuallityDay(){
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return  currentDate;
    }
}
