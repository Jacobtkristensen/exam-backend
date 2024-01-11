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

    public Reservation(LocalDate reservationDate, double price, Room room, Guest guest) {
        this.reservationDate = reservationDate;
        this.price = price;
        this.room = room;
        this.guest = guest;
        guest.addReservation(this);
        room.addReservation(this);
    }
}