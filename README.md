# Selenium WebDriver: Small Example Project
This was a project for the _Verification and Validation of Software_ (_Verificación y Validación de Software_) lecture at the _Universidad Nacional del Sur_ in Argentina.

## Goal
The main goal was to automate the **Functional Testing** on the following _toy_ website, developed by the teachers of the lecture: [_Obra Social de los Trabajadores de Hogwarts_](https://cs.uns.edu.ar/~mll/temp/testing/examen/login.html).

## A little about the *target* website

This website consists of a small self-management system for the health insurance of the workers of Hogwarts (yes, from Harry Potter).

Here, workers should be able to: login, arrange a medical appointment, request a refund for the purchase of medicines, modify their profile, etc.

The only two views available for testing are: the login system and the request for refund form.

## Specification
Since *Testing* is based on comparing what is observed to what is expected, let's take a look at the test cases given to us (students) for this task:

- Trying to log-in without username nor password should return a message saying *"Atención: Debe ingresar un nombre de usuario"* (*Warning: You should write a username and a password*)


- Trying to log-in using only a username (with no password) should return a message saying *"Atención: El password no puede estar vacío"* (*Warning: Password should not be empty*)


- Trying to log-in with the username **dumbridge** and the password **tomriddle** should result in a successful login and the user should be redirected to a view where the message *"Bienvenido a OSTH On-Line"* (*Welcome to OSTH On-Line*) is shown.


- Trying to log-in with the username **dumbridge** and an incorrect password should return a message saying *"Atención: El password ingresado no es válido"* (*Warning: the given pasword is not valid*).

## Implementation
### Tools
- [Selenium WebDriver](https://www.selenium.dev/documentation/webdriver/): Provides an API to access the elements of the target website.
- JUnit: for implementing the tests.

### POM Design Pattern
From the lecture we were encouraged to make use of this pattern, where **each page (or view) of the website** corresponds to **a class** where we define methods to access its elements.

As with many design patterns, goals are: 
- Readability 
- Easy maintenance: changes in the website's view imply changes to only the class that represents it.
- Code reusability: e.g. using the same classes for testing with TestNG instead of JUnit.

#### Example: Login Form

```java
public class LoginForm extends PageObject{
    
    /* Here we get the elements of the webpage,
     * using WebDriver. */
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

    //Here we define methods to access them
    //Writing in the login fields
    public void enterUsername(String username){
        this.username.sendKeys(username);
    }

    public void enterPassword(String password){
        this.password.sendKeys(password);
    }

    public void pressLoginBtn(){
        this.btnLogin.click();
    }

    //Or getting the login-result message
    public String getLoginStatus(){
        return this.loginStatus.getText();
    }

}
```

These methods are then directly used by the test class, for example in the following testcase (testing the login form with both a valid username and password):

```java
    @Test
    public void LoginSuccessfulTest(){
        driver.get(Utils.BASE_URL);

        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername(USERNAME); // <---
        loginForm.enterPassword(CORRECT_PASSWORD); // <---
        loginForm.pressLoginBtn(); // <---

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        InicioPage inicioPage = new InicioPage(driver);
        Assertions.assertTrue(inicioPage.isThereAWelcomeMessage());
    }
```