package task.homerent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "contract", schema = "public")
public class Contract {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_house")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private House house;

    @ManyToOne
    @JoinColumn(name = "id_tenant")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private User user;

    @Column(name = "end_date")
    private LocalDate end_date;
    @Column(name = "start_date")
    private LocalDate start_date;
}
