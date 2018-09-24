package br.ufpb.dcx.poo.sisclinica.models;

public class Medico{

    private String nome;
    private String especialidade;
    private int crm;
    private String cpf;
    private int salaDeAtendimento;
    private double salario;
    private boolean plantao;
    private final int nivelDeAcesso;

    public Medico(String nome, String especialidade, int crm,
            String cpf, int salaDeAtendimento, double salario, boolean plantao) {
        this.setNome(nome);
        this.setEspecialidade(especialidade);
        this.setCrm(crm);
        this.setCpf(cpf);
        this.setSalaDeAtendimento(salaDeAtendimento);
        this.setSalario(salario);
        this.setPlantao(plantao);
        this.nivelDeAcesso = 1;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getSalaDeAtendimento() {
        return salaDeAtendimento;
    }

    public void setSalaDeAtendimento(int salaDeAtendimento) {
        this.salaDeAtendimento = salaDeAtendimento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean getPlantao() {
        return plantao;
    }

    public void setPlantao(boolean plantao) {
        this.plantao = plantao;
    }

    public int getNivelDeAcesso() {
        return nivelDeAcesso;
    }

}
