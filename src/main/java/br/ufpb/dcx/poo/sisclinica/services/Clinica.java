/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.dcx.poo.sisclinica.services;


import br.ufpb.dcx.poo.sisclinica.models.Paciente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author nikni
 */
@Service
public class Clinica{
    
    private String nome;
    private List<Paciente> pacientes;

    
    public Clinica(){
        this.nome = null;
        this.pacientes = new ArrayList<>();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }


    public void adicionarPaciente(Paciente paciente) throws Exception{
        for(Paciente p : this.getPacientes()){
            if(p.getNome().equalsIgnoreCase(paciente.getNome())){
                throw new Exception("Paciente já existe no sistema!");
            }
        }
        this.pacientes.add(paciente);
    }
  
    
}