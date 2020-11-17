package io.swagger.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-13T18:54:04.403Z")

@Configuration
public class SwaggerDocumentationConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Swagger Petstore")
            .description("This is a sample server Petstore server.  You can find out more about     Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).      For this sample, you can use the api key `special-key` to test the authorization     filters.")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("","", "apiteam@swagger.io"))
            .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("io.swagger.controller"))
                    .build()
                .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes())
				.securityContexts(securityContexts());
    }

	private List<? extends SecurityScheme> securitySchemes() {
		SecurityScheme schemeApiKey = new ApiKey("api_key", "key", "query");
		SecurityScheme schemeBasicAuth = new BasicAuth("basicAuth");
		return Arrays.asList(schemeApiKey, schemeBasicAuth);
	}

	private List<SecurityContext> securityContexts() {
		SecurityContext contextSecurityKey = SecurityContext.builder()
			.forPaths(Predicates.not(PathSelectors.regex("/liveness")))
			.securityReferences(securityReferences()).build();
			
		return Arrays.asList(contextSecurityKey);
	}

	private List<SecurityReference> securityReferences() {
		AuthorizationScope[] scopes = new AuthorizationScope[0];
		SecurityReference apiKeySecRef = SecurityReference.builder()
			.reference("api_key")
			.scopes(scopes)
			.build();
			
		SecurityReference basicAuthSecRef = SecurityReference.builder()
			.reference("basicAuth")
			.scopes(scopes)
			.build();
		
		return Arrays.asList(apiKeySecRef, basicAuthSecRef);
	}
    
}
