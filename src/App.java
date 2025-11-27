import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("====== MENU DO SISTEMA DE LEILÃO ======");
            System.out.println("1  - Cadastrar participante");
            System.out.println("2  - Login participante");
            System.out.println("3  - Listar participantes");
            System.out.println("4  - Cadastrar leilão");
            System.out.println("5  - Listar leilões");
            System.out.println("6  - Cadastrar item de leilão");
            System.out.println("7  - Listar itens");
            System.out.println("8  - Registrar lance");
            System.out.println("9  - Listar lances");
            System.out.println("10 - Arrematar item");
            System.out.println("11 - Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {

                case 1: // cadastrar participante
                    System.out.print("Id do participante: ");
                    int idP = Integer.parseInt(sc.nextLine());
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Login: ");
                    String login = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();

                    Participante p = new Participante(idP, nome, login, senha, email, endereco, telefone);
                    p.registrarParticipante();
                    System.out.println("Participante cadastrado!\n");
                    break;

                case 2: // login
                    System.out.print("Login: ");
                    String loginDig = sc.nextLine();
                    System.out.print("Senha: ");
                    String senhaDig = sc.nextLine();

                    Participante auxP = new Participante(0, "", "", "", "", "", "");
                    Participante logado = auxP.loginParticipante(loginDig, senhaDig);

                    if (logado != null) {
                        System.out.println("Login efetuado! Bem-vindo, " + logado.getNomeParticipante() + "\n");
                    } else {
                        System.out.println("Login ou senha inválidos.\n");
                    }
                    break;

                case 3: // listar participantes
                    Participante auxP2 = new Participante(0, "", "", "", "", "", "");
                    ArrayList<Participante> listaP = auxP2.listarParticipantes();
                    for (Participante part : listaP) {
                        part.mostrar();
                    }
                    break;

                case 4: // cadastrar leilão
                    System.out.print("Id do leilão: ");
                    int idL = Integer.parseInt(sc.nextLine());
                    System.out.print("Data início (dd/mm/aaaa): ");
                    String dataIni = sc.nextLine();
                    System.out.print("Hora início (hh:mm): ");
                    String horaIni = sc.nextLine();
                    System.out.print("Data fim (dd/mm/aaaa): ");
                    String dataFim = sc.nextLine();
                    System.out.print("Hora fim (hh:mm): ");
                    String horaFim = sc.nextLine();

                    Leilao leilao = new Leilao(idL, dataIni, horaIni, dataFim, horaFim, false);
                    leilao.registrarLeilao();
                    System.out.println("Leilão cadastrado!\n");
                    break;

                case 5: // listar leilões
                    Leilao auxL = new Leilao(0, "", "", "", "", false);
                    ArrayList<Leilao> listaL = auxL.listarLeiloes();
                    for (Leilao l : listaL) {
                        l.mostrar();
                    }
                    break;

                case 6: // cadastrar item
                    System.out.print("Id do item: ");
                    int idItem = Integer.parseInt(sc.nextLine());
                    System.out.print("Id do leilão: ");
                    int idLeilao = Integer.parseInt(sc.nextLine());
                    System.out.print("Descrição do item: ");
                    String desc = sc.nextLine();
                    System.out.print("Lance mínimo: ");
                    double lanceMin = Double.parseDouble(sc.nextLine());

                    Leilao leilaoItem = new Leilao(idLeilao, "", "", "", "", false);
                    ItemLeilao item = new ItemLeilao(idItem, leilaoItem, desc, lanceMin, false, null);
                    item.registrarItem();
                    System.out.println("Item cadastrado!\n");
                    break;

                case 7: // listar itens
                    ItemLeilao auxItemLista = new ItemLeilao(0, null, "", 0.0, false, null);
                    ArrayList<ItemLeilao> listaItens = auxItemLista.listarItens();
                    for (ItemLeilao it : listaItens) {
                        it.mostrar();
                    }
                    break;

                case 8: // registrar lance
                    System.out.print("Id do lance: ");
                    int idLance = Integer.parseInt(sc.nextLine());
                    System.out.print("Id do participante: ");
                    int idPart = Integer.parseInt(sc.nextLine());
                    System.out.print("Id do item: ");
                    int idItemLance = Integer.parseInt(sc.nextLine());
                    System.out.print("Valor do lance: ");
                    double valor = Double.parseDouble(sc.nextLine());
                    System.out.print("Data do lance (dd/mm/aaaa): ");
                    String dataLance = sc.nextLine();
                    System.out.print("Hora do lance (hh:mm): ");
                    String horaLance = sc.nextLine();

                    Participante partLance = new Participante(idPart, "", "", "", "", "", "");
                    ItemLeilao itemLance = new ItemLeilao(idItemLance, null, "", 0.0, false, null);
                    Lance lance = new Lance(idLance, partLance, itemLance, valor, dataLance, horaLance);
                    lance.registrarLance();
                    System.out.println("Lance registrado!\n");
                    break;

                case 9: // listar lances
                    Lance auxLanceLista = new Lance(0, null, null, 0.0, "", "");
                    ArrayList<Lance> listaLances = auxLanceLista.listarLances();
                    for (Lance lan : listaLances) {
                        lan.mostrar();
                    }
                    break;

                case 10: // arrematar item
                    System.out.print("Id do item que deseja arrematar: ");
                    int idItemArrematar = Integer.parseInt(sc.nextLine());

                    ItemLeilao auxItemArremate = new ItemLeilao(0, null, "", 0.0, false, null);
                    ArrayList<ItemLeilao> itens = auxItemArremate.listarItens();

                    ItemLeilao itemEscolhido = null;
                    for (ItemLeilao it : itens) {
                        if (it.getIdItem() == idItemArrematar) {
                            itemEscolhido = it;
                            break;
                        }
                    }

                    if (itemEscolhido == null) {
                        System.out.println("Item não encontrado.\n");
                        break;
                    }

                    Lance auxLanceArremate = new Lance(0, null, null, 0.0, "", "");
                    ArrayList<Lance> lances = auxLanceArremate.listarLances();

                    Lance lanceVencedor = null;
                    double maiorValor = 0.0;

                    for (Lance lan : lances) {
                        if (lan.getItemLeilao() != null &&
                            lan.getItemLeilao().getIdItem() == idItemArrematar &&
                            lan.getValorLance() >= maiorValor) {

                            maiorValor = lan.getValorLance();
                            lanceVencedor = lan;
                        }
                    }

                    if (lanceVencedor == null) {
                        System.out.println("Nenhum lance encontrado para este item.\n");
                    } else {
                        itemEscolhido.arrematarItem(lanceVencedor);
                        System.out.println("Item arrematado com o lance de valor: " +
                                           lanceVencedor.getValorLance() + "\n");
                    }
                    break;

                case 11:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.\n");
                    break;
            }

        } while (opcao != 11);

        sc.close();
    }
}
