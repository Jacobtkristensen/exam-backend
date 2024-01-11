package dat3.exam.api;

import dat3.exam.dto.RoomRequest;
import dat3.exam.dto.RoomResponse;
import dat3.exam.service.RoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public RoomResponse addRoom(@RequestBody RoomRequest body){
        return roomService.addRoom(body);
    }
}
