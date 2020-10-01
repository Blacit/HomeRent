package task.homerent.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "log", schema = "public")
public class Log {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Who - кем совершено событие
    @Column(name = "who")
    String who;

    // Even - действие
    @Column(name = "event")
    String event;
}
