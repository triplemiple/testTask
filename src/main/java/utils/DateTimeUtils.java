package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtils {

    public final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static boolean isValidFormat(String format, String value) {
        LocalDateTime ldt;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, Locale.getDefault());

        try {
            ldt = LocalDateTime.parse(value, fomatter);
            String result = ldt.format(fomatter);
            return result.equals(value);
        } catch (Exception e) {
            //ignored
        }
        return false;
    }

    public static boolean isBefore(String format, String arg1, String arg2) {
        LocalDateTime arg1Ldt;
        LocalDateTime arg2Ldt;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, Locale.getDefault());

        try {
            arg1Ldt = LocalDateTime.parse(arg1, fomatter);
            arg2Ldt = LocalDateTime.parse(arg2, fomatter);
            return arg2Ldt.isBefore(arg1Ldt);
        } catch (Exception e) {
            //ignored
        }
        return false;
    }
}
