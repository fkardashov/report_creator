package servlets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FormGenerator {
    public static String getFormString() {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        df.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        String time = df.format(new Date());

        return "<!DOCTYPE html>" +
                "<html><head><title>Получение протокола теста</title>" +
                "  <style>\n" +
                "   h1 {\n" +
                "    font-family: consolas;\n" +
                "    font-size: 200%;\n" +
                "   } \n" +
                "   p {\n" +
                "    font-family: consolas; \n" +
                "    font-size: 12pt;\n" +
                "   }\n" +
                "  </style>" +
                "</head>" +

                "<body bgcolor=\"#B4EEB4\">" +

                "<table height=\"800\" cellpadding=\"0\" border=\"0\" align=\"center\">" +
                "<tr><td style=\"vertical-align:middle\">" +
                "<h1>Получение протокола теста</h1>\n" +
                "<form action=\"doReport\" method=\"POST\">" +
                "<p>Время старта <input type=\"text\" value=\"" +
                time +
                "\" name=\"start\">\n" +

                "Время окончания <input type=\"text\" value=\"" +
                time +
                "\" name=\"end\" />\n" +
                "<br /><br />\n" +
                "Название теста <input type=\"text\" value=\"\" name=\"desc\" size=\"65\"  />\n" +
                "<br /><br />\n" +
                "<input name=\"radio\" type=\"radio\" value=\"sochl_payments\" />СОЧЛ.Платежи<br/>" +
                "<input name=\"radio\" type=\"radio\" value=\"sochl_deposit\" />СОЧЛ.Вклады<br/>" +
                "<input name=\"radio\" type=\"radio\" value=\"vrvr\"         />ВРВР<br/>" +
                "<input name=\"radio\" type=\"radio\" value=\"test\" checked />ТЕСТ<br/>" +
                "</p>" +
                "<input type=\"submit\" value=\"Пуск\" />\n" +
                "</td></tr></table>" +
                "</form>\n" +
                "</body></html>";
    }

    public static String getErrorDate(String string){
        return "<style>" +
                "h2 {font-family: consolas;" +
                "font-size: 150%;" +
                "font-color: red"+
                "align: center" +
                "}" +
                "</style>" +
                "<h2>" +
                string +
                "</h2>";
    }

}
