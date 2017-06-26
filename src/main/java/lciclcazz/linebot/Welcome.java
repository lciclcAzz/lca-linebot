package lciclcazz.linebot;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by lciclcazz on 6/24/2017.
 */

@WebServlet("/")
public class Welcome extends HttpServlet{
    private static final String APP_NAME = System.getenv("APP_NAME");
        @Override
        public void doGet(HttpServletRequest req, HttpServletResponse res) {

            PrintWriter out;
            try {
                out = res.getWriter();
                out.print("lciclcAzz's Line Bot Messages.");
                out.println("APP_NAME : "+APP_NAME);
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            res.setStatus(HttpServletResponse.SC_OK);
        }
}
