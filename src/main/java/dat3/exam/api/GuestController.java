package dat3.exam.api;

import dat3.exam.dto.GuestRequest;
import dat3.exam.dto.GuestResponse;
import dat3.exam.service.GuestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/guests")
@RestController
public class GuestController {

    GuestService guestService;
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }
    //Anonymous
    //Oprettelse af bruger
    @PostMapping
    public GuestResponse createGuest(@RequestBody GuestRequest body){
        return guestService.createGuest(body);
    }
}
