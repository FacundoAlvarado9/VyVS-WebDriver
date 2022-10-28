import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginForm extends PageObject{

    private final String USERNAME = "dumbridge";
    private final String CORRECT_PASSWORD = "tomriddle";
    private final String INCORRECT_PASSWORD = "abc123";

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "/html/body/center/button[normalize-space(text()) = 'Ingresar']")
    private WebElement btnLogin;

    @FindBy(id = "estado")
    private WebElement loginStatus;

    public LoginForm(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(){
        this.username.sendKeys(USERNAME);
    }

    public void enterCorrectPassword(){
        this.password.sendKeys(CORRECT_PASSWORD);
    }

    public void enterWrongPassword(){
        this.password.sendKeys(INCORRECT_PASSWORD);
    }

    public void pressLoginBtn(){
        this.btnLogin.click();
    }

    public String getLoginStatus(){
        return this.loginStatus.getText();
    }

}
