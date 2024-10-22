package viettridao.mockproject.dtos.contracts;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettridao.mockproject.models.ContractSellBuy;
import viettridao.mockproject.models.Insurance;
import viettridao.mockproject.models.Tax;

/**
 * ContractSellBuyDTO
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractSellBuyDTO {

    private Long id;

    private Long contractBuyId;

    private Long contractSellId;

    private Long propertyId;

    @Positive(message = "Price is must be greater than 0")
    private Double price;

    @Positive(message = "Deposit is must be greater than 0")
    private Double deposit;

    private Date paymentDate;

    private Date handOverDate;

    private Set<Tax> taxes;

    private List<Insurance> insurances;

    private Double amount;

    private Date createDate;

    @Positive(message = "Progress is must be greater than 0")
    private Integer progress;

    private Boolean deleted;

    @JsonIgnore
    private MultipartFile attachmentFile;

    private String attachment;

    public ContractSellBuyDTO(ContractSellBuy contract) {
        this.id = contract.getId();
        this.contractBuyId = contract.getContractBuy().getId();
        this.contractSellId = contract.getContractSell().getId();
        this.propertyId = contract.getProperty().getId();
        this.price = contract.getPrice();
        this.deposit = contract.getDeposit();
        this.paymentDate = contract.getPaymentDate();
        this.handOverDate = contract.getHandOverDate();
        this.taxes = contract.getTaxes();
        this.insurances = contract.getInsurances();
        this.createDate = contract.getCreateDate();
        this.progress = contract.getProgress();
        this.deleted = contract.getDeleted();
        this.attachment = contract.getAttachment();
    }

    public static ContractSellBuyDTO fromContract(ContractSellBuy contract) {
        return new ContractSellBuyDTO(contract);
    }
}
