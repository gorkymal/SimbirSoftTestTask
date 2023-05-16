import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.function.Function;

public class HarryPotterPage {
    private WebDriver driver;

    public HarryPotterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath =  "//div/button[@ng-click = \"transactions()\"]")
    private WebElement transcationsButton;

    @FindBy (xpath = "//div/button[@ng-click = \"deposit()\"]")
    private WebElement depositButton;

    @FindBy (xpath = "//div/button[@ng-click = \"withdrawl()\"]")
    private WebElement withdrawalButton;

    @FindBy (xpath = "//input[@placeholder= \"amount\"]")
    private WebElement hiddenRow;

    @FindBy (xpath = "//form/button[@type = \"submit\"]")
    private WebElement hiddenDepositWithdrawButton;

    @FindBy (xpath = "//div/span[@ng-show = 'message']")
    private WebElement hiddenSuccessfulRow;

    @FindBy (xpath = "//div/strong[text()][2]")
    private WebElement balanceInfo;

    @FindBy (xpath = "//div/button[@ng-click = 'transactions()']")
    private WebElement transactionsButton;

    public int calculateFibonacci() {

        /**
         * В следующей строке нет + 1, т.к. в требованиях вы считаете не от 0 последовательность, а от 1.
         Соответственно, число рассчитано по вашим правилам
         */
        int i = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        int[] fib = new int[i + 1];
        fib[0] = 0;
        fib[1] = 1;

        for (int j = 2; j <= i; j++) {
            fib[j] = fib[j - 1] + fib[j - 2];
        }

        return fib[i];

    }


    public void clickDepositFibonacciMoney() {

        //Клик по кнопке Deposit
        depositButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //Ожидаем появление скрытой строки 5 сек
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder= \"amount\"]")));

        //Ожидаем появление скрытой кнопки Deposit 5 сек
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.valueOf("//form/button[@type = \"submit\"]"))));

        //Вставляем в появившуюся строку значение числа Фибоначчи из метода calculate Fibonacci
        hiddenRow.sendKeys(String.valueOf(calculateFibonacci()));

        //Кликаем по появившейся кнопке Deposit
        hiddenDepositWithdrawButton.click();

        //Ожидаем, пока не появится надпись "Deposit Successful"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.valueOf("//div/span[@ng-show = 'message']"))));

    }


    public void withdrawFibonacciMoney() {

        //Кликаем по кнопке Withdraw
        withdrawalButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //Ожидаем появления скрытой строки 5 сек
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.valueOf("//input[@placeholder= \"amount\"]"))));

        //Ожидаем появления скрытой кнопки Withdraw  сек
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.valueOf("//div/button[@ng-click = \"withdrawl()\"]"))));

        //Записываем в поле значение числа Фибоначчи
        hiddenRow.sendKeys(String.valueOf(calculateFibonacci()));

        //Кликаем по кнопке Withdraw
        hiddenDepositWithdrawButton.click();

        //Ожидаем 5 сек до появление надписи Transaction Successful
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.valueOf("//div/span[@ng-show = 'message']"))));

    }

    public void transactionButton() {
        transactionsButton.click();
    }



}
