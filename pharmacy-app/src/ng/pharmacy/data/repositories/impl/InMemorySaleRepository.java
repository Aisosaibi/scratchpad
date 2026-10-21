package ng.pharmacy.data.repositories.impl;

import ng.pharmacy.data.models.Sale;
import ng.pharmacy.data.repositories.interfaces.SaleRepository;
import ng.pharmacy.exceptions.EntityNotFoundException;
import ng.pharmacy.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySaleRepository implements SaleRepository {
    private final Map<Long, Sale> sales = new HashMap<>();
    private long nextId = 1;

    @Override
    public Sale save(Sale sale) {
        if (sale == null) throw new ValidationException("Sale cannot be null");
        sale.setId(nextId++);
        sales.put(sale.getId(), sale);
        return sale;
    }

    @Override
    public Sale findById(Long id) {
        if (sales.containsKey(id)) {
            return sales.get(id);
        } else throw new EntityNotFoundException("Sale with id " + id + " not found");
    }

    @Override
    public List<Sale> findAll() {
        return new ArrayList<>(sales.values());
    }

    @Override
    public void deleteById(Long id) {
        sales.remove(id);
    }

    @Override
    public long count() {
        return sales.size();
    }

    @Override
    public void deleteAll() {
        sales.clear();
    }
}
