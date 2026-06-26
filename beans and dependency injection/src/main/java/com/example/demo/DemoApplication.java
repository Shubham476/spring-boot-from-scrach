package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class DemoApplication implements CommandLineRunner {

//Field Dependency Injection
//    @Autowired
//	testBeans testBeansObj;

	//	@Autowired
//	NotificationService notSerObj;

	TestBeans testBeansObj;
	TestBeansWithConstructor testWithConObj;



	NotificationService notSerObj;
	NotificationService notSerObjforCondiProp;

/*
	constructor dependency injection
	public DemoApplication(TestBeansWithConstructor testWithConObj, TestBeans testBeansObj, NotificationService notSerObjforCondiProp) {
		this.testWithConObj = testWithConObj;
		this.testBeansObj = testBeansObj;
	}

	use qualifier to resolve ambiguity when multiple beans of the same type are available for dependency injection
	public DemoApplication(@Qualifier("emailnotif") NotificationService notSerObj){
		this.notSerObj = notSerObj;
	}
*/

	//use
    public DemoApplication(NotificationService notSerObjforCondiProp){
		this.notSerObjforCondiProp = notSerObjforCondiProp;
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		TestBeans orderService = context.getBean(TestBeans.class);
//		orderService.sendEmail("Email Sent");
	}

	@Override
	public void run(String... args) throws Exception {
//		testBeansObj.sendEmail("Hello");
//		this.testWithConObj.executeOrder(" Please check ");
//		notSerObj.send("Hello");
		notSerObjforCondiProp.send("hello");
	}


}

