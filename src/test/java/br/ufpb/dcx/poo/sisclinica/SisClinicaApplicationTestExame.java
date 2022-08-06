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

public class SisClinicaApplicationTestExame {

	private static final String SERVIDOR = "http://localhost:8080";
	
	@LocalServerPort
	private int port;
	
	public void TestePaciente() {
		baseURI = SERVIDOR;
	}
	
	@Test
	public void test4Exame() {
		String amanda = "{\"nome\":\"Carlos\",\"raca\": \"pardo\",\"dataNascimento\": \"04\",\"cpf\":\"123\",\"rg\":\"321\",\"sexo\":\"feminino\",\"nacionalidade\":\"brasileiro\",\"cep\":\"40\",\"uf\":\"pb\",\"tipoConsulta\":\"cardio\"}";
		given().port(port)
			.contentType("application/json")
			.body(amanda)
		.when().port(port)
			.post("/pacientes")
		.then()
			.statusCode(200)
			.body("nome",containsString("Carlos"))
			.body("raca",containsString("pardo"))
			.body("dataNascimento",containsString("04"))
			.body("cpf",containsString("123"))
			.body("rg",containsString("321"))
			.body("sexo",containsString("feminino"))
			.body("nacionalidade",containsString("brasileiro"))
			.body("cep",containsString("40"))
			.body("uf",containsString("pb"))
			.body("tipoConsulta",containsString("cardio"));
		
		String exame = "{\"dataExame\":\"567\",\"valor\": \"32\",\"dataResultado\": \"345\"}";
		given().port(port)
			.contentType("application/json")
			.body(exame)
		.when().port(port)
			.post("/pacientes/1/exames")
		.then()
			.statusCode(200)
			.body("dataExame",containsString("567"));
		
		given().port(port)
			.contentType("application/json")
		.when().port(port)
			.get("/pacientes/1/exames/1")
		.then()
			.statusCode(200)
			.body("dataExame",containsString("567"))
			.body("dataResultado",containsString("345"));
		
		given().port(port)
			.contentType("application/json")
		.when().port(port)
			.get("/pacientes/1/exames")
		.then()
			.statusCode(200)
			.body("[0].id",greaterThan(0))
			.body("[0].idPaciente",equalTo(1))
			.body("[0].dataExame",containsString("567"))
			.body("[0].dataResultado",containsString("345"));
		
		String exameAtualizado = "{\"dataExame\":\"667\",\"valor\": \"32\",\"dataResultado\": \"345\"}";
		given().port(port)
			.contentType("application/json")
			.body(exameAtualizado)
		.when().port(port)
			.put("/pacientes/1/exames/1")
		.then()
			.statusCode(200)
			.body("dataExame",containsString("667"));
	}
	
}
