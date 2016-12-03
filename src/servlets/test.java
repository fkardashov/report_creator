package servlets;

import config.Config;
import config.ReadConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet("/test")
public class test extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        OutputStream out = response.getOutputStream();

        String configFileName = "test.json";

        // загружаем нужный конфиг
        Config config = new ReadConfig().ParceConfig(getServletContext().getRealPath("/") + "/configs/" + configFileName);
        out.write(config.getTestName().getBytes());
    }
}