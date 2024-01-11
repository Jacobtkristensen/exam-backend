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
        Guest guest1 = new Guest("Guest1", passwordUsedByAll, "guest1@a.dk", "Hans", "Hansen", "12345678");
        guest1.addRole(Role.USER);
        guestRepository.save(guest1);
        UserWithRoles admin = new UserWithRoles("admin", passwordUsedByAll, "admin@a.dk");
        admin.addRole(Role.ADMIN);
        userWithRolesRepository.save(admin);


        List<Hotel> hotels = new ArrayList<>();
        Hotel hotel1 = new Hotel("Hotel 1", "Street 1", "City 1", "Zip 1", "Country 1");
        hotels.add(hotel1);
        Hotel hotel2 = new Hotel("Hotel 2", "Street 2", "City 2", "Zip 2", "Country 2");
        hotels.add(hotel2);
        Hotel hotel3 = new Hotel("Hotel 3", "Street 3", "City 3", "Zip 3", "Country 3");
        hotels.add(hotel3);
        Hotel hotel4 = new Hotel("Hotel 4", "Street 4", "City 4", "Zip 4", "Country 4");
        hotels.add(hotel4);
        Hotel hotel5 = new Hotel("Hotel 5", "Street 5", "City 5", "Zip 5", "Country 5");
        hotels.add(hotel5);

        hotelRepository.saveAll(hotels);

        for (Hotel hotel : hotels) {
            List<Room> rooms = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                String roomNumber = String.valueOf(i);
                int numberOfBeds = i;

                // find en måde at automatisere basePrice og bedPrice på

                double basePrice = 100;
                double bedPrice = 50;
                Room room = new Room(roomNumber, numberOfBeds,basePrice,bedPrice, hotel);
                rooms.add(room);
            }
            roomRepository.saveAll(rooms);
        }




//        Random random = new Random();
//        List<Hotel> hotels = new ArrayList<>();
//
//        for (int i = 1; i <= 250; i++) {
//            String name = "Hotel " + i;
//            String street = "Street " + i;
//            String city = "City " + i;
//            String zip = "Zip" + String.format("%05d", i);
//            String country = "Country " + i;
//
//            Hotel hotel = new Hotel(name, street, city, zip, country);
//            hotels.add(hotel);
//        }
//        hotelRepository.saveAll(hotels);
//
//        for (Hotel hotel : hotels) {
//            int numberOfRooms = 10 + random.nextInt(31);
//            List<Room> rooms = new ArrayList<>();
//            for (int j = 1; j <= numberOfRooms; j++) {
//                String roomNumber = Integer.toString(j);
//                int numberOfBeds = 1 + random.nextInt(4);
//                double price = Room.calculatePrice(numberOfBeds);
//
//                Room room = new Room(roomNumber, numberOfBeds, price, hotel);
//                rooms.add(room);
//            }
//            roomRepository.saveAll(rooms);
    }
}

