/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpb.dcx.poo.sisclinica.services;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dcx.poo.sisclinica.models.Consulta;
import br.ufpb.dcx.poo.sisclinica.models.Exame;
import br.ufpb.dcx.poo.sisclinica.models.Paciente;
import br.ufpb.dcx.poo.sisclinica.models.Medico;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClinicaService {

    private String nome;

    private List<Paciente> pacientes;
    private List<Consulta> consultas;
    private List<Medico> medicos;
    private long ultimoIdPaciente = 0;
    private long ultimoIdMedico = 0;

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

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public Paciente adicionarPaciente(Paciente paciente) throws Exception {
        for (Paciente p : this.getPacientes()) {
            if (p.getNome().equalsIgnoreCase(paciente.getNome())) {
                throw new Exception("Paciente já existe no sistema!");
            }
        }
        paciente.setId(++this.ultimoIdPaciente);
        this.pacientes.add(paciente);
        return paciente;
    }

    public long getUltimoIdPaciente() {
        return ultimoIdPaciente;
    }

    public void setUltimoIdPaciente(long ultimoIdPaciente) {
        this.ultimoIdPaciente = ultimoIdPaciente;
    }

    public Paciente procurarPaciente(long id) throws Exception {
        for (Paciente p : this.getPacientes()) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new Exception("Id não encontrado!");
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public void adicionarConsulta(Consulta consulta) throws Exception {
        for (Consulta consul : this.consultas) {
            if (consul.getPaciente().getNome().equalsIgnoreCase(consulta.getPaciente().getNome())) {
                throw new Exception("A consulta deste paciente já foi agendada");
            }
        }
        this.consultas.add(consulta);
    }

    public Consulta procurarConsulta(long id) throws Exception {
        for (Consulta c : this.getConsultas()) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new Exception("Consulta não encontrada");
    }

    public boolean removeConsulta(long id) throws Exception {
        List<Consulta> consultas = this.getConsultas();
        for (Consulta c : consultas) {
            if (c.getId() == id) {
                this.consultas.remove(c);
                return true;
            }
        }
        throw new Exception("ID não encontrado!");
    }

    public Consulta atualizarConsulta(long id, Consulta consultaAtualizada) throws Exception {
        List<Consulta> consultas = this.getConsultas();
        for (Consulta c : consultas) {
            if (c.getId() == id) {
                consultaAtualizada.setId(id);
                int index = consultas.indexOf(c);
                consultas.remove(c);
                consultas.add(index, consultaAtualizada);
                return consultaAtualizada;
            }
        }
        throw new Exception("ID não encontrado!");
    }

    public Paciente atualizaPaciente(long id, Paciente pacienteAtualizado) throws Exception {
        List<Paciente> listaPacientes = this.getPacientes();
        ModelMapper mp = new ModelMapper();
        for (Paciente p : listaPacientes) {
            if (p.getId() == id) {
                pacienteAtualizado.setId(id);
                mp.map(pacienteAtualizado, p);
                return p;

            }
        }
        throw new Exception("ID não encontrado");
    }

    public boolean removePaciente(long id) throws Exception {
        List<Paciente> listaPacientes = this.getPacientes();
        for (Paciente p : listaPacientes) {
            if (p.getId() == id) {
                listaPacientes.remove(p);
                return true;
            }
        }

        throw new Exception("Paciente não encontrado!");
    }

    public Medico adicionarMedico(Medico medico) throws Exception {
        for (Medico m : medicos) {
            if (m.getId() == medico.getId()) {
                throw new Exception("Já existe um medico com este id!");
            }
        }
        medico.setId(++this.ultimoIdPaciente);
        this.medicos.add(medico);
        return medico;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public Medico buscarMedico(long id) throws Exception {
        for (Medico m : medicos) {
            if (id == m.getId()) {
                return m;
            }
        }
        throw new Exception("Não existe medico com este id!");

    }

    public Medico atualizarMedico(Medico medico, long id) {
        for (Medico m : medicos) {
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

    public void deletarMedico(long id) throws Exception {
        for (Medico m : medicos) {
            if (m.getId() == id) {
                medicos.remove(m);
            } else {
                throw new Exception("Medico de Id" + id + " não existe");
            }
        }
    }

    public Exame adicionaExamePaciente(long idPaciente, Exame exame) throws Exception {
        Paciente p = this.procurarPaciente(idPaciente);
        p.addExame(exame);
        return exame;
    }

    public List<Exame> listaExamesPaciente(long id) throws Exception {
        Paciente p = this.procurarPaciente(id);
        return p.getExames();
    }
    
    public Exame procuraExameId(long idPaciente, long idExame) throws Exception{
        Paciente p = this.procurarPaciente(idPaciente);
        return p.procurarExame(idExame);
    }
    
    public Exame atualizaExameId(long idPaciente,long idExame, Exame ex) throws Exception{
        ModelMapper mp = new ModelMapper();
        Exame exameAntigo = this.procuraExameId(idPaciente, idExame);
        ex.setId(exameAntigo.getId());
        mp.map(ex, exameAntigo);
        return exameAntigo;
    }
}
