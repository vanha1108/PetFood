package ha.hoclaptrinhweb.dao;

import ha.hoclaptrinhweb.model.ProducerModel;
import ha.hoclaptrinhweb.paging.Pageble;

import java.util.List;

public interface IProducerDAO extends GenericDAO<ProducerModel>{
    ProducerModel findOne(Long id);
    Long save(ProducerModel producerModel);
    void update(ProducerModel producerModel);
    List<ProducerModel> findAll(Pageble pageble);
    List<ProducerModel> findAll();
    void delete(long ids);
    ProducerModel findOneByCode(String code);
    int getTotalProducer();
    List<String> findAllCode();
}
