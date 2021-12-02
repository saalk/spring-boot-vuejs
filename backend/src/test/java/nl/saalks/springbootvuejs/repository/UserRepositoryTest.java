package nl.saalks.springbootvuejs.repository;

import nl.saalks.springbootvuejs.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository users;

    private final User lindaHuis = new User("linda", "Huis");
    private final User saalksMeulen = new User("Saalks", "Meulen");

    @BeforeEach
    public void fillSomeDataIntoOurDb() {
        // Add new Users to Database
        entityManager.persist(lindaHuis);
        entityManager.persist(saalksMeulen);
    }

    @Test
    public void testFindByLastName() throws Exception {
        // Search for specific User in Database according to lastname
        List<User> usersWithLastNameHuis = users.findByLastName("Huis");

        assertThat(usersWithLastNameHuis, contains(lindaHuis));
    }

    @Test
    public void testFindByFirstName() throws Exception {
        // Search for specific User in Database according to firstname
        List<User> usersWithFirstNameSaalks = users.findByFirstName("Saalks");

        assertThat(usersWithFirstNameSaalks, contains(saalksMeulen));
    }

}