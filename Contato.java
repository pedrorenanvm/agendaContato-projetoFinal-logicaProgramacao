import java.util.ArrayList;
import java.util.List;

public class Contato {
    private Long id;
    private String nome;
    private String sobreNome;
    private List<Telefone>  telefones;

    // Construtor
    public Contato(Long id, String nome, String sobreNome){
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.telefones = new ArrayList<>();
    }

    // Métodos Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void adicionarTelefone(Telefone telefone){
        telefones.add(telefone);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(id+" | "+nome+" | "+sobreNome);

        for (Telefone telefone : telefones){
            result.append(" | ").append(telefone);
        }
        return result.toString();
    }
    public boolean equals (Contato outroContato){
        if (this == outroContato)
            return true;
        if(outroContato == null || getClass() != outroContato.getClass())
            return false;
        return id.equals(outroContato.id);
    }
    public boolean equalsTelefone(Contato outroContato){
        if(this == outroContato)
            return true;
        if(outroContato == null || getClass() != outroContato.getClass())
            return false;
        return telefones.equals(outroContato.telefones);
    }
}
