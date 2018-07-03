package com.olypics0702project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Olypics2016Project0702 {

	static WebDriver driver; 
	String url = "https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table";
	@BeforeClass
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().fullscreen();
		//driver.manage().deleteAllCookies(); // delete all the cookies

		// dynamic wait
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
	}

	@Test
	public void testCase1() {
		List<WebElement> firstColumn = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		
		ArrayList<Integer> actualList = new ArrayList<>(); 
		for (int i = 0; i < firstColumn.size()-1; i++) {
			actualList.add(Integer.parseInt((firstColumn.get(i).getText())));
		}
		
		SortedSet<Integer> expectedList = new TreeSet<>(actualList);
		Assert.assertEquals(actualList, expectedList);
		//click on NOC
		driver.findElement(By.xpath("//th[@class='headerSort'][contains(text(),'NOC')]")).click();
		
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
		
		ArrayList<String> actualCountryList = new ArrayList<>();
		
		
		for (int i = 0; i < countries.size()-1; i++) {
			actualCountryList.add(countries.get(i).getText());
			//System.out.println(actualCountryList);
		}
		System.out.println(actualCountryList+" Arraylist");
		SortedSet<String> expectedCountryList = new TreeSet<>(actualCountryList);
		System.out.println(expectedCountryList+"Treeset");
		Assert.assertEquals(actualCountryList, expectedCountryList);
		
	}
	@Test(priority=2)
	public void testCase2() {
	Assert.assertTrue(mostGold().equals("46 - United States")); 
	Assert.assertTrue(mostSilver().equals("37 - United States")); 
	Assert.assertTrue(mostBronze().equals("38 - United States")); 
	Assert.assertTrue(total().equals("121 - United States")); 
	}
	
	public static String mostGold(){
		SortedMap<Integer, String> mp= new TreeMap<>(); 
		List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[2]")); 
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
		
		for(int i=0; i<medals.size(); i++){
			mp.put(Integer.parseInt(medals.get(i).getText()),countries.get(i).getText() ); 
			
		}
		
		return mp.lastKey()+ " - " +  mp.get(mp.lastKey()); 
	}
	public static String mostSilver(){
		SortedMap<Integer, String> mp= new TreeMap<>(); 
		List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]")); 
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
		
		for(int i=0; i<medals.size(); i++){
			mp.put(Integer.parseInt(medals.get(i).getText()),countries.get(i).getText() ); 
			
		}
		
		return mp.lastKey()+ " - " +  mp.get(mp.lastKey()); 
	}
	public static String mostBronze(){
		SortedMap<Integer, String> mp= new TreeMap<>(); 
		List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]")); 
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
		
		for(int i=0; i<medals.size(); i++){
			mp.put(Integer.parseInt(medals.get(i).getText()),countries.get(i).getText() ); 
			
		}
		
		return mp.lastKey()+ " - " +  mp.get(mp.lastKey()); 
	}
	
	public static String total(){
		SortedMap<Integer, String> mp= new TreeMap<>(); 
		List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[5]")); 
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
		
		for(int i=0; i<medals.size(); i++){
			mp.put(Integer.parseInt(medals.get(i).getText()),countries.get(i).getText() ); 
			
		}
		
		return mp.lastKey()+ " - " +  mp.get(mp.lastKey()); 
	}
	
	@Test(priority=3)
	public void countryByMedalTC3() {
		
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
		System.out.println(SilverMedal() + "tc3 results");
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(SilverMedal(), "China","France" );  // webtables class Murodil like Eygpt we find the same way
		
//		Assert.assertEquals(SilverMedal() ,  "China"); 
//		Assert.assertEquals(SilverMedal() ,  "France");
		
	}
	
	public static Set<String> SilverMedal(){
		//SortedMap<Integer, String> mp= new TreeMap<>(); 
		Set<String> set= new HashSet<>();
		
		List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]")); 
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
		
		for(int i=0; i<medals.size() -1 ; i++){
			if (Integer.parseInt(medals.get(i).getText()) == 18) {
				set.add(countries.get(i).getText());
			}
		}
		
		return set; 
	}
	
	@Test(priority=4)
	public void getIndex() {
		rowAndColumn("Japan");
		System.out.println(rowAndColumn("Japan"));
		Assert.assertEquals(rowAndColumn("Japan"), "6,2");
	}
		public String rowAndColumn(String country) {
			Map<String, Integer> map = new HashMap<>();
			List<WebElement> countryNames = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
			List<WebElement> data = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
			
			for (int i = 0; i < data.size()-1; i++) {
				map.put((countryNames.get(i).getText()), i);
		
			}	
		
			return map.get(country)+","+2;
		}
		
		@Test(priority=5)
		public void testCase5(){
			driver.get(url);
			System.out.println(getSum()); 

			List<String> ls= Arrays.asList("Australia", "Italy"); 
			Assert.assertEquals( getSum(),ls); 
		}
		
		public static List<String> getSum(){
			
			List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));
			List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
			HashMap<String,Integer> mp= new HashMap<>();
			HashMap<String,Integer> hmp= new HashMap<>();
			
			SortedSet<String> st= new TreeSet<>();
			for(int i=0; i<medals.size()-1; i++){
				mp.put(countries.get(i).getText(), Integer.parseInt(medals.get(i).getText()) );
			}
			for(int i=0; i<medals.size()-1; i++){
				hmp.put(countries.get(i).getText(), Integer.parseInt(medals.get(i).getText()) ); 
			}
			for(Entry <String,Integer> each: mp.entrySet()){
				for(Entry <String,Integer> other: hmp.entrySet()){
					
					if(!(each.getKey().equals(other.getKey()))&&(each.getValue()+other.getValue())==18){
						st.add(each.getKey());
						st.add(other.getKey());
					} 
					}
				}
				
			 
			
			List<String> ls= new ArrayList<>(st);
			
			return ls; 
		}


}
