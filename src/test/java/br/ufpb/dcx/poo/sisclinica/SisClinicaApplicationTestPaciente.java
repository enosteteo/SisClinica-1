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

public class SisClinicaApplicationTestPaciente {

	private static final String SERVIDOR = "http://localhost:8080";
	
	@LocalServerPort
	private int port;
	
	public void TestePaciente() {
		baseURI = SERVIDOR;
	}
	
	@Test
	public void testPaciente() {
		
		String amanda = "{\"nome\":\"amanda\",\"raca\": \"pardo\",\"dataNascimento\": \"04\",\"cpf\":\"123\",\"rg\":\"321\",\"sexo\":\"feminino\",\"nacionalidade\":\"brasileiro\",\"cep\":\"40\",\"uf\":\"pb\",\"tipoConsulta\":\"cardio\"}";
		given().port(port)
			.contentType("application/json")
			.body(amanda)
		.when().port(port)
			.post("/pacientes")
		.then()
			.statusCode(200)
			.body("nome",containsString("amanda"))
			.body("raca",containsString("pardo"))
			.body("dataNascimento",containsString("04"))
			.body("cpf",containsString("123"))
			.body("rg",containsString("321"))
			.body("sexo",containsString("feminino"))
			.body("nacionalidade",containsString("brasileiro"))
			.body("cep",containsString("40"))
			.body("uf",containsString("pb"))
			.body("tipoConsulta",containsString("cardio"));
			
		given().port(port)
			.contentType("application/json")
		.when().port(port)
			.get("/pacientes/1")
		.then()
			.statusCode(200)
			.body("id", greaterThan(0))
			.body("nome", containsString("amanda"))
			.body("raca",containsString("pardo"))
			.body("dataNascimento",containsString("04"))
			.body("cpf",containsString("123"))
			.body("rg",containsString("321"))
			.body("sexo",containsString("feminino"))
			.body("nacionalidade",containsString("brasileiro"))
			.body("cep",containsString("40"))
			.body("uf",containsString("pb"))
			.body("tipoConsulta",containsString("cardio"));
			
		given().port(port)
			.contentType("application/json")
		.when().port(port)
			.get("/pacientes")
		.then()
			.statusCode(200)
			.body("[0].id", greaterThan(0))
			.body("[0].nome", containsString("amanda"))
			.body("[0].raca",containsString("pardo"))
			.body("[0].dataNascimento",containsString("04"))
			.body("[0].cpf",containsString("123"))
			.body("[0].rg",containsString("321"))
			.body("[0].sexo",containsString("feminino"))
			.body("[0].nacionalidade",containsString("brasileiro"))
			.body("[0].cep",containsString("40"))
			.body("[0].uf",containsString("pb"))
			.body("[0].tipoConsulta",containsString("cardio"));
		
		String amandaAtualizado = "{\"nome\":\"amanda\",\"raca\": \"pardo\",\"dataNascimento\": \"04\",\"cpf\":\"123\",\"rg\":\"321\",\"sexo\":\"feminino\",\"nacionalidade\":\"brasileiro\",\"cep\":\"40\",\"uf\":\"pb\",\"tipoConsulta\":\"ultrassom\"}";
		given().port(port)
			.contentType("application/json")
			.body(amandaAtualizado)
		.when().port(port)
			.put("/pacientes/1")
		.then()
			.statusCode(200)
			.body("nome",containsString("amanda"))
			.body("raca",containsString("pardo"))
			.body("dataNascimento",containsString("04"))
			.body("cpf",containsString("123"))
			.body("rg",containsString("321"))
			.body("sexo",containsString("feminino"))
			.body("nacionalidade",containsString("brasileiro"))
			.body("cep",containsString("40"))
			.body("uf",containsString("pb"))
			.body("tipoConsulta",containsString("ultrassom"));
		
		given().port(port)
			.contentType("application/json")
			.body(amanda)
		.when().port(port)
			.delete("/pacientes/1")
		.then()
			.statusCode(200)
			.body(equalTo("{ \"status\" : \" success\"}"));

	}
	
	
}
