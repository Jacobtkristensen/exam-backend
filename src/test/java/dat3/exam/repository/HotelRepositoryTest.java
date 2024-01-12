package dat3.exam.repository;

import dat3.exam.entity.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class HotelRepositoryTest {


    @Autowired
    HotelRepository hotelRepository;

    boolean isInitialized = false;

    @BeforeEach
    void setUp() {
        if (!isInitialized) {
            Hotel hotel = new Hotel("D'angleterre", "Kgs. Nytorv 2", "Copenhagen", "1050", "Denmark");
            hotelRepository.save(hotel);
            isInitialized = true;
        }
    }
    @Test
    void existsByName_ReturnsTrue() {
        assertTrue(hotelRepository.existsByName("D'angleterre"));
    }
}