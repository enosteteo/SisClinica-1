/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.dcx.poo.sisclinica.services;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dcx.poo.sisclinica.models.ConsultaModel;
import br.ufpb.dcx.poo.sisclinica.models.PacienteModel;
import br.ufpb.dcx.poo.sisclinica.models.MedicoModel;

import org.modelmapper.ModelMapper;

public class ClinicaService {

	private String nome;

	private List<PacienteModel> pacientes;
	private List<ConsultaModel> consultas;
	private List<MedicoModel> medicos;
	private int ultimoIdPaciente = 0;

	public ClinicaService() {
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

	public void adicionarPaciente(PacienteModel paciente) throws Exception {
		for (PacienteModel p : this.getPacientes()) {
			if (p.getNome().equalsIgnoreCase(paciente.getNome())) {
				throw new Exception("Paciente já existe no sistema!");
			}
		}
		this.pacientes.add(paciente);
	}

	public int getUltimoIdPaciente() {
		return ultimoIdPaciente;
	}

	public void setUltimoIdPaciente(int ultimoIdPaciente) {
		this.ultimoIdPaciente = ultimoIdPaciente;
	}

	public PacienteModel procurarPaciente(int id) throws Exception{
        for(PacienteModel p : this.getPacientes()) {
            if(p.getId() == id){
                return p;
            }
        }
        throw new Exception("Id não encontrado!");
     }

	public void setMedicos(List<MedicoModel> medicos) {
		this.medicos = medicos;
	}
	
	public List<ConsultaModel> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<ConsultaModel> consultas) {
		this.consultas = consultas;
	}

	public void adicionarConsulta(ConsultaModel consulta) throws Exception {
		for (ConsultaModel consul : this.consultas) {
			if (consul.getPaciente().getNome().equalsIgnoreCase(consulta.getPaciente().getNome())) {
				throw new Exception("A consulta deste paciente já foi agendada");
			}
		}
		this.consultas.add(consulta);
	}

	public ConsultaModel procurarConsulta(int id) throws Exception{
        for(ConsultaModel c : this.getConsultas()){
        	if(c.getId() == id) {
        		return c;
        	}
        }
        throw new Exception("Consulta não encontrada");
    }

	public boolean removeConsulta(int id) throws Exception {
		List<ConsultaModel> consultas = this.getConsultas();
		for (ConsultaModel c : consultas) {
			if (c.getId() == id) {
				this.consultas.remove(c);
				return true;
			}
		}
		throw new Exception("ID não encontrado!");
	}

	public ConsultaModel atualizarConsulta(int id, ConsultaModel consultaAtualizada) throws Exception {
        List<ConsultaModel> consultas = this.getConsultas();
        for(ConsultaModel c : consultas){
            if(c.getId() == id){
                consultaAtualizada.setId(id);
                int index = consultas.indexOf(c);
                consultas.remove(c);
                consultas.add(index,consultaAtualizada);
                return consultaAtualizada;
            }
        }
        throw new Exception("ID não encontrado!");
    }

	public PacienteModel atualizaPaciente(int id, PacienteModel pacienteAtualizado) throws Exception{
        List<PacienteModel> listaPacientes = this.getPacientes();
        ModelMapper mp = new ModelMapper();
        for(PacienteModel p : listaPacientes){
            if(p.getId() == id){
                pacienteAtualizado.setId(id);
                mp.map(pacienteAtualizado, p);
                return p;
                
            }
        }
        throw new Exception("ID não encontrado");
    }

	public boolean removePaciente(int id) throws Exception {
		List<PacienteModel> listaPacientes = this.getPacientes();
		for (PacienteModel p : listaPacientes) {
			if (p.getId() == id) {
				listaPacientes.remove(p);
				return true;
			}
		}

		throw new Exception("Paciente não encontrado!");
	}

	public MedicoModel adicionarMedico(MedicoModel medico) throws Exception {
		for (MedicoModel m : medicos) {
			if (m.getId() == medico.getId()) {
				throw new Exception("Já existe um medico com este id!");
			}
		}
		this.medicos.add(medico);
		return medico;
	}

	public List<MedicoModel> getMedicos() {
		return medicos;
	}

	public MedicoModel buscarMedico(int id) throws Exception {
		for (MedicoModel m : medicos) {
			if (id == m.getId()) {
				return m;
			}
		}
		throw new Exception("Não existe medico com este id!");

	}

	public MedicoModel atualizarMedico(MedicoModel medico, int id) {
		for (MedicoModel m : medicos) {
			if (m.getId() == id) {
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