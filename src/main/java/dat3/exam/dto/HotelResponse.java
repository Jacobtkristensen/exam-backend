package dat3.exam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.exam.entity.Hotel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelResponse {
    private int id;
    private String name;
    private String street;
    private String city;
    private String zip;
    private String country;
    private int noOfRooms;
    public HotelResponse(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.street = hotel.getStreet();
        this.city = hotel.getCity();
        this.zip = hotel.getZip();
        this.country = hotel.getCountry();
        this.noOfRooms = hotel.getRooms().size();
    }

}
