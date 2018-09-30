package br.ufpb.dcx.poo.sisclinica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufpb.dcx.poo.sisclinica.models.Consulta;
import br.ufpb.dcx.poo.sisclinica.services.ClinicaService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultaController {

    @Autowired
    private ClinicaService service;

    @RequestMapping(value = "/consultas", method = RequestMethod.GET)
    public ResponseEntity<List<Consulta>> listar() {
        List<Consulta> resultado = this.service.getConsultas();
        return new ResponseEntity<>(resultado, HttpStatus.OK);

    }

    @RequestMapping(value = "/consultas", method = RequestMethod.POST)
    public ResponseEntity<String> adicionar(@RequestBody Consulta consulta) throws Exception {
        this.service.adicionarConsulta(consulta);
        return new ResponseEntity<>("{ \"status\": \"success\"}", HttpStatus.OK);
    }

    @RequestMapping(value = "/consultas/{id}", method = RequestMethod.GET)
    public ResponseEntity<Consulta> listarConsultaId(@PathVariable(value = "id") int id) throws Exception {
        Consulta consultaId;
        consultaId = this.service.procurarConsulta(id);
        return new ResponseEntity<>(consultaId, HttpStatus.OK);
    }

    @RequestMapping(value = "/consultas/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeConsultaId(@PathVariable(value = "id") int id) throws Exception {
        this.service.removeConsulta(id);
        return new ResponseEntity<>("{ \"status\" : \" success\"}", HttpStatus.OK);
    }

    @RequestMapping(value = "/consultas/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Consulta> atualizarConsultaId(@PathVariable(value = "id") int id, @RequestBody Consulta consultaAtualizada) throws Exception {
        Consulta consultaFinal = this.service.atualizarConsulta(id, consultaAtualizada);
        return new ResponseEntity<>(consultaFinal, HttpStatus.OK);
    }

}
