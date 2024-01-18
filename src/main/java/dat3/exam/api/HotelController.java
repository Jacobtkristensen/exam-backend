package dat3.exam.api;

import dat3.exam.dto.HotelRequest;
import dat3.exam.dto.HotelResponse;
import dat3.exam.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    //Anonymous, User, Admin
    //Liste af hoteller
    @GetMapping
    public List<HotelResponse> getAllHotels(){
        return hotelService.getAllHotels();
    }


    //Anonymous, User, Admin
    //Specifik hotelinformation
    @GetMapping("/{id}")
    public HotelResponse getHotelById(@PathVariable int id){
        return hotelService.getHotelById(id);
    }

    //ADMIN
    //Tilføj hotel
    @PostMapping
    public HotelResponse addHotel(@RequestBody HotelRequest body){
        return hotelService.addHotel(body);
    }

    //ADMIN
    //Rediger hotel
    @PatchMapping("/{id}")
    public HotelResponse editHotel(@RequestBody HotelRequest body, @PathVariable int id){
        return hotelService.editHotel(body, id);
    }

    //ADMIN
    //Slet hotel
    @DeleteMapping("/{id}")
    public HotelResponse deleteHotel(@PathVariable int id){
        return hotelService.deleteHotel(id);
    }
}
