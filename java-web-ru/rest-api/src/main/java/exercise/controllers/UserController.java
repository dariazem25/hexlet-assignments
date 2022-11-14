package exercise.controllers;

import io.javalin.core.validation.JavalinValidation;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.Validator;
import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;

import java.util.List;
import java.util.Map;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();
        String json = DB.json().toJson(users);
        ctx.json(json);
        // END
    }

    ;

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.equalTo(Long.parseLong(id))
                .findOne();
        String json = DB.json().toJson(user);
        ctx.json(json);
        // END
    }

    public void create(Context ctx) {

        // BEGIN
        User user = ctx.bodyValidator(User.class)
                .check(obj -> !obj.getFirstName().isEmpty(), "Имя не должно быть пустым")
                .check(obj -> !obj.getLastName().isEmpty(), "Фамилия не должна быть пустой")
                .check(obj -> EmailValidator.getInstance().isValid(obj.getEmail()), "Email должен быть валидным")
                .check(obj -> StringUtils.isNumeric(obj.getPassword()), "Пароль должен содержать только цифры")
                .check(obj -> obj.getPassword().length() >=4, "Пароль должен быть больше 4 символов")
                .get();
        user.save();
        // END
    }

    public void update(Context ctx, String id) {
        // BEGIN
        String body = ctx.body();
        User user = DB.json().toBean(User.class, body);
        user.setId(id);
        user.update();
        // END
    }


    public void delete(Context ctx, String id) {
        // BEGIN
        User user = new QUser()
                .id.equalTo(Long.parseLong(id))
                .findOne();
        user.delete();
        // END
    }
}
