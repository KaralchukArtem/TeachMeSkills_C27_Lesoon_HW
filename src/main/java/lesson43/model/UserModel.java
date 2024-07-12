package lesson43.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserModel {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String passport_number;
}
