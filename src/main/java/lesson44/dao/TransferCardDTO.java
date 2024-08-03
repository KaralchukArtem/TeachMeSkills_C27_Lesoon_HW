package lesson44.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class TransferCardDTO {
    private int clientId;
    private String cardTo;
    private String cardFrom;
    private BigDecimal amount;
}
