package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/do_report")
public class do_report extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html"); //Задаем формат ответа - HTML, текст
        PrintWriter out = response.getWriter(); //Получаем объект, позволяющий записать контент в ответ
        out.write("<html>\n" +
                "<body>\n" +

                "</body>\n" +
                "</html>");
    }
    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html"); //Задаем формат ответа - HTML, текст
        PrintWriter out = response.getWriter(); //Получаем объект, позволяющий записать контент в ответ
        out.write("<html>\n" +
                "<body>\n" +
                "asdfsadfasfdasdf" +
                "</body>\n" +
                "</html>");
    }
}
