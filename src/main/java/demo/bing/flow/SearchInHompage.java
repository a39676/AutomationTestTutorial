package demo.bing.flow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bing.pojo.pe.HomePage;
import demo.bing.pojo.pe.ResultPage;
import demo.webDriver.CreateFireFoxWebDriver;

@Service
public class SearchInHompage {

	@Autowired
	private CreateFireFoxWebDriver fireFoxDriver;

	public void searchInHomepage(String keyword) {
		WebDriver driver = fireFoxDriver.buildFireFoxWebDriver();

		try {
			keywordSearchInHomepage(driver, keyword);
			checkResult(driver, keyword);

		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.quit();
		return;
	}

	private void keywordSearchInHomepage(WebDriver driver, String keyword) {

		String mainUrl = "https://cn.bing.com/?FORM=BEHPTB";

		try {
			driver.get(mainUrl);
		} catch (TimeoutException e) {
			System.err.println("timeout exception");
			return;
		}

		WebElement keywordInput = driver.findElement(By.xpath(HomePage.keywordInput));
		keywordInput.click();
		keywordInput.clear();
		keywordInput.sendKeys(keyword);

		WebElement searchButton = driver.findElement(By.xpath(HomePage.searchButton));
		searchButton.click();

		return;
	}

	private void checkResult(WebDriver driver, String keyword) {
		try {
			WebElement resultListOL = null;
			try {
				resultListOL = driver.findElement(By.xpath(ResultPage.resultListOL));
			} catch (Exception e) {
				System.err.println("Can NOT detected search result");
				throw new Exception();
			}

			if (!resultListOL.getText().contains(keyword)) {
				System.err.println("Result NOT contain keyword: " + keyword);
			} else {
				List<WebElement> resultListLi = driver.findElements(By.xpath(ResultPage.resultListLi));

				for (int i = 0; i < resultListLi.size(); i++) {
					if (resultListLi.get(i).getText().contains(keyword)) {
						System.out.println("Find keyword in index:" + (i + 1));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return;

	}
}
