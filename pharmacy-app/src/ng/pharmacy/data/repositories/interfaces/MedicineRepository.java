package ng.pharmacy.data.repositories.interfaces;

import ng.pharmacy.data.models.Medicine;

import java.util.List;

public interface MedicineRepository extends Repository<Medicine, Long> {
    List<Medicine> searchByName(String query);
}
