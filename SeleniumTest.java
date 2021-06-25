import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
	
	public static void main (String[] args) {
		
		WebDriver dri = new ChromeDriver();
		
		dri.get("https://google.com");
		
		
	}

}
