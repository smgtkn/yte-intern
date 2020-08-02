package yte.intern.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(Application.class, args);
		EtkinlikRepository etkinlikRepository=context.getBean(EtkinlikRepository.class);
		Etkinlik etkinlik1=new Etkinlik(null,"simge","23/04/1999","21/09/2077");
		etkinlikRepository.save(etkinlik1);
	}

}
