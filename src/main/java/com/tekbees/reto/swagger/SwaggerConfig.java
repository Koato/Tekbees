package com.tekbees.reto.swagger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket userApi() {
		return new Docket(DocumentationType.SWAGGER_2)
//				informacion del api
				.apiInfo(userApiInfo()).select()
//				todas las rutas
				.paths(PathSelectors.any())
//				buscar los controller
				.apis(RequestHandlerSelectors.basePackage("com.tekbees.reto.controller")).build()
//				desactivo mensajes de respuesta por defecto
				.useDefaultResponseMessages(false)
//				contexto del envio y retorno de la informacion
				.produces(contextType()).consumes(contextType())
//				protocolos de solicitud
				.protocols(protocolos())
//				mensajes globales para get
				.globalResponseMessage(RequestMethod.GET, responseMessageForGET());
	}

	private Set<String> protocolos() {
		Set<String> protocolo = new HashSet<>();
		protocolo.add("http");
		protocolo.add("https");
		return protocolo;
	}

	private ApiInfo userApiInfo() {
		Contact contact = new Contact("Kato", "http://www.kato.com", "speed21@outlook.com");
		return new ApiInfoBuilder().contact(contact).title("Tekbees").version("1.0")
				.termsOfServiceUrl("TERMS OF SERVICE URL").license("Apache License Version 2.0")
				.description("Conexión a MongoDB (Tekbees)").build();
	}

	private Set<String> contextType() {
		Set<String> context = new HashSet<>();
		context.add(MediaType.APPLICATION_JSON_VALUE);
		return context;
	}

	private List<ResponseMessage> responseMessageForGET() {
		return new ArrayList<ResponseMessage>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{
				add(new ResponseMessageBuilder().code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
						.message("ERROR INTERNO EN EL SERVIDOR").build());
				add(new ResponseMessageBuilder().code(HttpServletResponse.SC_FORBIDDEN).message("ACCESO DENEGADO")
						.build());
				add(new ResponseMessageBuilder().code(HttpServletResponse.SC_BAD_REQUEST).message("SINTAXIS INCORRECTA")
						.build());
			}
		};
	}
}