import graphql.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static HarryPotterPage harryPotterPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver",  "C:\\Users\\gmalyshev\\IdeaProjects\\TestTaskSimbirSoft\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        harryPotterPage = new HarryPotterPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5)); //Взято произвольное время на ожидание загрузки страницы - 5 сек
        String login_url = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";
        driver.get(login_url);
    }


    @Test
    public void depositAndWithdrawalAndCheckBalanceTest() {
        loginPage.logInAsCustomer();
        profilePage.chooseHarryPotterUserFromDropDown();
        profilePage.logInAsHarryPotterClickButton();
        harryPotterPage.clickDepositFibonacciMoney();
        harryPotterPage.withdrawFibonacciMoney();

        WebElement balanceElement = driver.findElement(By.xpath("//div/strong[text()][2]"));

        /** Проверка на то, что после вычитания баланс = 0
         * */
        Integer actualBalance = Integer.parseInt(balanceElement.getText());
        Integer expectedBalance = 0;

        Assert.assertTrue(actualBalance == expectedBalance);
    }

    @Test
    public void debitAndCreditInfoInChartTest() {
        loginPage.logInAsCustomer();
        profilePage.chooseHarryPotterUserFromDropDown();
        profilePage.logInAsHarryPotterClickButton();
        harryPotterPage.clickDepositFibonacciMoney();
        harryPotterPage.withdrawFibonacciMoney();
        harryPotterPage.transactionButton();

        /**
         * Тут дальше идет создание новых объектов WebElement, т.к. в классе PageObject для Гарри Поттера
         * я не инициализировал отдельно эти элементы через @FindBy. Счита, это не рационально, но у меня есть вопросы,
         * как это сделать лучше прям в классе страницы.
         */
        WebElement debitInfoInChart = driver.findElement(By.xpath("//tr[@id = 'anchor1']/td[3]"));
        String debitInfo = debitInfoInChart.getText(); //Инфа о депозите
        WebElement creditInfoInChart = driver.findElement(By.xpath("//tr[@id = 'anchor0']/td[3]"));
        String creditInfo = creditInfoInChart.getText();

        WebElement dateDebitInfo = driver.findElement(By.xpath("//tr[@id = 'anchor1']/td[1]"));
        String todayDate = String.valueOf(LocalDate.now().getDayOfMonth());
        WebElement dateCreditInfo = driver.findElement(By.xpath("//tr[@id = 'anchor0']/td[1]"));

        /** Проверка на то, что в таблице есть записи Credit + Debit и на то, что в этих же строчках есть сумма
         * из метода числа Фибоначчи.
         */
        Assert.assertTrue(debitInfoInChart.getText().equals(debitInfo));
        Assert.assertTrue(creditInfoInChart.getText().equals(creditInfo));
        Assert.assertTrue(dateDebitInfo.getText().contains(todayDate));
        Assert.assertTrue(dateCreditInfo.getText().contains(todayDate));

        TransactionsResultsForCSV.saveTests();
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}












