import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginForm extends PageObject{

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

    public void enterUsername(String username){
        this.username.sendKeys(username);
    }

    public void enterPassword(String password){
        this.password.sendKeys(password);
    }

    public void pressLoginBtn(){
        this.btnLogin.click();
    }

    public String getLoginStatus(){
        return this.loginStatus.getText();
    }

}
