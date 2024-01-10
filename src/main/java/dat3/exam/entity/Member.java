package dat3.exam.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member extends UserWithRoles {
    String firstname;


    public Member(String username, String password, String email, String firstname) {
        super(username, password, email);
        this.firstname = firstname;
    }

}
