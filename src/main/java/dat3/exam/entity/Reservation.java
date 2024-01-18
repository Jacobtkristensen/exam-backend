package dat3.exam.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation extends AdminDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate reservationDate;
    private double price;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Reservation(LocalDate reservationDate, Room room, Guest guest, Hotel hotel) {
        this.reservationDate = reservationDate;
        this.room = room;
        this.guest = guest;
        this.hotel = hotel;
        this.price = room.getPrice();
        guest.addReservation(this);
        room.addReservation(this);
    }
}