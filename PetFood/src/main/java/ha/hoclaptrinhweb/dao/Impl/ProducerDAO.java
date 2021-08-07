package ha.hoclaptrinhweb.dao.Impl;

import ha.hoclaptrinhweb.dao.IProducerDAO;
import ha.hoclaptrinhweb.mapper.ProducerMapper;
import ha.hoclaptrinhweb.model.CategoryModel;
import ha.hoclaptrinhweb.model.ProducerModel;
import ha.hoclaptrinhweb.paging.Pageble;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProducerDAO extends AbstractDAO<ProducerModel> implements IProducerDAO {

    @Override
    public ProducerModel findOne(Long id) {
        String sql = "SELECT * FROM producer WHERE id = ?";
        List<ProducerModel> results = query(sql, new ProducerMapper(), id);
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Long save(ProducerModel producerModel) {
        String sql = "INSERT INTO producer (codeproducer, nameproducer, phonenumber) VALUES (?, ?, ?)";
        return insert(sql, producerModel.getCodeProducer(), producerModel.getNameProducer(), producerModel.getPhoneNumber());
    }

    @Override
    public void update(ProducerModel producerModel) {
        String sql = "UPDATE producer SET codeproducer = ?, nameproducer = ?, phonenumber = ? WHERE id = ?";
        update(sql, producerModel.getCodeProducer(), producerModel.getNameProducer(), producerModel.getPhoneNumber(), producerModel.getId());
    }

    @Override
    public List<ProducerModel> findAll(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM producer");

        if(pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
        }

        if(pageble.getOffset() != null && pageble.getLimit() != null ) {
            sql.append(" LIMIT "+pageble.getOffset() + ", " + pageble.getLimit());
        }
        return query(sql.toString(), new ProducerMapper());
    }

    @Override
    public List<ProducerModel> findAll() {
        String sql = "SELECT * FROM producer";
        return query(sql, new ProducerMapper());
    }

    @Override
    public void delete(long ids) {
        String sql = "DELETE FROM producer WHERE id = ?";
        update(sql, ids);
    }

    @Override
    public ProducerModel findOneByCode(String code) {
        String sql = "SELECT * FROM producer WHERE codeproducer = ?";
        List<ProducerModel> producerModels = query(sql, new ProducerMapper(), code);
        return producerModels.isEmpty() ? null : producerModels.get(0);
    }

    @Override
    public int getTotalProducer() {
        String sql = "SELECT count(*) FROM producer";
        return count(sql);
    }

    @Override
    public List<String> findAllCode() {
        String sql = "SELECT * FROM producer";
        List<ProducerModel> producerModels = query(sql, new ProducerMapper());
        List<String> results = new ArrayList<>();
        for (ProducerModel item : producerModels) {
            results.add(item.getCodeProducer());
        }
        return results;
    }


}
