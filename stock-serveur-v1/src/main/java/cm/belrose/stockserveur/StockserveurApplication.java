package cm.belrose.stockserveur;

import cm.belrose.stockserveur.config.initializer.IInitializeStockDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @EnableJpaRepositories(...): allows integrating spring-data-envers project to your Spring Boot project
 */
@SpringBootApplication
@EntityScan(basePackageClasses = {
		StockserveurApplication.class,
		Jsr310JpaConverters.class
})
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class StockserveurApplication {


	/**
	 *all the Java 8 Date/Time fields in the domain models automatically get
	 * converted to SQL types when we persist them in the database.
	 */
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(StockserveurApplication.class, args);
	}









}
