/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.dcx.poo.sisclinica.services;

import br.ufpb.dcx.poo.sisclinica.models.MedicoModel;
import br.ufpb.dcx.poo.sisclinica.models.Paciente;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author nikni
 */
@Service
public class ClinicaService{
    
    private String nome;
    private List<Paciente> pacientes;
    private List<MedicoModel> medicos;
    private int ultimoIdPaciente = 0;
    

	public ClinicaService(){
        this.nome = null;
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
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

    public int getUltimoIdPaciente() {
    	return ultimoIdPaciente;
    }
    
    public void setUltimoIdPaciente(int ultimoIdPaciente) {
    	this.ultimoIdPaciente = ultimoIdPaciente;
    }
    
    public void setMedicos(List<MedicoModel> medicos) {
    	this.medicos = medicos;
    }

    public Paciente adicionarPaciente(Paciente paciente) throws Exception{
        for(Paciente p : this.getPacientes()){
            if(p.getNome().equalsIgnoreCase(paciente.getNome())){
                throw new Exception("Paciente já existe no sistema!");
            }
        }
        this.pacientes.add(paciente);
        return paciente;
    }
    
    public Paciente procurarPaciente(int id) throws Exception{
        for(Paciente p : this.getPacientes()){
            if(p.getId() == id){
                return p;
            }
        }
        throw new Exception("Paciente já existe no sistema!");
    }
    
    public Paciente atualizaPaciente(int id, Paciente pacienteAtualizado) throws Exception{
        List<Paciente> listaPacientes = this.getPacientes();
        ModelMapper mp = new ModelMapper();
        for(Paciente p : listaPacientes){
            if(p.getId() == id){
                pacienteAtualizado.setId(id);
                mp.map(pacienteAtualizado, p);
                return p;
                
            }
        }
        throw new Exception("ID não encontrado");
    }
    
    public boolean removePaciente(int id) throws Exception{
        List<Paciente> listaPacientes = this.getPacientes();
        for(Paciente p : listaPacientes){
            if(p.getId() == id){
                listaPacientes.remove(p);
                return true;
            }
        }
        
        throw new Exception("Paciente não encontrado!");
    }
    
    public MedicoModel adicionarMedico(MedicoModel medico) throws Exception{
    	for(MedicoModel m: medicos) {
    		if(m.getId() == medico.getId()) {
    			throw new Exception("Já existe um medico com este id!");
    		}
    	}
		this.medicos.add(medico);
		return medico;
    }
    
    public List<MedicoModel> getMedicos(){
    	return medicos;
    }
    
    public MedicoModel buscarMedico(int id) throws Exception {
    	for (MedicoModel m : medicos) {
			if(id == m.getId()) {
				return m;
			}
		}
		throw new Exception("Não existe medico com este id!");

    }

	public MedicoModel atualizarMedico(MedicoModel medico, int id) {
		for (MedicoModel m : medicos) {
			if(m.getId() == id) {
				medico.setId(id);
				int i = medicos.indexOf(m);
				medicos.remove(m);
				medicos.add(i, medico);
				return medico;
			}
		}
		return null;
	}

	public void deletarMedico(int id) throws Exception {
		for (MedicoModel m : medicos) {
			if(m.getId() == id) {
				medicos.remove(m);
			}else {
				throw new Exception("Medico de Id"+id+" não existe");
			}
		}
		
	}
    
}