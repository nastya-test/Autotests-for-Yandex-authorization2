package PageObject;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import junit.framework.Assert;
import org.example.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoginPage extends BaseTest {
    private WebDriver driver;

    //Локатор для поля логина
    @FindBy(id = "passp-field-login")
    private WebElement loginField;

    //Локатор кнопки входа в аккаунт
    @FindBy(id="passp:sign-in")
    private WebElement loginBtn;

    //Локатор для поля пароля
    @FindBy(id = "passp-field-passwd")
    private WebElement passwordField;

    //Локатор для сообщения об ошибке в логине
    @FindBy(id="field:input-login:hint")
    private WebElement errorLogin;

    //Локатор для сообщения об ошибке в пароле
    @FindBy(id="field:input-passwd:hint")
    private WebElement errorPassword;

    //Локатор для кнопки назад
    @FindBy(className = "PreviousStepButton")
    private WebElement PreviousStepBtn;

    //Штука для браузера
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("OLD Ввод логина")
    //Метод для ввода логина
    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    @Step("OLD Ввод пароля")
    //Метод для ввода пароля
    public void inputPasswd(String passwd) {
        passwordField.sendKeys(passwd); }

    @Step("OLD Клик по кнопке")
    public LoginPage clickLoginBtn() {
        loginBtn.click();
        return this;
    }

    //Метод для клика на элемент
    public LoginPage clickField(WebElement element) {
        element.click();
        return this;
    };

    //Метод для заполнения текстом
    private LoginPage fillField(WebElement element, String text) {
        element.sendKeys(text);
        return this;
    };

    //Метод для получения текста элемента
    private String getField(WebElement element) {
        return element.getText();
    };

    //Метод для получения значения элемента
    private String getValue(WebElement element) {
        return element.getAttribute("value");

    };

    //Для вложения
    @Attachment
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/test/resources", resourceName));
    }

    @Step ("Ввод логина")
    public LoginPage fillLoginField(String text) {
        clickField(loginField);
        fillField(loginField, text);
        clickField(loginBtn);
        return this;
    }

    @Step ("Ввод пароля")
    public LoginPage fillPasswordField(String text) {
        clickField(passwordField);
        fillField(passwordField, text);
        clickField(loginBtn);
        return this;
    }

    @Step ("Текст ошибки: Такой логин не подойдёт")
    public LoginPage errorLoginRu() throws IOException{
        String user = "Такой логин не подойдет";
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(user, getField(errorLogin));
        getBytes("picture.png");
        return this;

    }

    @Step ("Текст ошибки: Такого аккаунта нет")
    public LoginPage errorLoginEng() {
        String user = "Такого аккаунта нет";
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(user, getField(errorLogin));
        return this;
    }

    @Step ("Текст ошибки: Неправильный пароль")
    public LoginPage errorPassword() {
        String user = "Неверный пароль";
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(user, getField(errorPassword));
        return this;
    }

    @Step("Кнопка назад")
    public LoginPage PreviousStepButton(String text) {
        clickField(loginField);
        fillField(loginField, text);
        clickField(loginBtn);
        clickField(PreviousStepBtn);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(text, getValue(loginField));
        return this;
    }

}
