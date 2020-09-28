package task.homerent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "contract", schema = "public")
public class Contract {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // ManyToMany к Дому
    @ManyToOne
    @JoinColumn(name = "id_house")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private House house;

    // ManyToMany к Пользователю
    @ManyToOne
    @JoinColumn(name = "id_tenant")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private User user;


    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "start_date")
    private Date id_house;
}
