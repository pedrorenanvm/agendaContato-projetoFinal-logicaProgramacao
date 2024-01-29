public class Telefone {

    private Long id;
    private String ddd;
    private Long numero;
    private TipoTelefone tipo;

    public Telefone(Long id, String ddd, Long numero, TipoTelefone tipo){
        this.id = id;
        this.ddd = ddd;
        this.numero = numero;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public String getDdd() {
        return ddd;
    }

    public Long getNumero() {
        return numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return tipo + ": " + ddd + " " + numero;
    }
}
