package dat3.exam.service;

import dat3.exam.dto.GuestRequest;
import dat3.exam.dto.GuestResponse;
import dat3.exam.entity.Guest;
import dat3.exam.repository.GuestRepository;
import dat3.security.entity.Role;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GuestService {

    GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public GuestResponse createGuest(GuestRequest body) {
        Guest guest = GuestRequest.getGuestEntity(body);

        guestRepository.findById(guest.getUsername()).ifPresent(g -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Guest already exists");
        });
        if(guestRepository.findByEmail(guest.getEmail()) != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        guest.addRole(Role.USER);
        guestRepository.save(guest);
        return new GuestResponse(guest);
    }
}
