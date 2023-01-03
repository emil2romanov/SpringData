package cardealer.domain.sale;

import cardealer.domain.car.dtos.CarWithoutPartsDto;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleWithDiscountDto {

    public CarWithoutPartsDto car;

    public String customerName;

    @SerializedName("Discount")
    public  double discount;

    public BigDecimal price;

    public BigDecimal priceWithDiscount;
}
