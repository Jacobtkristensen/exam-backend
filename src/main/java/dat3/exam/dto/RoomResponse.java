package dat3.exam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.exam.entity.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomResponse {

    private int id;
    private String roomNumber;
    private int numberOfBeds;
    private double price;
    private int hotelId;

    //Response ved oprettelse af værelse
    public RoomResponse(Room room) {
        this.id = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.numberOfBeds = room.getNumberOfBeds();
        this.price = room.getPrice();
        this.hotelId = room.getHotel().getId();
    }
}
