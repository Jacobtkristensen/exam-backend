package dat3.exam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.exam.entity.Guest;
import dat3.exam.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuestRequest {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Reservation> reservations;

    public static Guest getGuestEntity(GuestRequest guestRequest){
        return new Guest(
                guestRequest.username,
                guestRequest.password,
                guestRequest.email,
                guestRequest.firstName,
                guestRequest.lastName,
                guestRequest.phoneNumber);
    }
}
