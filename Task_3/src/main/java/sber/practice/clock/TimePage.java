package sber.practice.clock;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/time")
public class TimePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Date date = new Date();
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print("<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Ваше системное время</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=\"width: 700px; margin-left: auto; margin-right: auto; " +
                "text-align: center; margin-top: 300px; border: 2px solid #fe6637; border-radius: 8px;\">\n" +
                "        <h1>Ваше время: " + date.toString().split(" ")[3].substring(0, 5) + "</h1>\n" +
                "        <h3>Часовой пояс: " + date.toString().split(" ")[4] + "</h1>\n" +
                "    </div>" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/time");
    }
}
