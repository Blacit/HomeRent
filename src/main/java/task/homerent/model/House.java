package task.homerent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table (name = "house", schema = "public")
public class House {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "house")
    private Set<Contract> contract;

    @Column(name = "landlord_id", nullable = false)
    private Long landlordId;
    @Column(name = "outside", nullable = false)
    private String outside;
    @Column(name = "rooms", nullable = false)
    private Integer rooms;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "description", nullable = false)
    private String description;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public House(Long landlordId, String outside, Integer rooms, Double price, String description) {
        this.landlordId = landlordId;
        this.outside = outside;
        this.rooms = rooms;
        this.price = price;
        this.description = description;
    }

    public House() {
    }
}
