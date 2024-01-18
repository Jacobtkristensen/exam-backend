package dat3.exam.repository;

import dat3.exam.entity.Hotel;
import dat3.exam.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    boolean existsByHotelAndRoomNumber(Hotel hotel, String roomNumber);

    List<Room> findAllByHotel(Hotel hotel);
    Room findByHotelAndRoomNumber(Hotel hotel, String roomNumber);

    boolean countAllByHotelId(int hotelId);
}
