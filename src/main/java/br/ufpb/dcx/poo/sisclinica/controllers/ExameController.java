/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.dcx.poo.sisclinica.controllers;

import br.ufpb.dcx.poo.sisclinica.models.Exame;
import br.ufpb.dcx.poo.sisclinica.services.ClinicaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    
    @RequestMapping(value = "/pacientes/{id}/exames", method = RequestMethod.GET)
    public ResponseEntity<List<Exame>> listaExamesPaciente(@PathVariable(value = "id") long id) throws Exception{
        List<Exame> lista = this.service.listaExamesPaciente(id);
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/pacientes/{id}/exames/{id2}", method = RequestMethod.GET)
    public ResponseEntity<Exame> procuraExamesIdPaciente(@PathVariable(value = "id") long idPaciente, @PathVariable(value = "id2") long idExame) throws Exception{
        Exame exame = this.service.procuraExameId(idPaciente, idExame);
        return new ResponseEntity<>(exame,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/pacientes/{id}/exames", method = RequestMethod.POST)
    public ResponseEntity<Exame> adicionarExamesPaciente(@PathVariable(value = "id") long id, @RequestBody Exame ex) throws Exception{
        Exame exame = this.service.adicionaExamePaciente(id, ex);
        return new ResponseEntity<>(exame,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/pacientes/{id}/exames/{id2}", method = RequestMethod.PUT)
    public ResponseEntity<Exame> atualizaExamesPaciente(@PathVariable(value = "id") long idPaciente, @PathVariable(value = "id2") long idExame, @RequestBody Exame exame) throws Exception{
        Exame exameAtualizado = this.service.atualizaExameId(idPaciente, idExame, exame);
        return new ResponseEntity<>(exameAtualizado,HttpStatus.OK);
    }
}
