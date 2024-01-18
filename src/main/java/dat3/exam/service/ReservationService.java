package dat3.exam.service;

import dat3.exam.dto.GuestRequest;
import dat3.exam.dto.GuestResponse;
import dat3.exam.dto.ReservationRequest;
import dat3.exam.dto.ReservationResponse;
import dat3.exam.entity.Guest;
import dat3.exam.entity.Hotel;
import dat3.exam.entity.Reservation;
import dat3.exam.entity.Room;
import dat3.exam.repository.GuestRepository;
import dat3.exam.repository.HotelRepository;
import dat3.exam.repository.ReservationRepository;
import dat3.exam.repository.RoomRepository;
import dat3.security.entity.Role;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;


@Service
public class ReservationService {

GuestRepository guestRepository;
ReservationRepository reservationRepository;
HotelRepository hotelRepository;
RoomRepository roomRepository;

    public ReservationService(GuestRepository guestRepository, ReservationRepository reservationRepository, HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    public List<ReservationResponse> getAllReservationsByGuestUsername(String username) {
        Guest guest = guestRepository.findByUsername(username);
        if (guest == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No guest with this username found");
        }
        return reservationRepository.findAllByGuest_Username(username).stream().map(reservation ->
                new ReservationResponse(reservation, reservation.getRoom(), reservation.getHotel(), reservation.getGuest())).toList();
    }
    public ReservationResponse reserveRoom (ReservationRequest body) {

        Guest guest = guestRepository.findByUsername(body.getUsername());
        if (guest == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No guest with this username found");
        }
        Hotel hotel = hotelRepository.findById(body.getHotelId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hotel with this id found"));

        Room room = roomRepository.findByHotelAndRoomNumber(hotel, String.valueOf(body.getRoomNumber()));
        if (room == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No room with this number found");
        }
        if (reservationRepository.existsByHotelIdAndRoomRoomNumberAndReservationDate(body.getHotelId(),
                String.valueOf(body.getRoomNumber()), LocalDate.parse(body.getReservationDate()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room is already reserved");
        }
        Reservation newReservation = reservationRepository.save(new Reservation(LocalDate.parse(body.getReservationDate()),
                room, guest, hotel));
        return new ReservationResponse(newReservation, room, hotel, guest);
    }



}
