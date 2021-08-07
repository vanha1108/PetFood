package ha.hoclaptrinhweb.service;

import ha.hoclaptrinhweb.model.ProducerModel;
import ha.hoclaptrinhweb.paging.Pageble;

import java.util.List;

public interface IProducerService {
    ProducerModel findOne(Long id);
    ProducerModel save(ProducerModel producerModel);
    ProducerModel update(ProducerModel updateModel);
    List<ProducerModel> findAll(Pageble pageble);
    List<ProducerModel> findAll();
    void delete(long[] ids);
    int getTotalProducer();
    List<String> findAllCode();
}
