/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.dcx.poo.sisclinica.services;


import br.ufpb.dcx.poo.sisclinica.models.PacienteModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author nikni
 */
@Service
public class ClinicaService{
    
    private String nome;
    private List<PacienteModel> pacientes;
    private int ultimoIdPaciente = 0;

    
    public ClinicaService(){
        this.nome = null;
        this.pacientes = new ArrayList<>();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public List<PacienteModel> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<PacienteModel> pacientes) {
        this.pacientes = pacientes;
    }


    public void adicionarPaciente(PacienteModel paciente) throws Exception{
        for(PacienteModel p : this.getPacientes()){
            if(p.getNome().equalsIgnoreCase(paciente.getNome())){
                throw new Exception("Paciente já existe no sistema!");
            }
        }
        paciente.setId(++ultimoIdPaciente);
        this.pacientes.add(paciente); 
    }
    
    public PacienteModel procurarPaciente(int id) throws Exception{
        for(PacienteModel p : this.getPacientes()){
            if(p.getId() == id){
                return p;
            }
        }
        
        throw new Exception("Paciente não encontrado");
    }
    
    public boolean removePaciente(int id) throws Exception{
        List<PacienteModel> pacientes = this.getPacientes();
        for(PacienteModel p : pacientes){
            if(p.getId() == id){
                this.pacientes.remove(p);
                return true;
            }
        } 
        throw new Exception("ID não encontrado!");
    }
    
    public PacienteModel atualizaPaciente(int id, PacienteModel pacienteAtualizado) throws Exception{
        List<PacienteModel> pacientes = this.getPacientes();
        for(PacienteModel p : pacientes){
            if(p.getId() == id){
                pacienteAtualizado.setId(id);
                int index = pacientes.indexOf(p);
                pacientes.remove(p);
                pacientes.add(index,pacienteAtualizado);
                return pacienteAtualizado;
            }
        }
        
        throw new Exception("ID não encontrado!");
    }

    public int getUltimoIdPaciente() {
        return ultimoIdPaciente;
    }

    public void setUltimoIdPaciente(int ultimoIdPaciente) {
        this.ultimoIdPaciente = ultimoIdPaciente;
    }
  
    
    
}