package task.homerent.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class HouseDto {

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @NotEmpty
    private Long landlord_id;

    @NotNull
    @NotEmpty
    private String outside;

    @NotNull
    @NotEmpty
    private Double price;

    @NotNull
    @NotEmpty
    private Integer rooms;

    @NotNull
    @NotEmpty
    private Long city_id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLandlord_id() {
        return landlord_id;
    }

    public void setLandlord_id(Long landlord_id) {
        this.landlord_id = landlord_id;
    }

    public String getOutside() {
        return outside;
    }

    public void setOutside(String outside) {
        this.outside = outside;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }
}
