package org.example;

import PageObject.RegistrationPage;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import java.util.Random;

public class RegistrationTest extends BaseTest {
    @Test
    @Description("Регистрация нового пользователя")
    public void registration() {
        RegistrationPage registrationPage = new RegistrationPage(getDriver());

        //Генерация переменных
        String name = RandomStringUtils.random(8, true, true);
        String surname = RandomStringUtils.random(8, true, true);
        String login = RandomStringUtils.random(8, true, true);
        String password = RandomStringUtils.random(8, true, true);

        //Генерация номера телефона
        String s = "0123456789";
        StringBuffer phoneNumber = new StringBuffer("999");
        int PHONE_NUMBER_LENGTH = 7;
        for (int i = 0; i < PHONE_NUMBER_LENGTH; i++) {
            phoneNumber.append(s.charAt(new Random().nextInt(s.length())));
        }

        registrationPage
                .clickCreateBtn().inputFirstname(name).inputLastname(surname).inputLogin(login).inputPassword(password).inputSecondPassword(password).inputPhone(phoneNumber.toString()).finalRegistration();
    }
}
