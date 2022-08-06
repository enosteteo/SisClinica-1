package br.ufpb.dcx.poo.sisclinica.models;

public class Consulta {

    private long id;
    private long idPaciente;
    private long idMedico;
    private String dataConsulta;
    private String dataRetorno;
    private boolean aberta;

    public Consulta() {

    }

    public Consulta(long idPaciente, long idMedico, String dataConsulta, String dataRetorno, boolean aberta) {
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.dataConsulta = dataConsulta;
        this.dataRetorno = dataRetorno;
        this.aberta = aberta;
    }

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(long idMedico) {
        this.idMedico = idMedico;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(String dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public boolean getAberta() {
        return aberta;
    }

    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
