package ng.pharmacy.data.repositories;

import ng.pharmacy.data.models.Medicine;
import ng.pharmacy.data.repositories.impl.InMemoryMedicineRepository;
import ng.pharmacy.exceptions.EntityNotFoundException;
import ng.pharmacy.exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryMedicineRepositoryTest {
    private InMemoryMedicineRepository inMemoryMedicineRepository;

    @BeforeEach
    public void setUp(){
        inMemoryMedicineRepository = new InMemoryMedicineRepository();
    }

    @Test
    public void test_save_medicine() {
        assertNotNull(inMemoryMedicineRepository.save(new Medicine()));
        assertThrows(ValidationException.class, ()-> inMemoryMedicineRepository.save(null));
    }

    @Test
    public void count_increasesPerSave() {
        inMemoryMedicineRepository.save(new Medicine());
        assertEquals(1, inMemoryMedicineRepository.count());
        inMemoryMedicineRepository.save(new Medicine());
        assertEquals(2, inMemoryMedicineRepository.count());
    }

    @Test
    public void count_returnsSameCountIfUpdateOccurs() {
        Medicine medicine =  new Medicine();
        medicine.setBrandName("Emzor");
        inMemoryMedicineRepository.save(medicine);
        assertEquals(1, inMemoryMedicineRepository.count());

        medicine.setBrandName("Longman");
        inMemoryMedicineRepository.save(medicine);
        assertEquals(1, inMemoryMedicineRepository.count());
    }

    @Test
    public void findById_returnsMedicineWithCorrectBrandName() {
        Medicine medicineOne = new Medicine();
        medicineOne.setGenericName("Paracetamol");
        medicineOne.setBrandName("Brand 1");
        inMemoryMedicineRepository.save(medicineOne);

        Medicine retrievedMed1 = inMemoryMedicineRepository.findById(medicineOne.getId());
        assertEquals("Brand 1", retrievedMed1.getBrandName());
        assertEquals("Paracetamol", retrievedMed1.getGenericName());

        Medicine medicineTwo =  new Medicine();
        medicineTwo.setGenericName("Paracetamol");
        medicineTwo.setBrandName("Brand 2");
        inMemoryMedicineRepository.save(medicineTwo);

        Medicine retrievedMed2 = inMemoryMedicineRepository.findById(medicineTwo.getId());
        assertEquals("Brand 2", retrievedMed2.getBrandName());
        assertEquals("Paracetamol", retrievedMed2.getGenericName());
    }
    
    @Test
    public void findAll_returnsAllMedicines() {
        for (int i = 0; i < 5; i++){
            inMemoryMedicineRepository.save(new Medicine());
        }
        List<Medicine> Medicines = inMemoryMedicineRepository.findAll();
        assertEquals(5, Medicines.size());
    }

    @Test
    public void deleteById_removesMedicineById() {
        for (int i = 0; i < 5; i++){
            inMemoryMedicineRepository.save(new Medicine());
        }
        inMemoryMedicineRepository.deleteById(1L);

        assertEquals(4, inMemoryMedicineRepository.count());
        assertThrows(EntityNotFoundException.class, ()-> inMemoryMedicineRepository.findById(1L));
    }

    @Test
    public void deleteAll_removesAllMedicine() {
        for (int i = 0; i < 5; i++){
            inMemoryMedicineRepository.save(new Medicine());
        }
        inMemoryMedicineRepository.deleteAll();

        assertEquals(0, inMemoryMedicineRepository.count());
    }

    @Test
    public void searchByName_returnsAllMatchingNames() {
        String[] drugNames = {"Paracetamol", "Paracetamol", "Anti-inflammation", "Antibiotic"};
        for (int i = 0; i < 4; i++){
            inMemoryMedicineRepository.save(new Medicine()).setGenericName(drugNames[i]);
        }

        List<Medicine> matches = inMemoryMedicineRepository.searchByName("Para");
        assertEquals(2, matches.size());
        matches = inMemoryMedicineRepository.searchByName("Anti");
        assertEquals(2, matches.size());
        matches = inMemoryMedicineRepository.searchByName("Anti-inflammation");
        assertEquals(1, matches.size());
    }
}
