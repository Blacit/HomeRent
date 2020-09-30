package task.homerent.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ContractDto {

    @NotNull
    @NotEmpty
    private LocalDate start_date;

    @NotNull
    @NotEmpty
    private LocalDate end_date;

    @NotNull
    @NotEmpty
    private Long id_house;

    @NotNull
    @NotEmpty
    private Long id_tenant;

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public Long getId_house() {
        return id_house;
    }

    public void setId_house(Long id_house) {
        this.id_house = id_house;
    }

    public Long getId_tenant() {
        return id_tenant;
    }

    public void setId_tenant(Long id_tenant) {
        this.id_tenant = id_tenant;
    }
}
