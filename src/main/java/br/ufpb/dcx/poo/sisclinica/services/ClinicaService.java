/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.dcx.poo.sisclinica.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.ufpb.dcx.poo.sisclinica.models.ConsultaModel;
import br.ufpb.dcx.poo.sisclinica.models.PacienteModel;

/**
 *
 * @author nikni
 */
@Service
public class ClinicaService{
    
    private String nome;
    private List<PacienteModel> pacientes;
    private List<ConsultaModel> consultas;
    
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
        this.pacientes.add(paciente);
    }

	public List<ConsultaModel> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<ConsultaModel> consultas) {
		this.consultas = consultas;
	}
	
	public void adicionarConsulta(ConsultaModel consulta) throws Exception{
		for (ConsultaModel consul : this.consultas) {
			if(consul.getPaciente().getNome().equalsIgnoreCase(consulta.getPaciente().getNome())) {
				throw new Exception("A consulta deste paciente já foi agendada");
			}
		}
		this.consultas.add(consulta);
	}
	public ConsultaModel procurarConsulta(int id) throws Exception{
        for(ConsultaModel p : this.getConsultas()){
            if(p.getId() == id){
                return p;
            }
        }
        
        throw new Exception("Consulta não encontrada");
    }
    
    public boolean removeConsulta(int id) throws Exception{
        List<ConsultaModel> consultas = this.getConsultas();
        for(ConsultaModel c : consultas){
            if(c.getId() == id){
                this.consultas.remove(c);
                return true;
            }
        } 
        throw new Exception("ID não encontrado!");
    }
    
    public ConsultaModel atualizarConsulta(int id, ConsultaModel consultaAtualizada) throws Exception{
        List<ConsultaModel> consultas = this.getConsultas();
        for(ConsultaModel c : consultas){
            if(p.getId() == id){
                consultaAtualizada.setId(id);
                int index = consultas.indexOf(c);
                consultas.remove(c);
                consultas.add(index,consultaAtualizada);
                return consultaAtualizada;
            }
        }
        
        throw new Exception("ID não encontrado!");
    }
	
	
  
    
}