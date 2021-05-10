package by.bsuir.angelina.minigolf.dao.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    private Integer id;
    private String surname;
    private String name;
    private LocalDate birthdate;
    private String phoneCell;
    private String email;
    private Office office;
    private PaymentType paymentType;
    private Coach coach;
    private Integer personQuantity;
    private Integer hours;

    public Integer getPrice() {
        return personQuantity*hours*20;
    }

}
