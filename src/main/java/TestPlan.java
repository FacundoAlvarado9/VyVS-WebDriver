import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class TestPlan {
    private static final WebDriver driver = new FirefoxDriver();

    @BeforeAll
    public static void start() {
        System.setProperty("webdriver.gecko.driver", Utils.GECKO_DRIVER_LOCATION);
    }

    @AfterAll
    public static void end(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.quit();
    }

    @Test
    public void NoUsernameNorPasswordTest(){
        driver.get(Utils.BASE_URL);

        LoginForm loginForm = new LoginForm(driver);
        loginForm.pressLoginBtn();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assertions.assertEquals("Atención: Debe ingresar un nombre de usuario", loginForm.getLoginStatus());
    }

    @Test
    public void UsernameButNoPasswordTest(){
        driver.get(Utils.BASE_URL);

        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername();
        loginForm.pressLoginBtn();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assertions.assertEquals("Atención: El password no puede estar vacío", loginForm.getLoginStatus());
    }

    @Test
    public void LoginSuccessfulTest(){
        driver.get(Utils.BASE_URL);

        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername();
        loginForm.enterCorrectPassword();
        loginForm.pressLoginBtn();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        InicioPage inicioPage = new InicioPage(driver);
        Assertions.assertTrue(inicioPage.isThereAWelcomeMessage());
    }

    @Test
    public void WrongPasswordTest(){
        driver.get(Utils.BASE_URL);

        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername();
        loginForm.enterWrongPassword();
        loginForm.pressLoginBtn();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assertions.assertEquals("Atención: El password ingresado no es válido", loginForm.getLoginStatus());
    }

}
