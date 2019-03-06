package wu.jdbc.servlet;

import wu.jdbc.service.RegeditService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.IllegalFormatCodePointException;
import java.util.Objects;

/**
 * 注册界面Servlet
 *
 */
public class RegPromptServlet extends HttpServlet {
    private RegeditService regeditService;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String pathName = request.getServletPath();
        if (Objects.equals("/regPrompt.do",pathName)){
            request.getRequestDispatcher("/WEB-INF/views/biz/regedit.jsp").forward(request,response);
        } else if (Objects.equals("/regedit.do",pathName)){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean flag = regeditService.addUser(username,password);
            if (flag){
                request.getRequestDispatcher("/WEB-INF/views/biz/login.jsp").forward(request,response);
            } else {
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
            }
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        regeditService = new RegeditService();
    }
}