package br.ufpb.dcx.poo.sisclinica.controllers;

import br.ufpb.dcx.poo.sisclinica.models.PacienteModel;
import br.ufpb.dcx.poo.sisclinica.services.ClinicaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PacienteController {

    @Autowired
    private ClinicaService service;

    @RequestMapping(value = "/pacientes", method = RequestMethod.GET)
    public ResponseEntity<List<PacienteModel>> listar() {
        List<PacienteModel> resultado = this.service.getPacientes();
        return new ResponseEntity<List<PacienteModel>>(resultado, HttpStatus.OK);

    }

    @RequestMapping(value = "/pacientes", method = RequestMethod.POST)

    public ResponseEntity<PacienteModel> adicionar(@RequestBody PacienteModel paciente) throws Exception {
        this.service.adicionarPaciente(paciente);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
