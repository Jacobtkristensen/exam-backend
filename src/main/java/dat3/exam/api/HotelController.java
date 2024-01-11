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
    @GetMapping
    public List<HotelResponse> getAllHotels(){
        return hotelService.getAllHotels();
    }


    //Anonymous, User, Admin
    @GetMapping("/{id}")
    public HotelResponse getHotelById(@PathVariable int id){
        return hotelService.getHotelById(id);
    }

    //ADMIN
    @PostMapping
    public HotelResponse addHotel(@RequestBody HotelRequest body){
        return hotelService.addHotel(body);
    }

    //ADMIN
    @PatchMapping("/{id}")
    public HotelResponse editHotel(@RequestBody HotelRequest body, @PathVariable int id){
        return hotelService.editHotel(body, id);
    }

    //ADMIN
    @DeleteMapping("/{id}")
    public HotelResponse deleteHotel(@PathVariable int id){
        return hotelService.deleteHotel(id);
    }
}
