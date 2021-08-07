package ha.hoclaptrinhweb.utils;

import ha.hoclaptrinhweb.model.ProductOfBillModel;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartUtil {

    public static ShoppingCartUtil shoppingCartUtil;
    public static List<ProductOfBillModel> models;

    public static ShoppingCartUtil getInstance() {
        if (shoppingCartUtil == null) {
            shoppingCartUtil = new ShoppingCartUtil();
            models = new ArrayList<>();
        }
        return shoppingCartUtil;
    }

    private List<ProductOfBillModel> addProduct(ProductOfBillModel value) {
        boolean check = false;
        for (ProductOfBillModel item: models) {
            if (item.getProductId() == value.getProductId()) {
                if (value.getQuality() == 0) {
                    models.remove(item);
                    return models;
                }
                if (item.getQuality() < value.getQuality()) {
                    item.setQuality(item.getQuality() + 1);
                } else if (item.getQuality() > value.getQuality()) {
                    item.setQuality(item.getQuality() - 1);
                }
               check = true;
            }
        }
        if (!check) {
            models.add(value);
        }
        return models;
    }

    public void putValue(HttpServletRequest request,String key, ProductOfBillModel value) {
       request.getSession().setAttribute(key, addProduct(value));
    }

    public List<ProductOfBillModel> getValue(HttpServletRequest request, String key) {
        return this.models;
    }

    public  void removeValue(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }

}
