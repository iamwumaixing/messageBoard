package wu.jdbc.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetEncodingFilter implements Filter {
    private String characterEncoding = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.characterEncoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding(characterEncoding);
        servletResponse.setCharacterEncoding(characterEncoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
