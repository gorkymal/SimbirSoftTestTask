import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;

public class TransactionsResultsForCSV {

    /** Отчёт Allure не сгенерировал, т.к. у меня не запустились все тесты подряд из-за локальной IDEA.
     */

    private static WebDriver driver;

    public TransactionsResultsForCSV(WebDriver driver) throws IOException {
        this.driver = new ChromeDriver();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/listTx");
    }


    /** Получение 2ух строк с депозитом и withdrawal из таблицы Transactions */
    static String row1Date = driver.findElement(By.xpath("\"//tr[@id = 'anchor1']/td[1]\"")).getText();
    static String row1Money = driver.findElement(By.xpath("\"//tr[@id = 'anchor1']/td[2]\"")).getText();
    static String row1Type = driver.findElement(By.xpath("\"//tr[@id = 'anchor1']/td[3]\"")).getText();

    static String row2Date = driver.findElement(By.xpath("\"//tr[@id = 'anchor0']/td[1]\"")).getText();
    static String row2Money = driver.findElement(By.xpath("\"//tr[@id = 'anchor0']/td[2]\"")).getText();
    static String row2Type = driver.findElement(By.xpath("\"//tr[@id = 'anchor0']/td[3]\"")).getText();

    static String myCSVFile = "C:\\Users\\gmalyshev\\IdeaProjects\\LaLaLa\\myCSV.csv";

    public static void saveTests() {
        try (FileWriter writer = new FileWriter(myCSVFile)) {

            // Записываем заголовки
            try {
                writer.append("Date");
                writer.append(',');
                writer.append("Money");
                writer.append(',');
                writer.append("Type");
                writer.append('\n');
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Записываем значения row1
            try {
                writer.append(row1Date);
                writer.append(',');
                writer.append(row1Money);
                writer.append(',');
                writer.append(row1Type);
                writer.append('\n');
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Записываем значения row2
            try {
                writer.append(row2Date);
                writer.append(',');
                writer.append(row2Money);
                writer.append(',');
                writer.append(row2Type);
                writer.append('\n');
                writer.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


