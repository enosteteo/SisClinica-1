package br.ufpb.dcx.poo.sisclinica.controllers;

import br.ufpb.dcx.poo.sisclinica.models.Paciente;
import br.ufpb.dcx.poo.sisclinica.services.ClinicaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PacienteController {

    @Autowired
    private ClinicaService service;

    @ApiOperation(
            value = "Obter Lista de pacientes cadastrados no sistema",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo a lista de pacientes cadastrados no sistema e seus dados.")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna a lista de pacientes em formato JSON",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })

    @RequestMapping(value = "/pacientes", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Paciente>> listar() {
        List<Paciente> resultado = this.service.getPacientes();
        return new ResponseEntity<List<Paciente>>(resultado, HttpStatus.OK);

    }

    @ApiOperation(
            value = "Cadastra um paciente no sistema",
            response = ResponseEntity.class,
            notes = "Essa operação recebe um JSON com os dados do Paciente e o adiciona ao sistema")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna um JSON com os dados do paciente adicionado",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })

    @RequestMapping(value = "/pacientes", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Paciente> adicionar(@RequestBody Paciente paciente) throws Exception {

        Paciente pacienteRetorno = this.service.adicionarPaciente(paciente);
        return new ResponseEntity<>(pacienteRetorno, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Obter os dados de pacientes cadastrados no sistema pelo seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo as informações do paciente identificado seu ID.")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna um JSON com os dados do paciente",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })

    @RequestMapping(value = "/pacientes/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Paciente> listarPacienteId(@PathVariable(value = "id") int id) throws Exception {
        Paciente pacienteId;
        pacienteId = this.service.procurarPaciente(id);
        return new ResponseEntity<>(pacienteId, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Deleta um paciente cadastrado no sistema pelo seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo o status da requisição.")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna um JSON contendo o status =  success",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })

    @RequestMapping(value = "/pacientes/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> removePacienteId(@PathVariable(value = "id") int id) throws Exception {
        this.service.removePaciente(id);
        return new ResponseEntity<>("{ \"status\" : \" success\"}", HttpStatus.OK);
    }

    @ApiOperation(
            value = "Atualiza os dados de pacientes cadastrados no sistema pelo seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo as informações do paciente atualizado que foi identificado pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna um JSON com os dados do paciente",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })

    @RequestMapping(value = "/pacientes/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Paciente> atualizarPacienteId(@PathVariable(value = "id") int id, @RequestBody Paciente pacienteAtualizado) throws Exception {
        Paciente pacienteFinal = this.service.atualizaPaciente(id, pacienteAtualizado);
        return new ResponseEntity<>(pacienteFinal, HttpStatus.OK);
    }

}
