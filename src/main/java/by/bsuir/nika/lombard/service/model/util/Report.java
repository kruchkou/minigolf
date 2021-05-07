package by.bsuir.nika.lombard.service.model.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report {

    private Integer acceptedItemsQuantity;
    private Integer acceptedItemsPrice;
    private Integer expiredItemsQuantity;
    private Integer expiredItemsPrice;
    private Integer realisedItemsQuantity;
    private Double realisedItemsProfit;

}
