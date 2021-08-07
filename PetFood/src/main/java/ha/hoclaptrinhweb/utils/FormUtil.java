package ha.hoclaptrinhweb.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtil  {

    public static <T> T toModel(Class<T> tclass, HttpServletRequest request) {
        T obj = null;
        try {
            obj = tclass.getDeclaredConstructor().newInstance();
            BeanUtils.populate(obj, request.getParameterMap());

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |NoSuchMethodException e) {
            System.out.print(e.getMessage());
        }
        return obj;
    }
}
