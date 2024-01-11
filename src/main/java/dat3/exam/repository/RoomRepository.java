package dat3.exam.repository;

import dat3.exam.entity.Hotel;
import dat3.exam.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    boolean existsByHotelAndRoomNumber(Hotel hotel, String roomNumber);
}
