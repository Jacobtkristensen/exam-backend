package dat3.exam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Hotel extends AdminDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    private String street;
    private String city;
    private String zip;
    private String country;

    @OneToMany(mappedBy = "hotel",  cascade = CascadeType.REMOVE)
    private List<Room> rooms = new ArrayList<>();


    public Hotel(String name, String street, String city, String zip, String country) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }
}
