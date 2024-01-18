package dat3.exam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

    public class Room extends AdminDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String roomNumber;
        private int numberOfBeds;
        private double price;
        private double basePrice;
        private double bedPrice;


        @ManyToOne
        @JoinColumn(name = "hotel_id")
        private Hotel hotel;

        @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
        private List<Reservation> reservations;

    public void addReservation(Reservation reservation){
        if(reservations == null) {
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }
        public Room(String roomNumber, int numberOfBeds, double basePrice, double bedPrice, Hotel hotel) {
            this.roomNumber = roomNumber;
            this.numberOfBeds = numberOfBeds;
            this.basePrice = basePrice;
            this.bedPrice = bedPrice;
            this.price = calculatePrice(numberOfBeds, basePrice, bedPrice);
            this.hotel = hotel;
        }
        //Udregner pris for et værelse baseret på basePrice, bedPrice og antal senge
        public static double calculatePrice(int numberOfBeds, double basePrice, double bedPrice) {
            return basePrice + (numberOfBeds - 1) * bedPrice;
        }
    }
