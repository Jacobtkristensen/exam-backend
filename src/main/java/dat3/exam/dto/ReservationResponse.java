package dat3.exam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.exam.entity.Guest;
import dat3.exam.entity.Hotel;
import dat3.exam.entity.Reservation;
import dat3.exam.entity.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

    private int id;
    private LocalDate reservationDate;
    private double price;
    private String username;
    private int hotelId;
    private String hotelName;
    private String roomNumber;
    private int numberOfBeds;


    //konverter Reservation til ReservationResponse
    public ReservationResponse(Reservation reservation, Room room, Hotel hotel, Guest guest) {
        this.id = reservation.getId();
        this.reservationDate = reservation.getReservationDate();
        this.price = reservation.getPrice();
        this.username = guest.getUsername();
        this.hotelId = hotel.getId();
        this.hotelName = hotel.getName();
        this.roomNumber = room.getRoomNumber();
        this.numberOfBeds = room.getNumberOfBeds();

    }

}
