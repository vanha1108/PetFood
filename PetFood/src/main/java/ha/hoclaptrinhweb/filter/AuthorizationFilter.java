package ha.hoclaptrinhweb.filter;


import ha.hoclaptrinhweb.constant.SystemConstant;
import ha.hoclaptrinhweb.model.AccountModel;
import ha.hoclaptrinhweb.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if(url.startsWith("/admin")) {
            AccountModel model = (AccountModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
            if(model != null) {
                if(model.getRole().getCode().equals("USER")) {
                    response.sendRedirect(request.getContextPath()+"/dang-nhap?action=dangnhap&message=permission_denied");
                } else if(model.getRole().getCode().equals("ADMIN")) {
                    chain.doFilter(servletRequest, servletResponse);
                }
            } else {
                response.sendRedirect(request.getContextPath()+"/dang-nhap?action=dangnhap&message=not_login");
            }
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
