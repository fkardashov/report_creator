package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sochl_payments")
public class sochl_payments extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html"); //Задаем формат ответа - HTML, текст
        PrintWriter out = response.getWriter(); //Получаем объект, позволяющий записать контент в ответ
        out.write("<html>\n" +
                "<body>\n" +
                "<form action=\"sochl_payments\" method=\"POST\">\n" +
                "        First Name: <input type=\"text\" name=\"first_name\">\n" +
                "<br />\n" +
                "        Last Name: <input type=\"text\" name=\"last_name\" />\n" +
                "<input type=\"submit\" value=\"Submit\" />\n" +
                "</form>\n" +
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
