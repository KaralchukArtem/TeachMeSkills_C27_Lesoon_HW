package lesson44.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientModel {
    private int id;
    private String name;
    private List<CardModel> cards;
}
