package dat3.exam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.exam.entity.Guest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuestResponse {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    //Response ved oprettelse af bruger
    //Guest -> GuestResponse
    public GuestResponse(Guest guest) {
        this.username = guest.getUsername();
        this.email = guest.getEmail();
        this.firstName = guest.getFirstName();
        this.lastName = guest.getLastName();
        this.phoneNumber = guest.getPhoneNumber();
    }
}
