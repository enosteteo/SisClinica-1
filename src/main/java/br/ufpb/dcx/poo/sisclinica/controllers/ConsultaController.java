package br.ufpb.dcx.poo.sisclinica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufpb.dcx.poo.sisclinica.models.ConsultaModel;
import br.ufpb.dcx.poo.sisclinica.services.ClinicaService;

public class ConsultaController {
	@Autowired
	private ClinicaService service;

	@RequestMapping(value = "/consultas", method = RequestMethod.GET)
	public ResponseEntity<List<ConsultaModel>> listar() {
		List<ConsultaModel> resultado = this.service.getConsultas();
		return new ResponseEntity<List<ConsultaModel>>(resultado, HttpStatus.OK);

	}

	@RequestMapping(value = "/consultas", method = RequestMethod.POST)
	public ResponseEntity<String> adicionar(@RequestBody ConsultaModel consulta) throws Exception {
		this.service.adicionarConsulta(consulta);
		return new ResponseEntity<>("{ \"status\": \"sucess\"}", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultas/{id}", method = RequestMethod.GET)
	public ResponseEntity<ConsultaModel> listarConsultaId(@PathVariable(value="id") int id) throws Exception{
		ConsultaModel consultaId;
		consultaId = this.service.procurarConsulta(id);
		return new ResponseEntity<>(consultaId, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultas/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeConsultaId(@PathVariable(value = "id") int id) throws Exception{
        this.service.removeConsulta(id);
        return new ResponseEntity<>("{ \"status\" : \" sucess\"}",HttpStatus.OK);
    }
    
    @RequestMapping(value = "/consulta/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ConsultaModel> atualizarConsultaId(@PathVariable(value = "id") int id, @RequestBody ConsultaModel consultaAtualizada) throws Exception{
        ConsultaModel consultaFinal = this.service.atualizarConsulta(id, consultaAtualizada);
        return new ResponseEntity<>(consultaFinal,HttpStatus.OK);
    }
    

}
