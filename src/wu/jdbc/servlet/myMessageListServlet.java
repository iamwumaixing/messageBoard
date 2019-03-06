package wu.jdbc.servlet;

import wu.jdbc.bean.Message;
import wu.jdbc.bean.User;
import wu.jdbc.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class myMessageListServlet extends HttpServlet {
    private MessageService messageService;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Long id = user.getId();
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null && (!"".equals(pageStr))){
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        List<Message> messages = messageService.getMyMessages(page,5,id);
        int count = messageService.countMyMessages(id);
        int last = count % 5 == 0 ? (count / 5) : ((count / 5) + 1);
        request.setAttribute("messages",messages);
        request.setAttribute("page",page);
        request.setAttribute("last",last);
        request.getRequestDispatcher("/WEB-INF/views/biz/my_message_list.jsp").forward(request,response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        messageService = new MessageService();
    }

    @Override
    public void destroy() {
        super.destroy();
        messageService = null;
    }
}
