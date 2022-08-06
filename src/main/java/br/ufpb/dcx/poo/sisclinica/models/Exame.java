package br.ufpb.dcx.poo.sisclinica.models;

public class Exame {

    private long id;
    private long idPaciente;
    private String dataExame;
    private double valor;
    private String dataResultado;
    
    
    public Exame(){
    }
    
    public Exame(long idPaciente, String dataExame, double valor, String dataResultado) {
        this.idPaciente = idPaciente;
        this.dataExame = dataExame;
        this.valor = valor;
        this.dataResultado = dataResultado;
    }

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDataExame() {
        return this.dataExame;
    }

    public void setDataExame(String dataExame) {
        this.dataExame = dataExame;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataResultado() {
        return this.dataResultado;
    }

    public void setDataResultado(String dataResultado) {
        this.dataResultado = dataResultado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
