package lesson44.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientModel {
    int id;
    String name;
    List<CardModel> cards;
}
