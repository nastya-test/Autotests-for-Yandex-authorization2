package org.example;
import PageObject.LoginPage;
import PageObject.ProfilePage;
import PageObject.RegistrationPage;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.apache.commons.lang3.RandomStringUtils;
import java.io.IOException;
import java.math.*;
import java.util.Random;
import com.codeborne.selenide.junit.ScreenShooter;

@RunWith(DataProviderRunner.class)
public class LoginTest extends BaseTest {

    @Test
    @Description ("Вход с правильным логином и паролем + соответствие")
    public void oldLoginTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        ProfilePage profilePage = new ProfilePage(getDriver());
        //вводим логин
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //вводим пароль
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //получаем отображаемый логин
        String user = profilePage.getUserLogin();
        //и сравниваем его с логином из файла настроек
        Assert.assertEquals(ConfProperties.getProperty("login"), user); }


        @Flaky //Пометка, что класс не стабильный
    @Test
    @Epic("Сообщения об ошибках")
        @Description ("Несуществующий логин русскими буквами")
        @Issue ("Id 10") //линковать автотесты с заведенными Issue
        @Link(name = "Ссылка на страницу авторизации", url = "https://passport.yandex.ru/auth/add") //Прикрепление ссылки
        @Owner(value = "Настя")
        @Severity(value = SeverityLevel.NORMAL)
    public void errorMessageRuLogin() throws IOException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLoginField("РусскийЛогин").errorLoginRu();
    }

    @Epic("Сообщения об ошибках")
    @Test
    @Description ("Несуществующий логин нерусскими буквами")
    public void errorMessageEngLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLoginField("EnglishLoginNeZaregan").errorLoginEng();
    }

    @Epic("Сообщения об ошибках")
    @Test
    @Description ("Некорректный пароль")
    public void errorMessagePassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLoginField("decorepanno").fillPasswordField("123456").errorPassword();
    }

    @Test
    @Description ("Переход по кнопке Назад")
    public void previousStepBtn() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.PreviousStepButton("decorepanno");
    }

    @Test
    @Description ("Выход из профиля")
    public void exit() {
        ProfilePage profilePage = new ProfilePage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLoginField("decorepanno").fillPasswordField("DecorPann");
        profilePage.Exit();
    }

    @DataProvider
    public static Object[][] engLoginProvider() {
        return new Object[][]{
                {"badloginlalalalal"},
                {"SecondBadLoginjidjif"}
                //You can put as many parameters as you want
        };
    }
    @Test
    @UseDataProvider("engLoginProvider")
    @Description("Несуществующий логин нерусскими буквами используя DataProvider")
    public void authTest1(String ruLogin)  {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLoginField(ruLogin).errorLoginEng();
    }

}

