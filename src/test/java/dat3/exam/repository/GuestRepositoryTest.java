package dat3.exam.repository;

import dat3.exam.entity.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GuestRepositoryTest {

    @Autowired
    GuestRepository guestRepository;

    boolean isInitialized = false;

    @BeforeEach
    void setUp() {
        if (!isInitialized) {
            Guest guest1 = new Guest("G1", "p123", "G1@kea.dk", "Hans", "Hansen", "87654321");
            guestRepository.save(guest1);

            isInitialized = true;
        }
    }

    @Test
    void findByEmail() {
        assertEquals("G1@kea.dk", guestRepository.findByEmail("G1@kea.dk").getEmail());
    }
}
