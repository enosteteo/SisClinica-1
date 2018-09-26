/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.dcx.poo.sisclinica.services;

import br.ufpb.dcx.poo.sisclinica.models.MedicoModel;
import br.ufpb.dcx.poo.sisclinica.models.PacienteModel;
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
    private List<PacienteModel> pacientes;
    private List<MedicoModel> medicos;
    private int ultimoIdPaciente = 0;
    

	public Clinica(){
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
    
    public List<PacienteModel> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<PacienteModel> pacientes) {
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

    public void adicionarPaciente(PacienteModel paciente) throws Exception{
        for(PacienteModel p : this.getPacientes()){
            if(p.getNome().equalsIgnoreCase(paciente.getNome())){
                throw new Exception("Paciente já existe no sistema!");
            }
        }
        this.pacientes.add(paciente);
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