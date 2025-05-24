package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.*;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Finds users whose email addresses contain the specified string (case-insensitive).
     * Filters the complete user list and returns matching results.
     *
     * @param email The substring to search for in user email addresses
     * @return List of users with matching email addresses
     */
    default List<User> findByEmailContainingIgnoreCase(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                .toList();
    }

    /**
     * Retrieves users born before the specified date.
     * Returns an empty list if no users match the criteria.
     *
     * @param date The reference date to compare user birthdates against
     * @return List of users with birthdates before the given date
     */
    default List<User> findUsersOlderThan(LocalDate date){
        List<User> usersOlderThan;
        usersOlderThan = findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(date))
                .toList();
        if(usersOlderThan.isEmpty()){
            return Collections.emptyList();
        }
        return usersOlderThan;
    }
}
