package lesson43.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class StudentModel {
    @NotEmpty
    @NotBlank(message = "Field is empty!")
    @Size(min = 2, message = "Field value must be at least 2 characters long.")
    private String name;
    @NotEmpty
    @NotBlank(message = "Field is empty!")
    @Size(min = 2, message = "Field value must be at least 2 characters long.")
    private String surname;
    @NotEmpty
    @NotBlank(message = "Field is empty!")
    private String group;
    @Min(value = 1, message = "Value is invalid. Min age: 1.")
    @NotNull
    private int age;
    private int id;

    public StudentModel(String name, String surname, String group, int age, int id) {
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.age = age;
        this.id = id;
    }
}
