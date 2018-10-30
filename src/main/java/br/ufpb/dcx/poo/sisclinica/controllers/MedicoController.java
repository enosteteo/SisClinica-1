/**
 *
 */
package br.ufpb.dcx.poo.sisclinica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.dcx.poo.sisclinica.models.Medico;
import br.ufpb.dcx.poo.sisclinica.models.Consulta;
import br.ufpb.dcx.poo.sisclinica.services.ClinicaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class MedicoController {

    @Autowired
    private ClinicaService service;

    @ApiOperation(
            value = "Obtem a lista de médicos cadastradas no sistema",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo a lista de médicos cadastrados no sistema e seus dados.")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna a lista de médicos em formato JSON",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })
    @RequestMapping(value = "/medicos", method = RequestMethod.GET)
    public ResponseEntity<List<Medico>> listar() {
        List<Medico> resultado = service.getMedicos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Obtem os dados de um médico cadastrado no sistema pelo seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo os dados de um médico")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna os dados de um médico em formato JSON",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })
    @RequestMapping(value = "/medicos/{id}", method = RequestMethod.GET)
    public ResponseEntity<Medico> buscarMedico(@PathVariable("id") int id) throws Exception {
        Medico retorno = this.service.buscarMedico(id);
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Cadastra um médico no sistema",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo os dados de um médico cadastrado")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna os dados do um médico em formato JSON",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })
    @RequestMapping(value = "/medicos", method = RequestMethod.POST)
    public ResponseEntity<Medico> adicionar(@RequestBody Medico medico) throws Exception {
        Medico resultado = this.service.adicionarMedico(medico);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Atualiza os dados de um médico cadastrado no sistema pelo seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo os dados de um médico")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna os dados de um médico em formato JSON",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })
    @RequestMapping(value = "/medicos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Medico> atualizarMedico(@PathVariable("id") int id, @RequestBody Medico medico) throws Exception {
        Medico resultado = this.service.atualizarMedico(medico, id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Deleta um médico cadastrado no sistema pelo seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo o status da requisição")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna um JSON contendo o status = success",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })
    @RequestMapping(value = "/medicos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaMedico(@PathVariable("id") int id) throws Exception {
        this.service.deletarMedico(id);
        return new ResponseEntity<>("{ \"status\" : \" success\"}", HttpStatus.OK);
    }
    
    @ApiOperation(
            value = "Obtem os dados de consultas de um médico cadastrado no sistema pelo seu ID",
            response = ResponseEntity.class,
            notes = "Essa operação retorna um JSON contendo os dados das consultas de um médico")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Retorna os dados das consultas de um médico em formato JSON",
                response = ResponseEntity.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
                response = ResponseEntity.class
        )

    })
    
    @RequestMapping(value = "/medicos/{id}/consultas", method = RequestMethod.GET)
    public ResponseEntity<List<Consulta>> buscarConsultasMedico(@PathVariable("id") int id) throws Exception {
        List<Consulta> retornoConsultas = this.service.buscarConsultasMedico(id);
        return new ResponseEntity<>(retornoConsultas, HttpStatus.OK);
    }
    
}
