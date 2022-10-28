import org.openqa.selenium.WebDriver;

public class InicioPage extends PageObject{

    public InicioPage(WebDriver driver) {
        super(driver);
    }

    public boolean isThereAWelcomeMessage(){
        //¿se encuentra el mensaje "..." en la página?
        return driver.getPageSource().contains("Bienvenido a OSTH On-Line");
    }
}
