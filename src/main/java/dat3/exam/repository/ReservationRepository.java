package dat3.exam.repository;

import dat3.exam.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAllByGuest_Username(String username);
    boolean existsByHotelIdAndRoomRoomNumberAndReservationDate(int hotelId, String roomNumber, LocalDate date);
}
