package viettridao.mockproject.dtos.contracts;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import viettridao.mockproject.models.ContractSell;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ContractSellDTO
 * Version: 1.0
 * Date: 5/24/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/24/2024 diepit9x Create
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractSellDTO {

    private Long id;

    private Long sellerId;

    private Long staffId;

    @Min(value = 1, message = "Property ID is invalid")
    private Long propertyId;

    @Min(value = 1, message = "Desired price must be > 0")
    private Double desiredPrice;

    @Min(value = 1, message = "Deposit must be > 0")
    private Double deposit;

    @NotBlank(message = "Payment method is not blank")
    @Size(min = 1, max = 30, message = "Payment method must be between 1 to 30 characters")
    private String paymentMethod;

    private Double brokerageFee;

    private Integer progress;

    private Boolean deleted;

    @JsonIgnore
    private MultipartFile attachmentFile;

    private String attachment;

    public ContractSellDTO(ContractSell contract) {
        this.id = contract.getId();
        this.sellerId = contract.getSeller().getId();
        this.staffId = contract.getStaff().getId();
        this.propertyId = contract.getProperty().getId();
        this.desiredPrice = contract.getDesiredPrice();
        this.deposit = contract.getDeposit();
        this.paymentMethod = contract.getPaymentMethod();
        this.brokerageFee = contract.getBrokerageFee();
        this.progress = contract.getProgress();
        this.deleted = contract.getDeleted();
        this.attachment = contract.getAttachment();
    }

    public static ContractSellDTO fromContract(ContractSell contract) {
        return new ContractSellDTO(contract);
    }
}
