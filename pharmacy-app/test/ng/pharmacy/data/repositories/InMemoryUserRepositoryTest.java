package ng.pharmacy.data.repositories;

import ng.pharmacy.data.models.User;
import ng.pharmacy.data.repositories.impl.InMemoryUserRepository;
import ng.pharmacy.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryUserRepositoryTest {

    private InMemoryUserRepository inMemoryUserRepository;

    @BeforeEach
    void setup() {
        inMemoryUserRepository = new InMemoryUserRepository();
    }

    @Test
    public void count_increasesPerSave() {
        inMemoryUserRepository.save(new User());
        assertEquals(1, inMemoryUserRepository.count());
        inMemoryUserRepository.save(new User());
        assertEquals(2, inMemoryUserRepository.count());
    }

    @Test
    public void count_returnsSameCountIfUpdateOccurs() {
        User user =  new User();
        user.setFullName("User One");
        user.setUsername("User_one");
        User savedUser = inMemoryUserRepository.save(user);
        assertEquals(1, inMemoryUserRepository.count());

        savedUser.setUsername("User_one_updated");
        User updatedUser = inMemoryUserRepository.save(savedUser);
        User foundUser = inMemoryUserRepository.findById(updatedUser.getId());
        assertEquals("User_one_updated", foundUser.getUsername());
        assertEquals(1, inMemoryUserRepository.count());
    }

    @Test
    public void findById_returnsUserWithCorrectUsername() {
        User user = new User();
        user.setFullName("User Two");
        user.setUsername("user_two");
        User savedUser = inMemoryUserRepository.save(user);

        User foundUser = inMemoryUserRepository.findById(savedUser.getId());

        assertEquals("user_two", foundUser.getUsername());
        assertEquals("User Two", foundUser.getFullName());
    }

    @Test
    public void findById_returnsUsersWithCorrectUsername() {
        User userOne =  new User();
        userOne.setFullName("User One");
        userOne.setUsername("user_one");
        inMemoryUserRepository.save(userOne);
        assertEquals(1, inMemoryUserRepository.count());

        User userTwo =  new User();
        userTwo.setFullName("User Two");
        userTwo.setUsername("user_two");
        inMemoryUserRepository.save(userTwo);
        assertEquals(2, inMemoryUserRepository.count());

        assertEquals("user_one", inMemoryUserRepository.findById(userOne.getId()).getUsername());
        assertEquals("user_two", inMemoryUserRepository.findById(userTwo.getId()).getUsername());
    }

    @Test
    public void deleteById_removesUserFromRepo() {
        User userOne =  new User();
        userOne.setFullName("User One");
        userOne.setUsername("user_one");
        inMemoryUserRepository.save(userOne);
        assertEquals(1, inMemoryUserRepository.count());

        User userTwo =  new User();
        userTwo.setFullName("User Two");
        userTwo.setUsername("user_two");
        inMemoryUserRepository.save(userTwo);
        assertEquals(2, inMemoryUserRepository.count());

        inMemoryUserRepository.deleteById(userOne.getId());
        assertEquals(1, inMemoryUserRepository.count());

        User userThree =  new User();
        userThree.setFullName("User Three");
        userThree.setUsername("user_three");
        inMemoryUserRepository.save(userThree);

        inMemoryUserRepository.deleteById(userTwo.getId());
        assertEquals(1, inMemoryUserRepository.count());
    }

    @Test
    public void test_deleteAll() {
        User userOne =  new User();
        userOne.setFullName("User One");
        userOne.setUsername("User_one");
        inMemoryUserRepository.save(userOne);

        User userTwo =  new User();
        userTwo.setFullName("User Two");
        userTwo.setUsername("User_two");
        inMemoryUserRepository.save(userTwo);

        User userThree =  new User();
        userThree.setFullName("User Three");
        userThree.setUsername("User_three");
        inMemoryUserRepository.save(userThree);

        assertEquals(3, inMemoryUserRepository.count());
        inMemoryUserRepository.deleteAll();

        assertEquals(0, inMemoryUserRepository.count());
    }

    @Test
    void test_deleteById() {
        User userOne =  new User();
        userOne.setId(10011);

        inMemoryUserRepository.save(userOne);
        assertEquals(1, inMemoryUserRepository.count());

        User userTwo = new User();
        userTwo.setId(10022);

        inMemoryUserRepository.save(userTwo);
        assertEquals(2, inMemoryUserRepository.count());
        inMemoryUserRepository.deleteById(userOne.getId());
        assertEquals(1, inMemoryUserRepository.count());
        assertThrows(EntityNotFoundException.class, ()-> inMemoryUserRepository.findById(10011L));
    }

    @Test
    void test_delete() {

    }

    @Test
    void deleteAll() {
    }

    @Test
    void findByUsername() {
    }

}
