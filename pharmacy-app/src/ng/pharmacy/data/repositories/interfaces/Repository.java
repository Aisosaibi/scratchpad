package ng.pharmacy.data.repositories.interfaces;

import java.util.List;

public interface Repository <T, ID>{
    T save(T entity);
    T findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    long count();
    void deleteAll();
}