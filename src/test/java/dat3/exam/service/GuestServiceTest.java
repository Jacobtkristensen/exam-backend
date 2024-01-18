package dat3.exam.service;

import dat3.exam.dto.GuestRequest;
import dat3.exam.dto.GuestResponse;
import dat3.exam.entity.Guest;
import dat3.exam.repository.GuestRepository;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GuestServiceTest {

    @Autowired
    GuestRepository guestRepository;

    GuestService guestService;

    @BeforeEach
    void setUp() {
        guestService = new GuestService(guestRepository);
    }

    @Test
    void createGuest_Successful() {
        //Arrange
        GuestRequest newGuestRequest = new GuestRequest();
        newGuestRequest.setUsername("newUser");
        newGuestRequest.setPassword("newPassword");
        newGuestRequest.setEmail("newEmail");
        newGuestRequest.setFirstName("newFirstName");
        newGuestRequest.setLastName("newLastName");
        newGuestRequest.setPhoneNumber("newPhoneNumber");
        //Act
        GuestResponse createdGuest = guestService.createGuest(newGuestRequest);

        //Assert
        assertEquals("newUser", createdGuest.getUsername());
    }
    @Test
    void createGuest_ExistingUsername() {
        //Arrange
        Guest existingGuest = new Guest("existingUser", "password", "email@example.com", "FirstName", "LastName", "12345678");
        guestRepository.save(existingGuest);

        //Setup test-data
        GuestRequest newGuestRequest = new GuestRequest();
        newGuestRequest.setUsername("existingUser");
        newGuestRequest.setPassword("newPassword");
        newGuestRequest.setEmail("newEmail");
        newGuestRequest.setFirstName("newFirstName");
        newGuestRequest.setLastName("newLastName");
        newGuestRequest.setPhoneNumber("newPhoneNumber");
        //Assert
        assertThrows(ResponseStatusException.class, () -> {
            guestService.createGuest(newGuestRequest);
        }, "Guest already exists");
    }
}