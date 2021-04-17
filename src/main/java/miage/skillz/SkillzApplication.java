package miage.skillz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

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
