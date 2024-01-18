package dat3.exam.repository;

import dat3.exam.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, String> {

        Guest findByEmail(String email);
        Guest findByUsername(String username);
}
