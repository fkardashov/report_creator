package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class index extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html"); //Задаем формат ответа - HTML, текст
        PrintWriter out = response.getWriter(); //Получаем объект, позволяющий записать контент в ответ
        out.write("<!DOCTYPE html>\n" + // Записываем в ответ HTML код простейшей странички
                "<html>\n" +
                "<head><title>A servlet without templates.</title></head>\n" +
                "<body bgcolor=\"#fdf5ff\">\n" +
                "<h1>Note:</h1>\n" +
                "<p>This whole page was created via servlet</p>\n" +
                "<a href=/sochl_payments>СОЧЛплажети</a>" +
                "</body></html>");
    }
}
