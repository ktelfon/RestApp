package lv.home.task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lv.home.task.dto.UserDto;
import lv.home.task.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserRestController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable String id) {
        log.info("Request to get by id user: {}", id);
        return ResponseEntity.ok(userService.getById(Integer.valueOf(id)));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody UserDto userDto) {
        log.info("Request to save user: {}, {}", userDto.getEmail(), userDto.getSecondName());
        return Optional.of(userService.save(userDto))
                .map(u -> ResponseEntity.created(
                        UriComponentsBuilder
                                .fromUri(URI.create(""))
                                .pathSegment(u.getId().toString())
                                .build().toUri()).build())
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable(name = "id") String id, @RequestBody UserDto userDto) {
        log.info("Request to update user: {}, {}", id, userDto.getEmail());
        return ResponseEntity.ok(userService.update(Integer.valueOf(id), userDto));
    }
}
