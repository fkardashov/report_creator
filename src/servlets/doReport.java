package servlets;

import config.Config;
import config.ReadConfig;
import doc.docProcess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/doReport")
public class doReport extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html"); //Задаем формат ответа - HTML, текст
        response.setCharacterEncoding("windows-1251");
        PrintWriter out = response.getWriter(); //Получаем объект, позволяющий записать контент в ответ

        out.write(FormGenerator.getFormString());
    }
    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        OutputStream out = response.getOutputStream();

        String configFileName = "";
        switch (request.getParameterValues("radio")[0]) {
            case "vrvr":
                configFileName = "vrvr.json";
                break;
            case "sochl_payments":
                configFileName = "sochl_payments";
                break;
            case "sochl_deposit":
                configFileName = "sochl_deposit.json";
                break;
            case "test":
                configFileName = "test.json";
                break;

            default:
                out.write(FormGenerator.getFormString().getBytes());
                out.write(FormGenerator.getErrorDate("Какая-то фигня с радио-батонами").getBytes());
                return;
        }
        // загружаем нужный конфиг
        Config config = new ReadConfig().ParceConfig(getServletContext().getRealPath("/") + "/configs/" + configFileName);


        //проверка даты начал и конца теста
        if(!isDate(request.getParameterValues("start")[0])){
            out.write(FormGenerator.getFormString().getBytes());
            out.write(FormGenerator.getErrorDate("Неправильная дата начала теста").getBytes());
            return;
        }
        else {
            config.setStartDate(request.getParameterValues("start")[0]);
        }

        if(!isDate(request.getParameterValues("end")[0])) {

            out.write(FormGenerator.getFormString().getBytes());
            out.write(FormGenerator.getErrorDate("Неправильная дата конца теста").getBytes());
            return;
        }
        else {
            config.setEndDate(request.getParameterValues("end")[0]);
        }


        if (!request.getParameterValues("desc")[0].equals(""))
        {
            config.setTestName(request.getParameterValues("desc")[0]);
            out.write(request.getParameterValues("desc")[0].getBytes());
        }
        //делаем док
        docProcess doc = new docProcess();
        doc.createDoc(config);

        String fileName = new String(config.getFileFolder() + config.getFileName());
        File file = new File(fileName);

        response.setContentType("application/docx");
        response.setContentLength((int) file.length());
        response.setHeader( "Content-Disposition", "attachment; filename=" + config.getFileName());

        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[1024*1024];
        int length;
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }

    private boolean isDate(String date) {
        Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}\\S[0-9]{4}");
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
}
