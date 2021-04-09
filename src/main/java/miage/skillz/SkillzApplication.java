package miage.skillz;

import com.fasterxml.classmate.TypeResolver;
import miage.skillz.controller.QuizzController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.schema.AlternateTypeRules.DIRECT_SUBSTITUTION_RULE_ORDER;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.LocalTime;

@SpringBootApplication
//@EnableSwagger2
public class SkillzApplication {

	/*@Autowired
	private TypeResolver typeResolver;*/

	public static void main(String[] args) {
		SpringApplication.run(SkillzApplication.class, args);
	}

	/*@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.alternateTypeRules(
						newRule(typeResolver.resolve(ResponseEntity.class, typeResolver.resolve(RequestEntity.class)),
						typeResolver.resolve(MultipartFile.class),
						DIRECT_SUBSTITUTION_RULE_ORDER))
				.directModelSubstitute(LocalTime.class, String.class)
				.select()
				.apis(RequestHandlerSelectors.basePackage("miage.skillz.controller"))
				.paths(PathSelectors.any())
				.build();

	}*/
}
