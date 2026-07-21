package ng.pharmacy.data.repositories;

import java.util.List;

public interface ParentRepository<T, id> {
    long count();
    T save(T entity);
    id findById(id ID);
    List<T> findAll();
    void deleteById(id ID);
    void deleteAll();
}
