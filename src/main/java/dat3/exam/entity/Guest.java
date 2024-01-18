package dat3.exam.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Guest extends UserWithRoles {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany(mappedBy = "guest", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    public void addReservation(Reservation reservation){
        if(reservations == null) {
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }
    public Guest(String username, String password, String email, String firstname, String lastname, String phoneNumber) {
        super(username, password, email);
        this.firstName = firstname;
        this.lastName = lastname;
        this.phoneNumber = phoneNumber;
    }

}
