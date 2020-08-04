package tatoc;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;

public class Tat {
	

	public static void main(String[] args) throws InterruptedException {
		
			
		   
		    
		    	System.setProperty("webdriver.chrome.driver","C:\\driver\\chromedriver_win32\\chromedriver.exe");
		    	WebDriver driver=new ChromeDriver();
		    //Launch Tatoc Website
		    	driver.get("http://10.0.1.86/tatoc");
		     
				

			//Maximize the Window Size
		
			     driver.manage().window().maximize();
			    
			//Click on basic course
		  
			
				    WebElement basic = driver.findElement(By.xpath("//a[text()='Basic Course']"));
				    basic.click();
				
			  
			  //Click on green
			  
			
				    WebElement green = driver.findElement(By.xpath("//div[@class='greenbox']"));
			    	green.click();
			   
			
				   
			   //Change color of box
				  
				    driver.switchTo().defaultContent();
				    driver.switchTo().frame("main");
				    WebElement Box1=driver.findElement(By.xpath("//div[@id='answer' and text()='Box 1']"));
				    String Box1color=Box1.getAttribute("class");
			   
				    driver.switchTo().frame("child");
				    WebElement Box2=driver.findElement(By.xpath("//div[@id='answer' and text()='Box 2']"));
				    String Box2color=Box2.getAttribute("class");
				    
				    
				    
			         while(!(Box1color.equals(Box2color)))
			         {
			        	   
			           
			        	   
				   
			   
			        	   	driver.switchTo().defaultContent();
			        	   	driver.switchTo().frame("main");
			        	   	driver.findElement(By.linkText("Repaint Box 2")).click();
				   
			        	   	driver.switchTo().defaultContent();
			        	   	driver.switchTo().frame("main");
			        	   	driver.switchTo().frame("child");
			        	   	Box2=driver.findElement(By.id("answer"));
			        	   	Box2color=Box2.getAttribute("class");
			           }
				   
				  
			   //Drag And Drop and proceed next page
			   
			           driver.switchTo().defaultContent();
			           driver.switchTo().frame("main");
			           driver.findElement(By.linkText("Proceed")).click();
			  
			           WebElement drag = driver.findElement(By.id("dragbox"));
			           WebElement drop = driver.findElement(By.id("dropbox"));
			           Actions act = new Actions(driver);
			           act.dragAndDrop(drag,drop).build().perform();
				       driver.findElement(By.linkText("Proceed")).click();
				   	   
				   
				   //Test6:Adding name to Popup Window,submit and proceed in next page

				       String MainWindow = driver.getWindowHandle();
				       driver.findElement(By.linkText("Launch Popup Window")).click();
				       Set<String> s1= driver.getWindowHandles();
				       Iterator<String> i1 = s1.iterator();
				       while(i1.hasNext())
				       {
				  
					      String ChildWindow = i1.next();
					      if(!(MainWindow.equalsIgnoreCase(ChildWindow)))
					      {
					      driver.switchTo().window(ChildWindow);
					      driver.findElement(By.id("name")).sendKeys("Megha Dixit");
					      driver.findElement(By.id("submit")).click();
					      }
				       }
				       driver.switchTo().window(MainWindow);
				       driver.findElement(By.linkText("Proceed")).click();
				     
				       
				  //Creation of cookie and proceed in next page
				       
				       driver.findElement(By.linkText("Generate Token")).click();
				       String tokentext = driver.findElement(By.id("token")).getText();
				       
				       String [] value=tokentext.split(":");
				       driver.manage().addCookie(new Cookie("Token", value[1].trim()));
				       driver.findElement(By.linkText("Proceed")).click();
				       Thread.sleep(1000);
				       
				  //Check that we have the reached the last page
				       
				       String finalPage = driver.findElement(By.xpath("//span[@class='finish']")).getText();
				       if(finalPage.equalsIgnoreCase("Obastacle course is Complete"))
				       {
				    	   System.out.println("Successfully Automate Tatoc");
				    	   
				       }
				       Thread.sleep(2000);
				       driver.quit();
				       
				  
				  
				  
			  }
			   
			   
				   
			   
		


			 
			   
			   






	}


