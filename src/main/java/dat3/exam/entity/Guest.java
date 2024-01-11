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

    @OneToMany(mappedBy = "guest")
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public Guest(String username, String password, String email, String firstname, String lastname, String phoneNumber) {
        super(username, password, email);
        this.firstName = firstname;
        this.lastName = lastname;
        this.phoneNumber = phoneNumber;
    }

}
