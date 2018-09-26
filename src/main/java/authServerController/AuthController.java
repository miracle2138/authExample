package authServerController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class AuthController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String appId = req.getParameter("appid");
        String type = req.getParameter("type");
        String callback = req.getParameter("callback");
        System.out.println("appid: " + appId);
        System.out.println("type: " + type);
        System.out.println("callback: " + callback);

        validateAppId(appId);

        if ("code".equals(type)){
            resp.sendRedirect(callback + "?code=" + genCode());
        }
    }

    private void validateAppId(String appId){
        // check
    }

    private String genCode(){
        return UUID.randomUUID().toString();
    }

}
