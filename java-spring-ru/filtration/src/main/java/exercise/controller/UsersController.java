package exercise.controller;

import exercise.model.User;
import exercise.model.QUser;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

// Зависимости для самостоятельной работы
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
//    @GetMapping("")
//    public Iterable<User> getUsersByFullName(@RequestParam(defaultValue = "") String firstName, @RequestParam(defaultValue = "") String lastName) {
//        return this.userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName).and(QUser.user.lastName.containsIgnoreCase(lastName)));
//    }

    @GetMapping("")
    public Iterable<User> getUsersByFullName(@QuerydslPredicate(root = User.class) Predicate predicate) {
        return userRepository.findAll(predicate);
    }
    // END
}

