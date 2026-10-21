package ng.pharmacy.data.repositories;

import ng.pharmacy.data.models.Drug;

import java.util.Map;

public class DrugRepositoryImpl implements DrugRepository{

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Drug save(Drug entity) {
        return null;
    }

    @Override
    public Long findById(Long ID) {
        return 0L;
    }

    @Override
    public Map<Drug> findAll() {
        return Map.of();
    }

    @Override
    public void deleteById(Long ID) {

    }

    @Override
    public void deleteAll() {

    }
}
