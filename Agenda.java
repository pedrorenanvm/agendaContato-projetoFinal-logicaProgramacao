import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contato> contatos;
    private static final String ARQUIVO_DADOS = "dados.txt";

    public Agenda(){
        this.contatos = new ArrayList<>();
        carregarDados();
    }

    private void carregarDados(){
       try(BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DADOS))){
        String linha;
        while ((linha = reader.readLine()) != null){
            String[] dados = linha.split(",");
            Long id = Long.parseLong(dados[0]);
            String nome = dados[1];
            String sobreNome = dados[2];

            Contato contato = new Contato(id, nome,sobreNome);
            contatos.add(contato);

            for(int i = 3; i < dados.length; i +=3){
                String  tipoTelefoneStr = dados[i];
                String  ddd = dados[i+1];
                Long numero = Long.parseLong(dados[i+2]);
                TipoTelefone tipoTelefone = TipoTelefone.valueOf(tipoTelefoneStr);

                Telefone telefone = new Telefone((long) (i / 3), ddd, numero, tipoTelefone);
                contato.adicionarTelefone(telefone);
            }
        }
       } catch (IOException e) {
           System.out.println("Erro ao carregar dados: " + e.getMessage());
       }
    }
    private void salvarDados(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DADOS))){
            for (Contato contato : contatos){
                writer.write(contato.getId()+ ","  + contato.getNome() + "," +contato.getSobreNome());
                for (Telefone telefone : contato.getTelefones()){
                    writer.write("," + telefone.getTipo() + "," + telefone.getDdd() + "," + telefone.getNumero());
                }

                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " +e.getMessage());
        }
    }
    public boolean verificarIdExistente (Long id){
        for (Contato contatoExistente : contatos){
            if(contatoExistente.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    public boolean verificarContatoExistente(Contato novoContato){
        for(Contato contatoExistente : contatos){
            if(contatoExistente.equals(novoContato)){
                return true;
            }
        }
        return false;
    }
    public boolean verificarTelefoneExistente(Contato novoContato){
        for(Telefone novoTelefone : novoContato.getTelefones()){
            for(Contato contato : contatos){
                for(Telefone telefone : contato.getTelefones()){
                    if(telefone.getDdd().equals(novoTelefone.getDdd())&& telefone.getNumero().equals(novoTelefone.getNumero()) && telefone.getTipo() == novoTelefone.getTipo()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void adicionarContato(Contato novoContato){
        if(!verificarTelefoneExistente(novoContato)){
            if (!verificarIdExistente(novoContato.getId()) && !verificarContatoExistente(novoContato)){
                contatos.add(novoContato);
                salvarDados();
                System.out.println("Contato adicionado!");
            }else {
                System.out.println("ID duplicado ou telefone já cadastrado. Não foi possível adicionar.");
            }
        }else {
            System.out.println("Telefone já cadastrado. Nõa foi possível adicionar o contato ");
        }
    }
    public void removerContato(Long id){
        Contato contatoRemover = buscarContatoPorId(id);
        if(contatoRemover != null){
            contatos.remove(contatoRemover);
            salvarDados();
        }else{
            System.out.println("Contato não encontrado");
        }
    }
    public void visualizarContatos(){
        System.out.println("#### Contatos ####");
        System.out.println("Id | Nome");
        for (Contato contato: contatos) {
            System.out.println(contato);
        }
    }
    public void editarContato(Long id, String novoNome, String novoSobreNome){
        Contato contatoEditar = buscarContatoPorId(id);
        if(contatoEditar != null){
            contatoEditar.setNome(novoNome);
            contatoEditar.setSobreNome(novoSobreNome);
            salvarDados();
        }else {
            System.out.println("Contato não encontrado");
        }
    }
    public Contato buscarContatoPorId(Long id){
        for(Contato contato : contatos){
            if(contato.getId().equals(id)){
                return contato;
            }
        }
        return null;
    }
    public boolean verificarTelefoneExistente(String ddd, Long numero, TipoTelefone tipo){
        for (Contato contato: contatos) {
            for (Telefone telefone : contato.getTelefones()) {
                if(telefone.getDdd().equals(ddd) && telefone.getNumero().equals(numero) && telefone.getTipo() == tipo){
                    return true;
                }
            }
        }
        return false;
    }
}
