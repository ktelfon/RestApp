package lv.home.task.mapper;

import lv.home.task.dto.UserDto;
import lv.home.task.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User fromDto(UserDto dto) {
        return User.builder()
                .userId(dto.getId())
                .firstName(dto.getFirstName())
                .secondName(dto.getSecondName())
                .email(dto.getEmail())
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getUserId())
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .email(user.getEmail())
                .build();
    }
}
