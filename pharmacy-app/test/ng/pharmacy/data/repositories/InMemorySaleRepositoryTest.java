package ng.pharmacy.data.repositories;

import ng.pharmacy.data.models.Sale;
import ng.pharmacy.data.repositories.impl.InMemorySaleRepository;
import ng.pharmacy.exceptions.EntityNotFoundException;
import ng.pharmacy.exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemorySaleRepositoryTest {
    
    private InMemorySaleRepository inMemorySaleRepository;

    @BeforeEach
    public void setUp(){
        inMemorySaleRepository = new InMemorySaleRepository();
    }

    @Test
    public void test_save_sale() {
        assertNotNull(inMemorySaleRepository.save(new Sale()));
        assertThrows(ValidationException.class, ()-> inMemorySaleRepository.save(null));
    }

    @Test
    public void count_increasesPerSave() {
        inMemorySaleRepository.save(new Sale());
        assertEquals(1, inMemorySaleRepository.count());
        inMemorySaleRepository.save(new Sale());
        assertEquals(2, inMemorySaleRepository.count());
    }

    @Test
    public void findById_returnsSaleWithCorrectId() {
        Sale sale1 = new Sale();
        sale1.setDateTime(LocalDateTime.now());
        inMemorySaleRepository.save(sale1);

        Sale sale2 = new Sale();
        sale2.setDateTime(LocalDateTime.now());
        inMemorySaleRepository.save(sale2);

        Sale found = inMemorySaleRepository.findById(sale2.getId());
        assertEquals(sale2.getId(), found.getId());
    }

    @Test
    public void findAll_returnsAllSaleS() {
        for (int i = 0; i < 5; i++){
            inMemorySaleRepository.save(new Sale());
        }
        List<Sale> sales = inMemorySaleRepository.findAll();
        assertEquals(5, sales.size());
    }

    @Test
    public void deleteById_removesSaleById() {
        for (int i = 0; i < 5; i++){
            inMemorySaleRepository.save(new Sale());
        }
        inMemorySaleRepository.deleteById(1L);

        assertEquals(4, inMemorySaleRepository.count());
        assertThrows(EntityNotFoundException.class, ()-> inMemorySaleRepository.findById(1L));
    }

    @Test
    public void deleteAll_removesAllMedicine() {
        for (int i = 0; i < 5; i++){
            inMemorySaleRepository.save(new Sale());
        }
        inMemorySaleRepository.deleteAll();

        assertEquals(0, inMemorySaleRepository.count());
    }
}
