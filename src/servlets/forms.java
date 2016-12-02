package servlets;

public class forms {
    public static String formIndex = "<!DOCTYPE html>" +
            "<html><head><title>Получение протокола теста</title></head>" +
            "<body bgcolor=\"#6495ed\"  align=\"center\">" +
            "<h1>Получение протокола теста</h1>\n" +
            "<form action=\"do_report\" method=\"POST\">" +
                "Время старта: <input type=\"text\" name=\"start\">\n" +
                "<br /><br />\n" +
                "Время окончания: <input type=\"text\" name=\"stop\" />\n" +
                "<br /><br />\n" +
                "<p><input name=\"group\" type=\"radio\" value=\"sochl_payment\">СОЧЛ.Платежи</p>"+
                "<p><input name=\"group\" type=\"radio\" value=\"sochl_deposit\">СОЧЛ.Вклады</p>"+
                "<p><input name=\"group\" type=\"radio\" value=\"vrvr\" checked>ВРВР</p>"+
                "<input type=\"submit\" value=\"Пуск\" />\n" +
                "<br /><br />\n" +
            "</form>\n" +
            "</body></html>";
}
