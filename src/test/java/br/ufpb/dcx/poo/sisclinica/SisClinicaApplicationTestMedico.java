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

public class SisClinicaApplicationTestMedico {

	private static final String SERVIDOR = "http://localhost:8080";
	
	@LocalServerPort
	private int port;
	
	public void TestePaciente() {
		baseURI = SERVIDOR;
	}
	
	@Test
	public void testMedico() {
		String medico = "{\"nome\":\"cardio\",\"especialidade\": \"s2\",\"crm\": \"4\",\"cpf\":\"789\",\"salaDeAtendimento\":\"4\",\"salario\":\"4.000\"}";
		given().port(port)
			.contentType("application/json")
			.body(medico)
		.when().port(port)
			.post("/medicos")
		.then()
			.statusCode(200)
			.body("nome",containsString("cardio"))
			.body("especialidade",containsString("s2"));
		
		given().port(port)
			.contentType("application/json")
		.when().port(port)
			.get("/medicos/1")
		.then()
			.statusCode(200)
			.body("id", greaterThan(0))
			.body("nome",containsString("cardio"))
			.body("especialidade",containsString("s2"));
		
		given().port(port)
			.contentType("application/json")
		.when().port(port)
			.get("/medicos")
		.then()
			.statusCode(200)
			.body("[0].id", greaterThan(0))
			.body("[0].nome",containsString("cardio"))
			.body("[0].especialidade",containsString("s2"))
			.body("[0].crm",equalTo(4))
			.body("[0].salaDeAtendimento",equalTo(4));
		
		String medicoAtualizado = "{\"nome\":\"cardio\",\"especialidade\": \"s2\",\"crm\": \"4\",\"cpf\":\"789\",\"salaDeAtendimento\":\"5\",\"salario\":\"4.000\"}";
		given().port(port)
			.contentType("application/json")
			.body(medicoAtualizado)
		.when().port(port)
			.put("/medicos/1")
		.then()
			.statusCode(200)
			.body("nome",containsString("cardio"))
			.body("especialidade",containsString("s2"))
			.body("salaDeAtendimento",equalTo(5));
	
		given().port(port)
	 		.contentType("application/json")
	 		.body(medico)
	 	.when().port(port)
	 		.delete("/medicos/1")
	 	.then()
	 		.statusCode(200)
	 		.body(equalTo("{ \"status\" : \" success\"}"));
	}
}
