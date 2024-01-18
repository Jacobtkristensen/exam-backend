package dat3.exam.api;

import dat3.exam.dto.RoomRequest;
import dat3.exam.dto.RoomResponse;
import dat3.exam.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    //Tilføj værelse til hotel
    //ADMIN
    @PostMapping
    public RoomResponse addRoom(@RequestBody RoomRequest body){
        return roomService.addRoom(body);
    }


    //Liste af værelser på hotel
    //USER
    @GetMapping("/{id}")
    public List<RoomResponse> getAllRoomsByHotelId(@PathVariable int id){
        return roomService.getAllRoomsByHotelId(id);
    }
}

