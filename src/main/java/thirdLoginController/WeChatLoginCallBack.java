package thirdLoginController;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WeChatLoginCallBack extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        System.out.println("code: " + code);

        String token = getToken(code);
        System.out.println("token: " + token);

        String userInfo = getUserInfo(token);
        resp.getWriter().println("Welcome!" + userInfo);
    }

    private String getUserInfo(String token){
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod("http://localhost:8080/user?token=" + token);
        String result = null;
        try {
            httpClient.executeMethod(getMethod);
            result = new String(getMethod.getResponseBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private String getToken(String code){
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod("http://localhost:8080/token?code=" + code);
        String token = null;
        try {
            httpClient.executeMethod(getMethod);
            token = new String(getMethod.getResponseBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

}
