package lesson44.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardModel {
    int id;
    String cardNumber;
    int cardBalance;
}
