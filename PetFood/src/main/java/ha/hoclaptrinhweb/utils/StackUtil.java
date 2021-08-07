package ha.hoclaptrinhweb.utils;

import javax.servlet.http.HttpServletRequest;

public class StackUtil {
    public static StackUtil stackUtil;

    public static StackUtil getInstance() {
        if (stackUtil == null) {
            stackUtil = new StackUtil();
        }
        return  stackUtil;
    }

    public void putValue(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    public Object getValue(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    public  void removeValue(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }
}
