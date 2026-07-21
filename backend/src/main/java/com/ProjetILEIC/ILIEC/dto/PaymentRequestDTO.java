package com.ProjetILEIC.ILIEC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    @NotNull(message = "Stagiaire ID reference is required")
    private Long stagiaireId;

    @NotNull(message = "Recorded by user ID reference is required")
    private Long recordedById;

    @NotNull(message = "Payment month is required")
    @Min(value = 1, message = "Month must be between 1 and 12")
    @Max(value = 12, message = "Month must be between 1 and 12")
    private Integer month;

    @NotNull(message = "Payment year is required")
    @Min(value = 2020, message = "Year must be valid")
    private Integer year;

    @NotNull(message = "Payment amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Payment date is required")
    @PastOrPresent(message = "Payment date cannot be in the future")
    private LocalDate paymentDate;

    @NotBlank(message = "Payment method is required") // e.g., CASH, VIREMENT, CHEQUE
    @Size(max = 50, message = "Payment method text is too long")
    private String paymentMethod;

    @NotBlank(message = "Payment status is required") // e.g., PAID, PENDING, FAILED
    @Size(max = 30, message = "Status text is too long")
    private String status;

    @Size(max = 100, message = "Reference must not exceed 100 characters")
    private String reference; // Left without @NotBlank because cash payments might not have a tracking ID!
}