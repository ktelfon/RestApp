package lv.home.task.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Entity(name = "simpleUser")
public class User {
    @Id
    @GeneratedValue
    private Integer userId;
    private String firstName;
    private String secondName;
    private String email;
}
