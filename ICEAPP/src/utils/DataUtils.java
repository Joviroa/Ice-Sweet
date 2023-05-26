package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    public static String formatarData(Date data) {
        return DATE_FORMATTER.format(data);
    }

    public static Date parseData(String dataStr) throws ParseException {
        return DATE_FORMATTER.parse(dataStr);
    }

    public static String obterDataAtual() {
        Date dataAtual = new Date();
        return formatarData(dataAtual);
    }

    public static Date adicionarDias(Date data, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DAY_OF_MONTH, dias);
        return calendar.getTime();
    }

    public static boolean verificarDataAntesDe(Date data1, Date data2) {
        return data1.before(data2);
    }

    public static boolean verificarDataDepoisDe(Date data1, Date data2) {
        return data1.after(data2);
    }

    public static boolean verificarMesmoDia(Date data1, Date data2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(data1);
        cal2.setTime(data2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }
}

