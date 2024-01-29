import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();

        while (true){
            System.out.println("\n##################");
            System.out.println("##### AGENDA #####");
            System.out.println("##################");
            System.out.println(">>>> Contatos <<<<");
            agenda.visualizarContatos();
            System.out.println(">>>> Menu <<<<");
            System.out.println("1 - Adicionar Contato");
            System.out.println("2 - Remover Contato");
            System.out.println("3 - Editar Contato");
            System.out.println("4 - Sair");

            int escolha = scanner.nextInt();
            switch (escolha){
                case 1:
                    System.out.println("Digite o nome do contato:");
                    scanner.nextLine();// Consumir a quebra de linha pendente
                    String nome = scanner.nextLine();
                    System.out.println("Digite o sobrenome do contato:");
                    String sobreNome = scanner.nextLine();

                    System.out.println("Digite o ID do contato:");
                    Long id = scanner.nextLong();

                    Contato novoContato = new Contato(id,nome,sobreNome);

                    System.out.println("Digite a quantidade de telefones:");
                    int qtdTelefones = scanner.nextInt();

                    for(int i=0; i < qtdTelefones; i++){
                        System.out.println("Digite o tipo do telefone " + (i + 1) + ":");
                        System.out.println("1 - Comercial");
                        System.out.println("2 - Residencial");
                        System.out.println("3 - Celular");
                        int tipoTelefoneEscolha = scanner.nextInt();

                        TipoTelefone tipoTelefone = null;
                        switch (tipoTelefoneEscolha){
                            case 1:
                                tipoTelefone = TipoTelefone.COMERCIAL;
                                break;
                            case 2:
                                tipoTelefone = tipoTelefone.RESIDENCIAL;
                                break;
                            case 3:
                                tipoTelefone = tipoTelefone.CELULAR;
                                break;
                            default:
                                System.out.println("Tipo de telefone inválido.");
                                tipoTelefone = tipoTelefone.CELULAR;
                                break;
                        }

                        System.out.println("Digite o DDD do telefone "+ (i + 1) + ":");
                        String ddd = scanner.next();
                        System.out.println("Digite o número de telefone "+ (i + 1) + ":");
                        Long numero = scanner.nextLong();

                        if(!agenda.verificarTelefoneExistente(ddd,numero,tipoTelefone)){
                            Telefone telefone = new Telefone((long) (i+1), ddd, numero,tipoTelefone);
                            novoContato.adicionarTelefone(telefone);
                        }else {
                            System.out.println("Telefone já cadastrado. Não é possível adicionar.");
                        }
                    }
                    agenda.adicionarContato(novoContato);
                    System.out.println("Contato adicionado com sucesso!");
                    break;
                case 2:
                    System.out.println("Digite o ID do contato a ser removido:");
                    Long idRemover = scanner.nextLong();
                    agenda.removerContato(idRemover);
                    break;
                case 3:
                    System.out.println("Digite o ID do contato a ser editado:");
                    Long idEditar = scanner.nextLong();
                    System.out.println("Digite o novo nome:");
                    scanner.nextLine(); // Consumir linha
                    String novoNome = scanner.nextLine();
                    System.out.println("Digite seu sobrenome:");
                    String novoSobreNome = scanner.nextLine();
                    agenda.editarContato(idEditar,novoNome,novoSobreNome);
                    break;
                case 4:
                    System.out.println("Saindo da aplicação. Até mais!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
