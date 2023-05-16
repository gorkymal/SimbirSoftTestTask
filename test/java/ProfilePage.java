import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "userSelect")
    private WebElement dropDownOfUsers;

    @FindBy (xpath = "//option[(text() = \"Harry Potter\")]")
    private WebElement userHarryPotter;

    public void chooseHarryPotterUserFromDropDown() {
        dropDownOfUsers.click();
        userHarryPotter.click();
    }

    public void logInAsHarryPotterClickButton() {

        //Вызываем явное ожидание до появления скрытой кнопки Login (взято условно 10 секунд)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type = \"submit\"]"))).click();
    }
}
