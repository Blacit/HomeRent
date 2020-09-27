package task.homerent.model;

import lombok.Data;

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
    private House house;

    // ManyToMany к Пользователю
    @ManyToOne
    @JoinColumn(name = "id_tenant")
    private User user;


    @Column(name = "end_date", nullable = false)
    private Date endDate;
    @Column(name = "start_date", nullable = false)
    private Date id_house;
}
