package viettridao.mockproject.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettridao.mockproject.models.Insurance;
import viettridao.mockproject.services.imp.FormatterService;

/**
 * InsuranceDTO
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 * 5/30/2024 kiet-kun-afk Update
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDTO {

    @JsonIgnore
    private FormatterService formatter = new FormatterService();

    private Long id;

    private Long contractSellBuyId;

    private String name;

    private Double price;

    private String expirationDate;

    private Boolean lien;

    private Boolean deed;

    public InsuranceDTO(Insurance insurance) {
        this.id = insurance.getId();
        this.contractSellBuyId = insurance.getContractSellBuy().getId();
        this.name = insurance.getName();
        this.price = insurance.getPrice();
        this.expirationDate = formatter.dateTimeToString(insurance.getExpirationDate());
        this.lien = insurance.getLien();
        this.deed = insurance.getDeed();
    }

    public static InsuranceDTO fromInsurance(Insurance insurance) {
        return new InsuranceDTO(insurance);
    }
}
