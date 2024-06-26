package dat3.exam.service;

import dat3.exam.dto.HotelRequest;
import dat3.exam.dto.HotelResponse;
import dat3.exam.entity.Hotel;
import dat3.exam.repository.HotelRepository;
import dat3.exam.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HotelService {

    RoomRepository roomRepository;
    HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }
    public List<HotelResponse> getAllHotels() {
        return hotelRepository.findAll().stream().map(hotel -> new HotelResponse(hotel)).toList();
    }
    public HotelResponse getHotelById(int id) {
        return new HotelResponse(hotelRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hotel with this id found")));
    }

    public  HotelResponse addHotel(HotelRequest body) {
        if(hotelRepository.existsByName(body.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel with that name already exists");
        }
        Hotel newHotel = new Hotel(body.getName(), body.getStreet(), body.getCity(),body.getZip(),body.getCountry());
        return new HotelResponse(hotelRepository.save(newHotel));
    }
    //Rediger hotel
    public HotelResponse editHotel(HotelRequest body, int id) {
        //Tjek om hotel findes

        Hotel editHotel = hotelRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hotel with this id found"));
        //Tjek om request indeholder navn
        if (body.getName() != null) {
            editHotel.setName(body.getName());
        }
        //Tjek om request indeholder street
        if (body.getStreet() != null) {
            editHotel.setStreet(body.getStreet());
        }
        //Tjek om request indeholder city
        if (body.getCity() != null) {
            editHotel.setCity(body.getCity());
        }
        //Tjek om request indeholder zip
        if (body.getZip() != null) {
            editHotel.setZip(body.getZip());
        }
        //Tjek om request indeholder country
        if (body.getCountry() != null) {
            editHotel.setCountry(body.getCountry());
        }
        //returner gemte hotel
        return new HotelResponse(hotelRepository.save(editHotel));
    }


    public HotelResponse deleteHotel(int id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hotel with this id found"));
        HotelResponse response = new HotelResponse(hotel);
        hotelRepository.delete(hotel);
        return response;
    }

}