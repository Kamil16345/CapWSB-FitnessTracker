package pl.wsb.fitnesstracker.user.api;


import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);

    void deleteById(Long id);

    List<User> findByEmailContainingIgnoreCase(String email);

    void updateById(Long id, pl.wsb.fitnesstracker.user.internal.UserDto userDto);

}
