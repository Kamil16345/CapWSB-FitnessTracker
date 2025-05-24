package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    @ResponseStatus(HttpStatus.OK)
    public List<UserSimpleDto> getAllUsersBasic() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUser(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return userMapper.toDto(user.get());
    }

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public List<UserIdAndEmailDto> getUserByEmail(@RequestParam String email) {
        List<User> users = userService.findByEmailContainingIgnoreCase(email);
        if (users.isEmpty()) {
            throw new UserNotFoundException("User with email containing: " + email + " was not found");
        }
        return userMapper.toIdAndEmailDto(users);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User savedUser = userService.createUser(userMapper.toEntity(userDto));
        return userMapper.toDto(savedUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userService.updateById(id, userDto);
    }

//    @GetMapping
//    public List<UserDto> getAllUsersOlderThan(@PathVariable LocalDate date){
//
//    }

}