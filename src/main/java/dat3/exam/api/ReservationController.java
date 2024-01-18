package dat3.exam.api;

import dat3.exam.dto.ReservationRequest;
import dat3.exam.dto.ReservationResponse;
import dat3.exam.entity.Reservation;
import dat3.exam.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //USER
    @GetMapping("/my-reservations")
    public List<ReservationResponse> getAllReservations(Principal principal) {
        return reservationService.getAllReservationsByGuestUsername(principal.getName());
    }
    //USER
    @PostMapping
    public ReservationResponse reserveRoom(@RequestBody ReservationRequest body) {
        return reservationService.reserveRoom(body);
    }
}
