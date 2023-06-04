package lv.home.task.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    private Integer id;
    private String firstName;
    private String secondName;
    private String email;
}
