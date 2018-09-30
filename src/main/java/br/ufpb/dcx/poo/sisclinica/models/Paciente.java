package br.ufpb.dcx.poo.sisclinica.models;

import java.util.ArrayList;
import java.util.List;

public class Paciente {

    private long id;
    private String nome;
    private String raca;
    private String dataNascimento;
    private String cpf;
    private String rg;
    private String sexo;
    private String nacionalidade;
    private String cep;
    private String uf;
    private String tipoConsulta;
    private List<Exame> exames = new ArrayList<>();
    private long idUltimoExame = 0;

    public Paciente() {
        this.nome = null;
    }

    public Paciente(String nome, String raca, String dataNascimento, String cpf, String rg, String sexo, String nacionalidade, String cep, String uf, String tipoConsulta) {
        this.nome = nome;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.sexo = sexo;
        this.nacionalidade = nacionalidade;
        this.cep = cep;
        this.uf = uf;
        this.tipoConsulta = tipoConsulta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String dataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getTipoConsulta() {
        return this.tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public List<Exame> getExames() {
        return this.exames;
    }

    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }

    public Exame addExame(Exame ex) throws Exception {
        ex.setId(++this.idUltimoExame);
        ex.setIdPaciente(this.getId());
        for (Exame exame : this.getExames()) {
            if(exame.getId() == ex.getId()){
                throw new Exception("Exame já cadastrado!");
            }
            
        }
        this.exames.add(ex);
        return ex;
    }
    
    public Exame procurarExame(long id) throws Exception{
        for(Exame ex : this.getExames()){
            if(ex.getId() == id){
                return ex;
            }
        }
        throw new Exception("Exame não encontrado!");
    }

}
