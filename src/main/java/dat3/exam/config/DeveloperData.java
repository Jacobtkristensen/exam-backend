package dat3.exam.config;

import dat3.exam.entity.Guest;
import dat3.exam.entity.Hotel;
import dat3.exam.entity.Room;
import dat3.exam.repository.GuestRepository;
import dat3.exam.repository.HotelRepository;
import dat3.exam.repository.ReservationRepository;
import dat3.exam.repository.RoomRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class DeveloperData implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    GuestRepository guestRepository;
    HotelRepository hotelRepository;
    ReservationRepository reservationRepository;
    RoomRepository roomRepository;
    PasswordEncoder passwordEncoder;
    String passwordUsedByAll;


    public DeveloperData(UserWithRolesRepository userWithRolesRepository, GuestRepository guestRepository,
                         HotelRepository hotelRepository, ReservationRepository reservationRepository,
                         RoomRepository roomRepository, PasswordEncoder passwordEncoder) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.guestRepository = guestRepository;
        this.hotelRepository = hotelRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.passwordEncoder = passwordEncoder;
        passwordUsedByAll = "test12";
    }


    @Override
    public void run(ApplicationArguments args) {
        setupDevData();
    }

    private void setupDevData() {
        //Brugere
        Guest guest1 = new Guest("Guest1", passwordUsedByAll, "guest1@kea.dk", "Hans", "Hansen", "12345678");
        guest1.addRole(Role.USER);
        guestRepository.save(guest1);
        //Admin
        UserWithRoles admin = new UserWithRoles("admin", passwordUsedByAll, "admin@a.dk");
        admin.addRole(Role.ADMIN);
        userWithRolesRepository.save(admin);


        List<Hotel> hotels = new ArrayList<>();
        Random random = new Random();

        // Lav hoteller ud fra predefinerede lister. Navne er unikke. resten loopes tilfældigt igennem.
        for (int i = 0; i < HotelNameGenerator.HOTEL_NAMES.size(); i++) {
            String name = HotelNameGenerator.HOTEL_NAMES.get(i);
            String street = HotelStreetGenerator.STREETS.get(random.nextInt(HotelStreetGenerator.STREETS.size()));
            String city = HotelCityGenerator.CITIES.get(random.nextInt(HotelCityGenerator.CITIES.size()));
            String zip = HotelZipGenerator.ZIPS.get(random.nextInt(HotelZipGenerator.ZIPS.size()));
            String country = HotelCountryGenerator.COUNTRY_NAMES.get(random.nextInt(HotelCountryGenerator.COUNTRY_NAMES.size()));

            hotels.add(new Hotel(name, street, city, zip, country));
        }

        hotelRepository.saveAll(hotels);

        // Lav værelser til hvert hotel
        for (Hotel hotel : hotels) {
            List<Room> rooms = new ArrayList<>();
            int numberOfRooms = 10 + random.nextInt(31); // Random number of rooms between 10 and 40
            for (int i = 1; i <= numberOfRooms; i++) {
                String roomNumber = String.valueOf(i);
                int numberOfBeds = 1 + random.nextInt(4);
                double basePrice = 500.0;
                double bedPrice = 100.0;
                Room room = new Room(roomNumber, numberOfBeds, basePrice, bedPrice, hotel);
                rooms.add(room);
            }
            roomRepository.saveAll(rooms);
        }
    }
}

