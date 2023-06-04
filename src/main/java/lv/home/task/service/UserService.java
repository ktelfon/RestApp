package lv.home.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lv.home.task.dto.UserDto;
import lv.home.task.mapper.UserMapper;
import lv.home.task.model.User;
import lv.home.task.repository.UserRepo;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserDto save(UserDto userDto) {
        User user = userMapper.fromDto(userDto);
        log.info("Saving user : {} {}", user.getEmail(), user.getSecondName());
        user = userRepo.save(user);
        return userMapper.toDto(user);
    }

    public UserDto getById(Long id) {
        return userMapper.toDto(userRepo.findById(id).orElseThrow(() -> {
            log.error("No user with id: {}", id);
            return new RuntimeException("No user with id:" + id);
        }));
    }
}
