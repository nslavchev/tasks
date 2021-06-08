package temperatures;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Temperatures {
    public static void main(String[] args) throws ParseException {
        Map<String,Double> temps = new HashMap<>();

        try {
            temps = new ObjectMapper().readValue(new File("src/main/java/temperatures/temperaturesData.json"), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        Date day = dateFormatter.parse("01.01.2015");

        // a) first week
        double tempFirstWeek = 0;
        while (day.before(dateFormatter.parse("08.01.2015"))){
            tempFirstWeek += temps.get(dateFormatter.format(day));
            day = increment(day);
        }
        System.out.println("Average temperature during the first week: " + Math.round(tempFirstWeek / 7 * 100.0) / 100.0);

        // a) second week
        double tempSecondWeek = 0;
        while (day.before(dateFormatter.parse("15.01.2015"))){
            tempSecondWeek += temps.get(dateFormatter.format(day));
            day = increment(day);
        }
        System.out.println("Average temperature during the second week: " + Math.round(tempSecondWeek / 7 * 100.0) / 100.0);

        //b)
        day = dateFormatter.parse("02.01.2015");
        double prevTemp = temps.get(dateFormatter.format(dateFormatter.parse("01.01.2015")));
        System.out.println(customDateFormat(dateFormatter.parse("01.01.2015")) + " : -" + Math.round((prevTemp) * 100.0) / 100.0);
        while (day.before(dateFormatter.parse("15.01.2015"))){
            double currTemp = temps.get(dateFormatter.format(day));
            if (currTemp - prevTemp < 0){
                System.out.println(customDateFormat(day) + " : -" + Math.round((prevTemp - currTemp) * 100.0) / 100.0);
            }else if (currTemp - prevTemp > 0){
                System.out.println(customDateFormat(day) + " : +" + Math.round((currTemp - prevTemp) * 100.0) / 100.0);
            }else {
                System.out.println(customDateFormat(day) + " : +0");
            }

            day = increment(day);
            prevTemp = currTemp;
        }
    }

    static Date increment (Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static String customDateFormat (Date date) {
        DateFormat formatter = new SimpleDateFormat("EEEE, dd MMMM", Locale.ENGLISH);
        return formatter.format(date);
    }
}
