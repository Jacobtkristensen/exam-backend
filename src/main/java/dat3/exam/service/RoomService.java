package dat3.exam.service;

import dat3.exam.dto.RoomRequest;
import dat3.exam.dto.RoomResponse;
import dat3.exam.entity.Hotel;
import dat3.exam.entity.Room;
import dat3.exam.repository.HotelRepository;
import dat3.exam.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RoomService {

    RoomRepository roomRepository;
    HotelRepository hotelRepository;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    public RoomResponse addRoom(RoomRequest body) {
        Hotel hotel = hotelRepository.findById(body.getHotelId()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No hotel with this id found"));
        if (roomRepository.existsByHotelAndRoomNumber(hotel, body.getRoomNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel already has this room number");
        }
        Room room = new Room(
                body.getRoomNumber(),
                body.getNumberOfBeds(),
                body.getBasePrice(),
                body.getBedPrice(),
                hotel);
        Room savedRoom = roomRepository.save(room);
        return new RoomResponse(savedRoom);
    }

}
