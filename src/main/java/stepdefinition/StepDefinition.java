package stepdefinition;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepDefinition {
	WebDriver driver = new ChromeDriver();
	Properties properties;
	Session session;
	MimeMessage message;

	@Given("^i am in gmail inbox&")
	public void userIsOnGmail() {

		System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");

		driver.manage().window().maximize();

		driver.navigate().to("https://mail.google.com");
		properties = System.getProperties();

		properties.setProperty("mail.smtp.host", "localhost");

		session = Session.getDefaultInstance(properties);

		message = new MimeMessage(session);
	}

	@Then("^i click compose button$")
	public void clickCompose() {
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();

	}

	@Then("^i fill to field$")
	public void fillToField() {

		String to = "kewalkaroc@gmail.com";
		
		try {
		
			message.setFrom(new InternetAddress("kewalkarock@gmail.com"));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@Then("^i fill subject and body$")
	public void fillSubjectAndBody() {

		
		try {
			message.setSubject("Incubyte");
			message.setText("Automation QA test for Incubyte");


		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	@Then("^i attached a file$")
	public void attachedFile() {

		
		try {
			MimeBodyPart mpt=new MimeBodyPart();
			Multipart multipart=new MimeMultipart();
			mpt= new MimeBodyPart();
			
			String file="./resorces/file.xls";
			
			DataSource source1 = new FileDataSource(file);
			mpt.setDataHandler(new DataHandler(source1));
	        multipart.addBodyPart(mpt);

	        message.setContent(multipart);

	        System.out.println("Sending");
	        Transport.send(message);
	        
			
			


		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	

}
