package dat3.exam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomRequest {

    private String roomNumber;
    private int numberOfBeds;
    private double basePrice;
    private double bedPrice;
    private int hotelId;
}
