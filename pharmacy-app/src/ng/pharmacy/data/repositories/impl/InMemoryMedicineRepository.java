package ng.pharmacy.data.repositories.impl;

import ng.pharmacy.data.models.Medicine;
import ng.pharmacy.data.repositories.interfaces.MedicineRepository;
import ng.pharmacy.exceptions.EntityNotFoundException;
import ng.pharmacy.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryMedicineRepository implements MedicineRepository {
    private final Map<Long, Medicine> medicines = new HashMap<>();
    private long nextId = 1;

    @Override
    public Medicine save(Medicine medicine) {
        if (medicine == null) throw new ValidationException("Medicine cannot be null");

//        if (!medicines.containsValue(medicine)) medicine.setId(nextId++);
        if (medicine.getId() == 0) {
            medicine.setId(nextId++);
        }
        medicines.put(medicine.getId(), medicine);
        return medicine;
    }

    @Override
    public Medicine findById(Long id) {
        if  (medicines.containsKey(id)) {
            return medicines.get(id);
        } else throw new EntityNotFoundException("Medicine with id " + id + " not found");
    }

    @Override
    public List<Medicine> findAll() {
        return new ArrayList<>(medicines.values());
    }

    @Override
    public void deleteById(Long id) {
        medicines.remove(id);
    }

    @Override
    public long count() {
        return medicines.size();
    }

    @Override
    public List<Medicine> searchByName(String query) {
        List<Medicine> results = new ArrayList<>();
        for (Medicine medicine : medicines.values()) {
            if (medicine.matchesSearch(query)) {
                results.add(medicine);
            }
        }
        return results;
    }

    @Override
    public void deleteAll() {
        medicines.clear();
    }
}
