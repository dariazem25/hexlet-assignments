package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;
import io.ebean.Transaction;

import exercise.domain.User;
import exercise.domain.query.QUser;

class AppTest {

    private static Javalin app;
    private static String baseUrl;
    private static Transaction transaction;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start();
        var port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }
    // END

    // Хорошей практикой является запуск тестов с базой данных внутри транзакции.
    // Перед каждым тестом транзакция открывается,
    @BeforeEach
    void beforeEach() {
        transaction = DB.beginTransaction();
    }

    // А после окончания каждого теста транзакция откатывается
    // Таким образом после каждого теста база данных возвращается в исходное состояние,
    // каким оно было перед началом транзакции.
    // Благодаря этому тесты не влияют друг на друга
    @AfterEach
    void afterEach() {
        transaction.rollback();
    }

    @Test
    void testRoot() {
        HttpResponse<String> response = Unirest.get(baseUrl).asString();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
                .get(baseUrl + "/users")
                .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что на станице есть определенный текст
        assertThat(content).contains("Wendell Legros");
        assertThat(content).contains("Larry Powlowski");
    }

    @Test
    void testUser() {

        HttpResponse<String> response = Unirest
                .get(baseUrl + "/users/5")
                .asString();
        String content = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(content).contains("Rolando Larson");
        assertThat(content).contains("galen.hickle@yahoo.com");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
                .get(baseUrl + "/users/new")
                .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testCreateValidUser() {
        String firstName = "Ivan";
        String lastName = "Ivanov";
        String email = "ivanov@mail.ru";
        String password = "1234";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(response.getStatus()).isEqualTo(302);
        User actualUser = new QUser()
                .lastName.equalTo(lastName)
                .findOne();

        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getFirstName()).isEqualTo(firstName);
        assertThat(actualUser.getLastName()).isEqualTo(lastName);
        assertThat(actualUser.getEmail()).isEqualTo(email);
        assertThat(actualUser.getPassword()).isEqualTo(password);
    }

    @Test
    void testCreateUserInvalidFirstName() {
        String firstName = "";
        String lastName = "Ivanov";
        String email = "ivanov@mail.ru";
        String password = "1234";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);
        User actualUser = new QUser()
                .lastName.equalTo(lastName)
                .findOne();

        assertThat(actualUser).isNull();
        String content = response.getBody();
        assertThat(content).contains(lastName);
        assertThat(content).contains(email);
        assertThat(content).contains(password);
        assertThat(content).contains("Имя не должно быть пустым");
    }

    @Test
    void testCreateUserInvalidLastName() {
        String firstName = "Ivan";
        String lastName = "";
        String email = "ivanov@mail.ru";
        String password = "1234";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);
        User actualUser = new QUser()
                .lastName.equalTo(lastName)
                .findOne();

        assertThat(actualUser).isNull();
        String content = response.getBody();
        assertThat(content).contains(firstName);
        assertThat(content).contains(email);
        assertThat(content).contains(password);
        assertThat(content).contains("Фамилия не должна быть пустой");
    }

    @Test
    void testCreateUserInvalidEmail() {
        String firstName = "Ivan";
        String lastName = "Ivanov";
        String email = "ivanov@mail";
        String password = "1234";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);
        User actualUser = new QUser()
                .lastName.equalTo(lastName)
                .findOne();

        assertThat(actualUser).isNull();
        String content = response.getBody();
        assertThat(content).contains(firstName);
        assertThat(content).contains(lastName);
        assertThat(content).contains(password);
        assertThat(content).contains("Должно быть валидным email");
    }

    @Test
    void testCreateUserInvalidPasswordLength() {
        String firstName = "Ivan";
        String lastName = "Ivanov";
        String email = "ivanov@mail.ru";
        String password = "123";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);
        User actualUser = new QUser()
                .lastName.equalTo(lastName)
                .findOne();

        assertThat(actualUser).isNull();
        String content = response.getBody();
        assertThat(content).contains(firstName);
        assertThat(content).contains(lastName);
        assertThat(content).contains("Пароль должен содержать не менее 4 символов");
    }

    @Test
    void testCreateUserInvalidAllFields() {
        String email = "ivanov@mail";
        String password = "123";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "")
                .field("lastName", "")
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);
        User actualUser = new QUser()
                .email.equalTo(email)
                .findOne();

        assertThat(actualUser).isNull();
        String content = response.getBody();
        assertThat(content).contains(email);
        assertThat(content).contains(password);
        assertThat(content).contains("Имя не должно быть пустым");
        assertThat(content).contains("Фамилия не должна быть пустой");
        assertThat(content).contains("Должно быть валидным email");
        assertThat(content).contains("Пароль должен содержать не менее 4 символов");
    }
    // END
}
