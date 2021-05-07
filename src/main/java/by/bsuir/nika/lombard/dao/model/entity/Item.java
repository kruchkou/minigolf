package by.bsuir.nika.lombard.dao.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import by.bsuir.nika.lombard.dao.model.util.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    private Integer id;
    private Client owner;
    private Category category;
    private String name;
    private String desc;
    private String condition;
    private Integer price;
    private LocalDate acceptDate;
    private Integer keepingDays;
    private Status status;

    public int getDaysLeft() {
        return (int) ChronoUnit.DAYS.between(LocalDate.now(),acceptDate.plusDays(keepingDays));
    }

}
