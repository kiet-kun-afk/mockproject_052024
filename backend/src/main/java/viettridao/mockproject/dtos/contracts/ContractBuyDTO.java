package viettridao.mockproject.dtos.contracts;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettridao.mockproject.models.ContractBuy;
import viettridao.mockproject.services.imp.FormatterService;

/**
 * ContractBuyDTO
 * Version: 1.0
 * Date: 5/28/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/28/2024 kiet-kun-afk Create
 * 5/30/2024 kiet-kun-afk Update
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractBuyDTO {

    @JsonIgnore
    private FormatterService formatter = new FormatterService();

    private Long id;

    private Long buyerId;

    private Long staffId;

    @Min(value = 1, message = "Property type ID is invalid")
    private Long propertyTypeId;

    @Min(value = 1, message = "Property type ID is invalid")
    private Long desiredLocationId;

    @Min(value = 1, message = "Desired price must be > 0")
    private Double desiredPrice;

    @Min(value = 1, message = "Deposit must be > 0")
    private Integer desiredArea;

    private String otherRequests;

    private String startDate;

    private String endDate;

    @Positive
    private Double brokerageFee;

    @Positive
    private Integer progress;

    private Boolean deleted;

    @JsonIgnore
    private MultipartFile attachmentFile;

    private String attachment;

    public ContractBuyDTO(ContractBuy contract) {
        this.id = contract.getId();
        this.buyerId = contract.getBuyer().getId();
        this.staffId = contract.getStaff().getId();
        this.propertyTypeId = contract.getPropertyType().getId();
        this.desiredLocationId = contract.getDesiredLocation().getId();
        this.desiredPrice = contract.getDesiredPrice();
        this.desiredArea = contract.getDesiredArea();
        this.otherRequests = contract.getOtherRequests();
        this.startDate = formatter.dateTimeToString(contract.getStartDate());
        this.endDate = formatter.dateTimeToString(contract.getEndDate());
        this.brokerageFee = contract.getBrokerageFee();
        this.progress = contract.getProgress();
        this.deleted = contract.getDeleted();
        this.attachment = contract.getAttachment();
    }

    public static ContractBuyDTO fromContract(ContractBuy contract) {
        return new ContractBuyDTO(contract);
    }
}
