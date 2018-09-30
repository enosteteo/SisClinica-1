package br.ufpb.dcx.poo.sisclinica.controllers;

import br.ufpb.dcx.poo.sisclinica.models.Paciente;
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

@RestController
public class PacienteController {

    @Autowired
    private ClinicaService service;

    @RequestMapping(value = "/pacientes", method = RequestMethod.GET)
    public ResponseEntity<List<Paciente>> listar() {
        List<Paciente> resultado = this.service.getPacientes();
        return new ResponseEntity<List<Paciente>>(resultado, HttpStatus.OK);

    }

    @RequestMapping(value = "/pacientes", method = RequestMethod.POST)


    public ResponseEntity<Paciente> adicionar(@RequestBody Paciente paciente) throws Exception {
        Paciente pacienteRetorno = this.service.adicionarPaciente(paciente);
        return new ResponseEntity<>(pacienteRetorno, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/pacientes/{id}", method = RequestMethod.GET)
    
    public ResponseEntity<Paciente> listarPacienteId(@PathVariable(value="id") int id) throws Exception{
    	Paciente pacienteId;
        pacienteId = this.service.procurarPaciente(id);
        return new ResponseEntity<>(pacienteId, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/pacientes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removePacienteId(@PathVariable(value = "id") int id) throws Exception{
        this.service.removePaciente(id);
        return new ResponseEntity<>("{ \"status\" : \" success\"}",HttpStatus.OK);
    }
    
    @RequestMapping(value = "/pacientes/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Paciente> atualizarPacienteId(@PathVariable(value = "id") int id, @RequestBody Paciente pacienteAtualizado) throws Exception{
    	Paciente pacienteFinal = this.service.atualizaPaciente(id, pacienteAtualizado);
        return new ResponseEntity<>(pacienteFinal,HttpStatus.OK);
    }
    

}
