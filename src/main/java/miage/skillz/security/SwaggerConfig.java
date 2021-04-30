package miage.skillz.security;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;
import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.schema.AlternateTypeRules.DIRECT_SUBSTITUTION_RULE_ORDER;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Autowired
	private TypeResolver typeResolver;

    @Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
//				.alternateTypeRules(
//						newRule(typeResolver.resolve(ResponseEntity.class, typeResolver.resolve(RequestEntity.class)),
//						typeResolver.resolve(MultipartFile.class),
//						DIRECT_SUBSTITUTION_RULE_ORDER))
//				.directModelSubstitute(LocalTime.class, String.class)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("miage.skillz"))
//				.paths(PathSelectors.any())
//				.build();

	}

	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Skillz REST API")
				.description("Some custom description of API.")
				.version("API TOS")
				.termsOfServiceUrl("Terms of service")
				.contact(apiContact())
				.license("License of API")
				.licenseUrl("API license URL")
				.build();
	}

	@Bean
	public Contact apiContact() {
		return new Contact(
				"Contact Name",
				"Contact Url",
				"contact@example.com"
		);
	}
}
