package ha.hoclaptrinhweb.service;

import ha.hoclaptrinhweb.model.ProductOfBillModel;

import java.util.List;

public interface IProductOfBillService {
    double getTotalPrice(List<ProductOfBillModel> models);
}
