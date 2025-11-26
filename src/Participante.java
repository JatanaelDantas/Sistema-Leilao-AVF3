import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Participante{
    private int idParticipante;
    private String nomeParticipante;
    private String loginParticipante;
    private String senhaParticipante;
    private String emailParticipante;
    private String enderecoParticipante;
    private String telefoneParticipante;

    public Participante(int idParticipante, String nomeParticipante, String loginParticipante,  String senhaParticipante,  String emailParticipante,  String enderecoParticipante, String telefoneParticipante){
        this.idParticipante = idParticipante;
        this.nomeParticipante = nomeParticipante;
        this.loginParticipante = loginParticipante;
        this.senhaParticipante = senhaParticipante;
        this.emailParticipante = emailParticipante;
        this.enderecoParticipante = enderecoParticipante;
        this.telefoneParticipante = telefoneParticipante;
    }

    public int getIdParticipante(){
        return idParticipante;
    }

    public String getNomeParticipante(){
        return nomeParticipante;
    }

    public String getLoginParticipante(){
        return loginParticipante;
    }
   
    public String getSenhaParticipante(){
        return senhaParticipante;
    }
   
    public String getEmailParticipante(){
        return emailParticipante;
    }
    
    public String getEnderecoParticipante(){
        return enderecoParticipante;
    }
   
    public String getTelefoneParticipante(){
        return telefoneParticipante;
    }

    public void setIdParticipante(int idParticipante){
        this.idParticipante = idParticipante;
    }

    public void setNomeParticipante(String nomeParticipante){
        this.nomeParticipante = nomeParticipante;
    }

    public void setLoginParticipante(String loginParticipante){
        this.loginParticipante = loginParticipante;
    }

    public void setSenhaParticipante(String senhaParticipante){
        this.senhaParticipante = senhaParticipante;
    }

    public void setEmailParticipante(String emailParticipante){
        this.emailParticipante = emailParticipante;
    }

    public void setEnderecoParticipante(String enderecoParticipante){
        this.enderecoParticipante = enderecoParticipante;
    }

    public void setTelefoneParticipante(String telefoneParticipante){
        this.telefoneParticipante = telefoneParticipante;
    }

     public boolean registrarParticipante() throws Exception {

        FileWriter fw = new FileWriter("participantes.txt", true); 
        BufferedWriter bw = new BufferedWriter(fw);
   
        String linha = idParticipante + ";" +
                       nomeParticipante + ";" +
                       loginParticipante + ";" +
                       senhaParticipante + ";" +
                       emailParticipante + ";" +
                       enderecoParticipante + ";" +
                       telefoneParticipante;

        bw.write(linha);
        bw.newLine();  
        bw.close();    

        return true;   
    }

   public Participante loginParticipante(String login, String senha) throws Exception {
    
    FileReader fr = new FileReader("participantes.txt");
    BufferedReader br = new BufferedReader(fr);

    String linha = br.readLine();

    while (linha != null) {

        String[] partes = linha.split(";");

        String loginArq = partes[2];
        String senhaArq = partes[3];

        if (loginArq.equals(login) && senhaArq.equals(senha)) {

            int id = Integer.parseInt(partes[0]);
            String nome = partes[1];
            String email = partes[4];
            String end = partes[5];
            String tel = partes[6];

            br.close();
            return new Participante(id, nome, loginArq, senhaArq, email, end, tel);
        }

        linha = br.readLine();
    }

    br.close();
    return null;
}

public ArrayList<Participante> listarParticipantes() throws Exception {

    ArrayList<Participante> lista = new ArrayList<Participante>();

    FileReader fr = new FileReader("participantes.txt");
    BufferedReader br = new BufferedReader(fr);

    String linha = br.readLine();

    while (linha != null) {

        String[] partes = linha.split(";");

        int id = Integer.parseInt(partes[0]);
        String nome = partes[1];
        String login = partes[2];
        String senha = partes[3];
        String email = partes[4];
        String end = partes[5];
        String tel = partes[6];

        lista.add(new Participante(id, nome, login, senha, email, end, tel));

        linha = br.readLine();
    }

    br.close();

    return lista;
}

 public void mostrar() {
        System.out.println("Id: " + idParticipante);
        System.out.println("Nome: " + nomeParticipante);
        System.out.println("Login: " + loginParticipante);
        System.out.println("Email: " + emailParticipante);
        System.out.println("Endereço: " + enderecoParticipante);
        System.out.println("Telefone: " + telefoneParticipante);
        System.out.println("--------------------------------");
    }
}