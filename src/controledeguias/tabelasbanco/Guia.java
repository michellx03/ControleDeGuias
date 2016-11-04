package controledeguias.tabelasbanco;

public class Guia {
    private Long id_guia;
    private Long numero_guia_principal;
    private String cirugia;
    private String localizacao;
    private String observacao;
    private String status_da_guia;
    private String medico_da_guia;
    private String data_guia;
    private String data_prontuario;
    private Long numero_do_prontuario;
    private Long id_paciente;
    
    //GETTERS AND SETTERS
    public Long getId_guia() {
        return id_guia;
    }

    public void setId_guia(Long id_guia) {
        this.id_guia = id_guia;
    }

    public Long getNumero_guia_principal() {
        return numero_guia_principal;
    }

    public void setNumero_guia_principal(Long numero_da_guia_principal) {
        this.numero_guia_principal = numero_da_guia_principal;
    }

    public String getCirugia() {
        return cirugia;
    }

    public void setCirugia(String cirugia) {
        this.cirugia = cirugia;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus_da_guia() {
        return status_da_guia;
    }

    public void setStatus_da_guia(String status_da_guia) {
        this.status_da_guia = status_da_guia;
    }

    public String getMedico_da_guia() {
        return medico_da_guia;
    }

    public void setMedico_da_guia(String medico_da_guia) {
        this.medico_da_guia = medico_da_guia;
    }

    public String getData_guia() {
        return data_guia;
    }

    public void setData_guia(String data_guia) {
        this.data_guia = data_guia;
    }

    public String getData_prontuario() {
        return data_prontuario;
    }

    public void setData_prontuario(String data_prontuario) {
        this.data_prontuario = data_prontuario;
    }

    public Long getNumero_do_prontuario() {
        return numero_do_prontuario;
    }

    public void setNumero_do_prontuario(Long numero_do_prontuario) {
        this.numero_do_prontuario = numero_do_prontuario;
    }

    public Long getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(Long id_paciente) {
        this.id_paciente = id_paciente;
    }    
}
