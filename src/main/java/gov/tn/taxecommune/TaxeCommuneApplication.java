package gov.tn.taxecommune;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
//@ComponentScan
//(scanBasePackages = {"gov.tn.taxeCommune.controller","gov.tn.taxeCommune.config","gov.tn.taxeCommune.security"})
//@EnableAutoConfiguration
//@ComponentScan(basePackages="gov.tn.taxecommune")
//@EnableJpaRepositories("gov.tn.taxecommune.repository")
//@ComponentScan(basePackageClasses = {AuthentificationController.class, UserRepository.class})
//@RequestMapping("/taxecommune/rest/v1/services/")
public class TaxeCommuneApplication{ 
//extends SpringBootServletInitializer{	
//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(TaxeCommuneApplication.class);
//    }
//	@RequestMapping("/hh")
//	String home() {
//		return "Hello World!";
//	}

	public static void main(String[] args) {
		SpringApplication.run(TaxeCommuneApplication.class, args);
	}
}
