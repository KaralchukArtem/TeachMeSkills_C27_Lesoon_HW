package lesson44.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardModel {
    private int id;
    private String cardNumber;
    private int cardBalance;
}
