package br.ufpb.dcx.poo.sisclinica.models;

/**
 *
 * @author nikni
 */
public class Consulta {
    
    private Paciente paciente;
    private Medico medico;
    private String dataConsulta;
    private String dataRetorno;
    private boolean aberta;

    public Consulta(Paciente paciente, Medico medico, String dataConsulta, String dataRetorno, boolean aberta) {
        this.paciente = paciente;
        this.medico = medico;
        this.dataConsulta = dataConsulta;
        this.dataRetorno = dataRetorno;
        this.aberta = aberta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
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
    
    
}
