import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Leilao {
    private int idLeilao;
    private String dataInicioLeilao;
    private String horaInicioLeilao;
    private String dataFimLeilao;
    private String horaFimLeilao;
    private boolean statusLeilao;

    public Leilao(int idLeilao, String dataInicioLeilao, String horaInicioLeilao, String dataFimLeilao, String horaFimLeilao, boolean statusLeilao){
        this.idLeilao = idLeilao;
        this.dataInicioLeilao = dataInicioLeilao;
        this.horaInicioLeilao = horaInicioLeilao;
        this.dataFimLeilao = dataFimLeilao;
        this.horaFimLeilao = horaFimLeilao;
        this.statusLeilao = statusLeilao;
    }

    public int getIdLeilao(){
        return idLeilao;
    }

    public String getDataInicioLeilao(){
        return dataInicioLeilao;
    }

    public String getHoraInicioLeilao(){
        return horaInicioLeilao;
    }

    public String getDataFimLeilao(){
        return dataFimLeilao;
    }
    
    public String getHoraFimLeilao(){
        return horaFimLeilao;
    }

    public boolean getStatusLeilao(){
        return statusLeilao;
    }
  
    public void setIdLeilao(int idLeilao){
        this.idLeilao = idLeilao;
    }

    public void setDataInicioLeilao(String dataInicioLeilao){
        this.dataInicioLeilao = dataInicioLeilao;
    }

    public void setHoraInicioLeilao(String horaInicioLeilao){
        this.horaInicioLeilao = horaInicioLeilao;
    }

    public void setDataFimLeilao(String dataFimLeilao){
        this.dataFimLeilao = dataFimLeilao;
    }

    public void setHoraFimLeilao(String horaFimLeilao){
        this.horaFimLeilao = horaFimLeilao;
    }

    public void setStatusLeilao(boolean statusLeilao){
        this.statusLeilao = statusLeilao;
    }

    public boolean registrarLeilao() throws Exception {

        FileWriter fw = new FileWriter("leiloes.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);

        String linha = idLeilao + ";" +
                       dataInicioLeilao + ";" +
                       horaInicioLeilao + ";" +
                       dataFimLeilao + ";" +
                       horaFimLeilao + ";" +
                       statusLeilao;

        bw.write(linha);
        bw.newLine();
        bw.close();

        return true;
    }

      public Leilao consultarLeilao(int idProcurado) throws Exception {

        FileReader fr = new FileReader("leiloes.txt");
        BufferedReader br = new BufferedReader(fr);

        String linha = br.readLine();

        while (linha != null) {

            String[] partes = linha.split(";");

            int id = Integer.parseInt(partes[0]);
            String dataIni  = partes[1];
            String horaIni  = partes[2];
            String dataFim  = partes[3];
            String horaFim  = partes[4];
            boolean status  = Boolean.parseBoolean(partes[5]);

            if (id == idProcurado) {
                br.close();
                return new Leilao(id, dataIni, horaIni, dataFim, horaFim, status);
            }

            linha = br.readLine();
        }

        br.close();
        return null;
    }

    public ArrayList<Leilao> listarLeiloes() throws Exception {

    ArrayList<Leilao> lista = new ArrayList<Leilao>();

    FileReader fr = new FileReader("leiloes.txt");
    BufferedReader br = new BufferedReader(fr);

    String linha = br.readLine();

    while (linha != null) {

        String[] partes = linha.split(";");

        int idLeilaoArquivo     = Integer.parseInt(partes[0]);
        String dataInicioArq    = partes[1];
        String horaInicioArq    = partes[2];
        String dataFimArq       = partes[3];
        String horaFimArq       = partes[4];
        boolean statusArq       = Boolean.parseBoolean(partes[5]);

        Leilao l = new Leilao(idLeilaoArquivo, dataInicioArq, horaInicioArq,
                              dataFimArq, horaFimArq, statusArq);
        lista.add(l);

        linha = br.readLine();
    }

    br.close();
    return lista;
}


public boolean iniciarLeilao(String dataInicio, String horaInicio) throws Exception {
    this.dataInicioLeilao = dataInicio;
    this.horaInicioLeilao = horaInicio;
    this.statusLeilao = true;
    return registrarLeilao();
}


public boolean finalizarLeilao(String dataFim, String horaFim) throws Exception {
    this.dataFimLeilao = dataFim;
    this.horaFimLeilao = horaFim;
    this.statusLeilao = true;
    return registrarLeilao();
}


public void mostrar() {
    System.out.println("ID Leilão: " + idLeilao);
    System.out.println("Início: " + dataInicioLeilao + " " + horaInicioLeilao);
    System.out.println("Fim: " + dataFimLeilao + " " + horaFimLeilao);
    System.out.println("Status (boolean): " + statusLeilao);
    System.out.println("--------------------------------");
}
}
