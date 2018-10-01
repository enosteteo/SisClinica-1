package br.ufpb.dcx.poo.sisclinica;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SisClinicaApplicationTestConsulta {

	private static final String SERVIDOR = "http://localhost:8080";
	
	@LocalServerPort
	private int port;
	
	public void TestePaciente() {
		baseURI = SERVIDOR;
	}
	
	@Test
	public void testConsulta() {
		String consulta = "{\"paciente\":\"amanda\",\"medico\": \"cardio\",\"dataConsulta\": \"04\",\"dataRetorno\":\"123\"}";
                
		given().port(port)
			.contentType("application/json")
			.body(consulta)
		.when().port(port)
			.post("/consultas")
		.then()
			.statusCode(200)
			.body(equalTo("{ \"status\": \"success\"}"));
                
		given().port(port)
			.contentType("application/json")
		.when().port(port)
			.get("/consultas/1")
		.then()
			.statusCode(200)
			.body("dataConsulta",containsString("04"));
			
		given().port(port)
			.contentType("application/json")
		.when().port(port)
			.get("/consultas")
		.then()
			.statusCode(200)
			.body("[0].id",greaterThan(0))
			.body("[0].idPaciente",equalTo(0))
			.body("[0].idMedico",equalTo(0));
		
		String consultaAtualizado = "{\"paciente\":\"amanda\",\"medico\": \"cardio\",\"dataConsulta\": \"14\",\"dataRetorno\":\"123\"}";
		given().port(port)
			.contentType("application/json")
			.body(consultaAtualizado)
		.when().port(port)
			.put("/consultas/1")
		.then()
			.statusCode(200)
			.body("dataConsulta",containsString("14"));
		
		given().port(port)
 			.contentType("application/json")
 			.body(consulta)
 		.when().port(port)
 			.delete("/consultas/1")
 		.then()
 			.statusCode(200)
 			.body(equalTo("{ \"status\" : \" success\"}"));
	}
}
