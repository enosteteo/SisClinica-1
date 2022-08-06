/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.dcx.poo.sisclinica.controllers;

import br.ufpb.dcx.poo.sisclinica.models.Exame;
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

/**
 *
 * @author nikni
 */
@RestController
public class ExameController {
    
    @Autowired
    private ClinicaService service;
    
    @ApiOperation(
            value = "Obter as consultas de um paciente cadastrado no sistema pelo seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo as informações das consultas de um  paciente identificado seu ID.")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna um JSON com os dados das consultas",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })

    @RequestMapping(value = "/pacientes/{id}/exames", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Exame>> listaExamesPaciente(@PathVariable(value = "id") long id) throws Exception{
        List<Exame> lista = this.service.listaExamesPaciente(id);
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }
    
      @ApiOperation(
            value = "Obter os dados de uma consulta especifica de um paciente cadastrado no sistema pelo seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo as informações da consulta de um  paciente identificado seu ID.")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna um JSON com os dados da consulta",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })
    @RequestMapping(value = "/pacientes/{id}/exames/{id2}", method = RequestMethod.GET)
      @CrossOrigin(origins = "http://localhost:4200")
      public ResponseEntity<Exame> procuraExamesIdPaciente(@PathVariable(value = "id") long idPaciente, @PathVariable(value = "id2") long idExame) throws Exception{
        Exame exame = this.service.procuraExameId(idPaciente, idExame);
        return new ResponseEntity<>(exame,HttpStatus.OK);
    }
    
      @ApiOperation(
            value = "Cadastra uma cosulta para um paciente cadastrado no sistema e identificado pelo seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo as informações da consulta cadastrada.")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna um JSON com os dado da consulta",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })
    @RequestMapping(value = "/pacientes/{id}/exames", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
      public ResponseEntity<Exame> adicionarExamesPaciente(@PathVariable(value = "id") long id, @RequestBody Exame ex) throws Exception{
        Exame exame = this.service.adicionaExamePaciente(id, ex);
        return new ResponseEntity<>(exame,HttpStatus.OK);
    }
    
         @ApiOperation(
            value = "Atualiza uma cosulta para um paciente cadastrado no sistema e identificado pelo seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo as informações da consulta atualizada.")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna um JSON com os dado da consulta atualizada",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })
    @RequestMapping(value = "/pacientes/{id}/exames/{id2}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Exame> atualizaExamesPaciente(@PathVariable(value = "id") long idPaciente, @PathVariable(value = "id2") long idExame, @RequestBody Exame exame) throws Exception{
    Exame exameAtualizado = this.service.atualizaExameId(idPaciente, idExame, exame);
    return new ResponseEntity<>(exameAtualizado,HttpStatus.OK);
    }
}
