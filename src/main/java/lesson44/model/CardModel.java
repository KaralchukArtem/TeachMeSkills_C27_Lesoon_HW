package lesson44.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "customer card entity")
public class CardModel {
    private int id;
    private String cardNumber;
    private int cardBalance;
}
