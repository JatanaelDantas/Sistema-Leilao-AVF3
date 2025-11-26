import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
                Scanner sc = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("====== MENU ======");
            System.out.println("1  - Cadastrar participante");
            System.out.println("2  - Login participante");
            System.out.println("3  - Listar participantes");
            System.out.println("4  - Cadastrar leilão");
            System.out.println("5  - Listar leilões");
            System.out.println("6  - Cadastrar item de leilão");
            System.out.println("7  - Listar itens");
            System.out.println("8  - Registrar lance");
            System.out.println("9  - Listar lances");
            System.out.println("10 - Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1: // Cadastrar participante
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

                case 2: // Login participante
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

                case 3: // Listar participantes
                    Participante auxP2 = new Participante(0, "", "", "", "", "", "");
                    ArrayList<Participante> listaP = auxP2.listarParticipantes();
                    for (Participante part : listaP) {
                        part.mostrar();
                    }
                    break;

                case 4: // Cadastrar leilão
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

                case 5: // Listar leilões
                    Leilao auxL = new Leilao(0, "", "", "", "", false);
                    ArrayList<Leilao> listaL = auxL.listarLeiloes();
                    for (Leilao l : listaL) {
                        l.mostrar();
                    }
                    break;

                case 6: // Cadastrar item
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

                case 7: // Listar itens
                    ItemLeilao auxItem = new ItemLeilao(0, null, "", 0.0, false, null);
                    ArrayList<ItemLeilao> listaItens = auxItem.listarItens();
                    for (ItemLeilao it : listaItens) {
                        it.mostrar();
                    }
                    break;

                case 8: // Registrar lance
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

                case 9: // Listar lances
                    Lance auxLance = new Lance(0, null, null, 0.0, "", "");
                    ArrayList<Lance> listaLances = auxLance.listarLances();
                    for (Lance lan : listaLances) {
                        lan.mostrar();
                    }
                    break;

                case 10:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.\n");
                    break;
            }

        } while (opcao != 10);

        sc.close();

    }
}
