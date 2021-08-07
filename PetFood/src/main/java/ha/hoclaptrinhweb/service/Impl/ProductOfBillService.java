package ha.hoclaptrinhweb.service.Impl;

import ha.hoclaptrinhweb.model.ProductOfBillModel;
import ha.hoclaptrinhweb.service.IProductOfBillService;

import java.util.List;

public class ProductOfBillService implements IProductOfBillService {

    @Override
    public double getTotalPrice(List<ProductOfBillModel> models) {
        double total = 0;
        for (ProductOfBillModel item: models) {
            total += item.getQuality() * item.getProductModel().getPrice();
        }
        return total;
    }
}
