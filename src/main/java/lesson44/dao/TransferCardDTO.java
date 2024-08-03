package lesson44.dao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Schema(description = "customer TransferDTO entity")
public class TransferCardDTO {
    private int clientId;
    @Schema(description = "from where the transfer will be made")
    private String cardTo;
    @Schema(description = "where will the transfer be")
    private String cardFrom;
    private BigDecimal amount;
}
