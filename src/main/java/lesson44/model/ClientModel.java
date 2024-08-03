package lesson44.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "customer client entity")
public class ClientModel  {
    private int id;
    private String name;
    @Schema(description = "customer cards object")
    private List<CardModel> cards;
}
