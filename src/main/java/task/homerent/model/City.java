package task.homerent.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "city", schema = "public")
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH
    }, mappedBy = "city")
    @JsonBackReference
    private Set<House> house;

    @Column(name = "id_region", nullable = false)
    private Integer id_region;
    @Column(name = "name", nullable = false)
    private String name;
}
