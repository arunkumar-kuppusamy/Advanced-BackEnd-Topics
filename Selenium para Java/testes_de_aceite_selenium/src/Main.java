
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * Selenium Standalone Server 3.13.0 - https://goo.gl/4g538W
 * Selenium Client & WebDriver Language Bindings, Java 3.13.0 - https://goo.gl/QaHpb8
 */
public class Main {

	private static WebDriver driver;
	private static String URL = "file:///"+new File("").getAbsolutePath();
	
	@Before
	public void inicializa() {
					
		//URL nao eh do navegador nativo e sim de um exe driver que roda o navegar num servidor localhost
		//Baixe o webdriver que tenha suporte ao navegador que vc tem instaldo no Sistema Operacional 
		//http://chromedriver.chromium.org/
		final String locationDriver = "resources\\chromedriver_65a68.exe";
		System.setProperty("webdriver.chrome.driver", locationDriver);
		driver = new ChromeDriver();//Versão 67.0.3396.87 / chromedriver_65a68.exe
		driver.get(URL+"/resources/html/a.html");
	}

	@After
	public void fechaNavegador() {
		/*try { Thread.sleep(5 * 1000); } catch (InterruptedException e) {}*/
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//if (driver != null)
			//driver.quit();
	}
	
	@Test
	public void testeRadioButton() {

		System.out.println(new File("").getAbsolutePath());
		
		Random randomNum = new Random();
		int showMe = 0 + randomNum.nextInt(9);
		List<WebElement> radioButton = driver.findElements(By.name("inlineRadioOptions"));
		radioButton.get(showMe).click();
		
		boolean bValue = radioButton.get(showMe).isSelected();
		System.out.println("showMe:["+showMe+"] size:["+radioButton.size()+"] bValue:["+bValue+"]");

		WebElement oCheckBox = driver.findElement(By.cssSelector("#exampleFormControlSelect1 option[value='"+(showMe+1)+"']"));
		System.out.println("Value: "+oCheckBox.getAttribute("value"));
		//oCheckBox.click();
		Assert.assertTrue(oCheckBox.isSelected());

		TestaFormulario form = new TestaFormulario(driver, URL+"/resources/html/b.html");
		boolean ok = form.testaCepComZeroAEsquerda();
		Assert.assertTrue(ok);
	}

	
	
	

}
