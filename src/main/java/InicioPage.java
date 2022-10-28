import org.openqa.selenium.WebDriver;

public class InicioPage extends PageObject{

    public InicioPage(WebDriver driver) {
        super(driver);
    }

    public boolean isThereAWelcomeMessage(){
        return driver.getPageSource().contains("Bienvenido a OSTH On-Line");
    }
}
