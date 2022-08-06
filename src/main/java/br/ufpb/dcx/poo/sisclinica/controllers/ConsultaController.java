package br.ufpb.dcx.poo.sisclinica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufpb.dcx.poo.sisclinica.models.Consulta;
import br.ufpb.dcx.poo.sisclinica.services.ClinicaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultaController {

    @Autowired
    private ClinicaService service;

    @ApiOperation(
            value = "Obtem a lista de consultas cadastradas no sistema",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo a lista de consultas cadastradas no sistema e seus dados.")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna a lista de consultas em formato JSON",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })

    @RequestMapping(value = "/consultas", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Consulta>> listar() {
        List<Consulta> resultado = this.service.getConsultas();
        return new ResponseEntity<>(resultado, HttpStatus.OK);

    }

    @ApiOperation(
            value = "Cadastra uma consulta no sistema",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo o status = success")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna um JSON contendo status = success",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })

    @RequestMapping(value = "/consultas", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> adicionar(@RequestBody Consulta consulta) throws Exception {
        this.service.adicionarConsulta(consulta);
        return new ResponseEntity<>("{ \"status\": \"success\"}", HttpStatus.OK);
    }

    @ApiOperation(
            value = "Obtem os dados de uma consulta com base no seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo os dados de uma  consulta cadastrada")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna os dados da consulta em formato JSON",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })

    @RequestMapping(value = "/consultas/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Consulta> listarConsultaId(@PathVariable(value = "id") int id) throws Exception {
        Consulta consultaId;
        consultaId = this.service.procurarConsulta(id);
        return new ResponseEntity<>(consultaId, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Deleta uma consulta cadastrada no sistema pelo seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo o status = success")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Essa operação retorna um JSON contendo o status = success",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })
    @RequestMapping(value = "/consultas/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> removeConsultaId(@PathVariable(value = "id") int id) throws Exception {
        this.service.removeConsulta(id);
        return new ResponseEntity<>("{ \"status\" : \" success\"}", HttpStatus.OK);
    }

    @ApiOperation(
            value = "Atualiza uma consulta cadastrada no sistema pelo seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo os dados da consulta atualizada")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Essa operação retorna um JSON contendo os dados da consulta atualizada",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })
    @RequestMapping(value = "/consultas/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Consulta> atualizarConsultaId(@PathVariable(value = "id") int id, @RequestBody Consulta consultaAtualizada) throws Exception {
        Consulta consultaFinal = this.service.atualizarConsulta(id, consultaAtualizada);
        return new ResponseEntity<>(consultaFinal, HttpStatus.OK);
    }

}
