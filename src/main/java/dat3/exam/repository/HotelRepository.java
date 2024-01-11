package dat3.exam.repository;

import dat3.exam.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    boolean existsByName(String name);
}
