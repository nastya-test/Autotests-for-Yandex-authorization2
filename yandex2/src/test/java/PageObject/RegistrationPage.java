package PageObject;
import io.qameta.allure.Step;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RegistrationPage {
    private WebDriver driver;

    //Штука для браузера
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //Кнопка создать ID
    @FindBy(id = "passp:exp-register")
    private WebElement CreateAccount;

    //Поле Имя
    @FindBy(id = "firstname")
    private WebElement firstname;

    //Поле Фамилия
    @FindBy(css="[for='lastname']")
    private WebElement lastname;

    @FindBy(id = "lastname")
    private WebElement lastnamew;

    //Поле Логин
    @FindBy(css="[for='login']")
    private WebElement login;

    @FindBy(id = "login")
    private WebElement loginw;

    //Поле Придумайте пароль
    @FindBy(css="[for='password']")
    private WebElement password1;

    @FindBy(id = "password")
    private WebElement password1w;

    //Поле Повторите пароль
    @FindBy(css="[for='password_confirm']")
    private WebElement password2;

    @FindBy(id = "password_confirm")
    private WebElement password2w;

    //Поле Номер мобильного телефона
    @FindBy(css="[for='phone']")
    private WebElement phone;

    @FindBy(id = "phone")
    private WebElement phonew;

    //Чек бокс условия
    @FindBy(id = "eula_accepted")
    private WebElement chek1;

    //Чек бокс рассылка
    @FindBy(id = "keep_unsubscribed")
    private WebElement chek2;

    //Кнопка Зарегистрироваться
    @FindBy(className = "Button2_type_submit")
    private WebElement RegistrationBtn;

    //Метод для клика
    public RegistrationPage clickField(WebElement element) {
        element.click();
        return this;
    };

    //Метод для заполнения текстом
    private RegistrationPage fillField(WebElement element, String text) {
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

    @Step ("Кнопка создать")
    public RegistrationPage clickCreateBtn() {
        clickField(CreateAccount);
        return this;
    }

    @Step ("Ввод имени")
    public RegistrationPage inputFirstname(String text) {
        clickField(firstname);
        fillField(firstname, text);
        return this;
    }

    @Step ("Ввод фамилии")
    public RegistrationPage inputLastname(String text){
        clickField(lastname);
        fillField(lastnamew, text);
        return this;
    }

    @Step ("Ввод логина")
    public RegistrationPage inputLogin(String text){
        clickField(login);
        fillField(loginw, text);
        return this;
    }

    @Step ("Ввод пароля")
    public RegistrationPage inputPassword(String text){
        clickField(password1);
        fillField(password1w, text);
        return this;
    }

    @Step ("Ввод повторного пароля")
    public RegistrationPage inputSecondPassword(String text){
        clickField(password2);
        fillField(password2w, text);
        return this;
    }

    @Step ("Ввод телефона")
    public RegistrationPage inputPhone(String text){
        clickField(phone);
        fillField(phonew, text);
        return this;
    }

    @Step ("Простановка чек боксов + кнопка зарегистрироваться")
    public RegistrationPage finalRegistration() {
        //clickField(chek1);
        clickField(chek2);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickField(RegistrationBtn);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;

    }

}
