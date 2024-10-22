// package viettridao.mockproject.configurations.annotations;

// import java.time.LocalDateTime;

// import jakarta.validation.ConstraintValidator;
// import jakarta.validation.ConstraintValidatorContext;
// import viettridao.mockproject.models.ContractBuy;

// public class EndDateAfterStartDateValidator implements
// ConstraintValidator<EndDateAfterStartDate, ContractBuy> {

// @Override
// public void initialize(EndDateAfterStartDate constraintAnnotation) {
// }

// @Override
// public boolean isValid(ContractBuy contractBuy, ConstraintValidatorContext
// context) {
// if (contractBuy == null) {
// return true; // or false if you want to consider null as invalid
// }

// // Obtain the start date from your entity
// // Assuming you have a way to get the start date; for example, through a
// static
// // method or context
// LocalDateTime startDate = contractBuy.getStartDate();
// LocalDateTime endDate = contractBuy.getEndDate();
// if (startDate == null || endDate == null) {
// return true; // or false if you want to consider null as invalid
// }

// return endDate.isAfter(startDate);
// }
// }
