package wu.jdbc.servlet;

import wu.jdbc.bean.Message;
import wu.jdbc.bean.User;
import wu.jdbc.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 留言处理Servlet
 *
 */
public class MessageServlet extends HttpServlet {
    private MessageService messageService;
    @Override
    public void init() throws ServletException {
        super.init();
        messageService = new MessageService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathName = request.getServletPath();
        if (Objects.equals("/addMessagePrompt.do",pathName)){
            request.getRequestDispatcher("/WEB-INF/views/biz/add_message.jsp").forward(request,response);
        } else if (Objects.equals("/addMessage.do",pathName)){
            User user = (User) request.getAttribute("user");
            if (null == user){
                request.getRequestDispatcher("/message/list.do").forward(request,response);
            }else {
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                boolean flag = messageService.addMessage(new Message(user.getId(),user.getName(),title,content));
                if (flag){
                    request.getRequestDispatcher("/message/list.do").forward(request,response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
                }
            }
        } else if (Objects.equals("/alterMessagePrompt.do",pathName)){
            String title = new String(request.getParameter("title").getBytes("iso8859-1"),"utf-8");
            String content = new String(request.getParameter("content").getBytes("iso8859-1"),"utf-8");
            Long id = Long.valueOf(request.getParameter("id"));
            request.setAttribute("content",content);
            request.setAttribute("title",title);
            request.setAttribute("id",id);
            request.getRequestDispatcher("/WEB-INF/views/biz/alter_message.jsp").forward(request,response);
        } else if (Objects.equals("/alterMessage.do",pathName)){
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Long id = Long.valueOf(request.getParameter("id"));
            boolean flag = messageService.alterMessage(title,content,id);
            if (flag){
                request.getRequestDispatcher("/my/message/list.do").forward(request,response);
            } else {
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
            }
        } else if (Objects.equals("/deleteMessage.do",pathName)){
            Long id = Long.valueOf(request.getParameter("id"));
            boolean flag = messageService.deleteMessage(id);
            if (flag) {
                request.getRequestDispatcher("/my/message/list.do").forward(request,response);
            } else {
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
            }
        }
        else {
            request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
        }
    }
}
