package dat3.exam.repository;

import dat3.exam.entity.Hotel;
import dat3.exam.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class RoomRepositoryTest {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;
    boolean isInitialized = false;

    @BeforeEach
    void setUp() {
        if (!isInitialized) {
            roomRepository.deleteAll();
            Hotel hotel = new Hotel("D'angleterre", "Kgs. Nytorv 2", "Copenhagen", "1050", "Denmark");
            hotelRepository.save(hotel);
            Room room = new Room("101", 2, 1000, 200, hotel);
            roomRepository.save(room);
            isInitialized = true;
        }
    }

    @Test
    void existsByHotelAndRoomNumber_ReturnTrue() {
        // Find hotel via filter for at undgå at skulle lave metoden existsByName i hotelRepository bare for at teste denne metode
        List<Hotel> hotels = hotelRepository.findAll();
        Hotel hotel = hotels.stream().filter(h -> h.getName().equals("D'angleterre")).findFirst().get();

        assertTrue(roomRepository.existsByHotelAndRoomNumber(hotel, "101"));
    }
    @Test
    void existsByHotelAndRoomNumber_ReturnFalse() {
        // Find hotel via filter for at undgå at skulle lave metoden existsByName i hotelRepository bare for at teste denne metode
        List<Hotel> hotels = hotelRepository.findAll();
        Hotel hotel = hotels.stream().filter(h -> h.getName().equals("D'angleterre")).findFirst().get();

        assertFalse(roomRepository.existsByHotelAndRoomNumber(hotel, "102"));
    }
}