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
public class SisclinicaApplicationTests {

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
			
		given().port(port)
			 .contentType("application/json")
			 .body(amanda)
		.when().port(port)
			.delete("/pacientes/1")
		.then()
			.statusCode(200)
			.body(equalTo("{ \"status\" : \" success\"}"));

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
			.body(equalTo("{ \"status\" : \" success\"}"));
			//.body("medico",containsString("cardio"))
			//.body("dataConsulta",containsString("04"))
			//.body("dataRetorno",containsString("123"));
	}
	
	
	@Test
	public void testMedico() {
		String medico = "{\"nome\":\"cardio\",\"especialidade\": \"s2\",\"crm\": \"345\",\"cpf\":\"789\",\"salaDeAtendimento\":\"4\",\"salario\":\"4.000\"}";
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
			.get("/medicos/2")
		.then()
			.statusCode(200)
			.body("id", greaterThan(1))
			.body("nome",containsString("cardio"))
			.body("especialidade",containsString("s2"));
		
		//given().port(port)
			//.contentType("application/json")
		//.when().port(port)
			//.get("/medicos")
		//.then()
			//.statusCode(200)
			//.body("[2].id", greaterThan(1))
			//.body("[2].nome",containsString("cardio"))
			//.body("[2].especialidade",containsString("s2"));
			//.body("[2].
	
		
		//given().port(port)
	 		//.contentType("application/json")
	 		//.body(medico)
	 	//.when().port(port)
	 		//.delete("/medicos/2")
	 	//.then()
	 		//.statusCode(200)
	 		//.body(equalTo("{ \"status\" : \" success\"}"));
	}
	
	@Test
	public void testExame() {
		String exame = "{\"dataExame\":\"567\",\"valor\": \"32\",\"dataResultado\": \"345\"}";
		given().port(port)
			.contentType("application/json")
			.body(exame)
		.when().port(port)
			.post("/exames")
		.then()
			.statusCode(200)
			.body("dataExame",containsString("567"))
			.body("valor",contains(32));
	}
	
}
