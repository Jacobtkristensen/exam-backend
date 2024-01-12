package dat3.exam.service;

import dat3.exam.dto.HotelRequest;
import dat3.exam.dto.HotelResponse;
import dat3.exam.entity.Hotel;
import dat3.exam.repository.HotelRepository;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class HotelServiceTest {


    HotelService hotelService;

    @Autowired
    HotelRepository hotelRepository;


    @BeforeEach
    void setUp() {
        hotelService = new HotelService(hotelRepository, null);
    }
    @Test
    void getAllHotels() {
        Hotel hotel1 = new Hotel("D'angleterre", "Kgs. Nytorv 2", "Copenhagen", "1050", "Denmark");
        hotelRepository.save(hotel1);
        Hotel hotel2 = new Hotel("D'angleterre2", "Kgs. Nytorv 22", "Copenhagens", "1051", "Denmark");
        hotelRepository.save(hotel2);

        List<HotelResponse> hotels = hotelService.getAllHotels();

        assertEquals(2, hotels.size());
    }

    @Test
    void getHotelById() {
        Hotel hotel1 = new Hotel("D'angleterre", "Kgs. Nytorv 2", "Copenhagen", "1050", "Denmark");
        hotelRepository.save(hotel1);

        HotelResponse hotelReponse = hotelService.getHotelById(hotel1.getId());

        assertEquals(hotel1.getId(), hotelReponse.getId());
    }

    @Test
    void addHotel() {
        HotelRequest hotelRequest = new HotelRequest();
        hotelRequest.setName("D'angleterre");
        hotelRequest.setStreet("Kgs. Nytorv 2");
        hotelRequest.setCity("Copenhagen");
        hotelRequest.setZip("1050");
        hotelRequest.setCountry("Denmark");

        HotelResponse hotelResponse = hotelService.addHotel(hotelRequest);

        assertEquals(hotelRequest.getName(), hotelResponse.getName());
    }

//    @Test
//    void editHotel() {
//    }
//
//    @Test
//    void deleteHotel() {
//    }
}