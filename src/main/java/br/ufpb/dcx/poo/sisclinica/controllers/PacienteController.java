/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.dcx.poo.sisclinica.controllers;

import br.ufpb.dcx.poo.sisclinica.models.Paciente;
import br.ufpb.dcx.poo.sisclinica.services.Clinica;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nikni
 */
@RestController
public class PacienteController {

    @Autowired
    private Clinica service;

    @RequestMapping(value = "/pacientes", method = RequestMethod.GET)
    public ResponseEntity<List<Paciente>> listar() {
        List<Paciente> resultado = this.service.getPacientes();
        return new ResponseEntity<List<Paciente>>(resultado, HttpStatus.OK);

    }

    @RequestMapping(value = "/pacientes", method = RequestMethod.POST)

    public ResponseEntity<Paciente> adicionar(@RequestBody Paciente paciente) throws Exception {
        this.service.adicionarPaciente(paciente);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
