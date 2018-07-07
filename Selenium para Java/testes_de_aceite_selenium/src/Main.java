

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

		public static void main(String[] args) {
			inicializa();
			
			instrucoes();
			
			fecha(8);//em 20 segundos
		}
		
		private static void instrucoes() {
			
			driver.get("https://translate.google.com.br/");
			WebElement campoBY = driver.findElement(By.id("source"));
			campoBY.sendKeys("Testing if English to Portuguese has been chosen");
			//Testing if English to Portuguese has been chosen
			
			/*List<WebElement> campoCSS = driver.findElements(By.id("result_box"));//document.getElementById("result_box").querySelectorAll("span")[0].innerHTML;  
			String s = campoCSS.get(0).getText();*/
			
			boolean f = driver.getPageSource().contains("result_box");
			
			System.out.println("SAIDA: ["+f+"]");
			
			
			
		}
		
		private static void inicializa() {
			//URL nao eh do navegador nativo e sim de um exe driver que roda o navegar num servidor localhost
			//Baixe o webdriver que tenha suporte ao navegador que vc tem instaldo no Sistema Operacional 
			//http://chromedriver.chromium.org/
			final String locationDriver = "resources\\chromedriver_65a68.exe";
			System.setProperty("webdriver.chrome.driver", locationDriver);
			driver = new ChromeDriver();//Versão 67.0.3396.87 / chromedriver_65a68.exe
		}

		private static void fecha(int seg) {
			//Espera x segundos e fecha o navegador.
			try { Thread.sleep( seg   *1000 ); }
			catch (InterruptedException e) {}
			//-------------
			if(driver != null)
		        driver.quit();
			//fecha sem pedir
		}
	}
