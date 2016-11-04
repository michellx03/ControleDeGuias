package controledeguias.tabelasbanco;

public class Paciente {
    
    private Long id_paciente;
    private String nome;
    private Long matricula_no_convenio;
    private Long idade;
    private String convenio;
    
    public String getConvenio() {
        return convenio;
    }

    //GETTER AND SETTERS
    public void setConvenio(String convenio) {    
        this.convenio = convenio;
    }

    public Long getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(Long id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getMatricula_no_convenio() {
        return matricula_no_convenio;
    }

    public void setMatricula_no_convenio(Long matricula_no_convenio) {
        this.matricula_no_convenio = matricula_no_convenio;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }
    
}
