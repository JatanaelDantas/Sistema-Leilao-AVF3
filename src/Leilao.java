import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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


    public ArrayList<Leilao> listarLeiloes() throws Exception {

        ArrayList<Leilao> lista = new ArrayList<Leilao>();

        File arquivo = new File("leiloes.txt");
        if (!arquivo.exists()) {
            return lista;
        }

        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);

        String linha = br.readLine();

        while (linha != null) {

            if (linha.trim().equals("")) {
                linha = br.readLine();
                continue;
            }

            String[] partes = linha.split(";");

            int id = Integer.parseInt(partes[0]);
            String dataIni = partes[1];
            String horaIni = partes[2];
            String dataFim = partes[3];
            String horaFim = partes[4];
            boolean status = Boolean.parseBoolean(partes[5]);

            Leilao l = new Leilao(id, dataIni, horaIni, dataFim, horaFim, status);
            lista.add(l);

            linha = br.readLine();
        }

        br.close();
        return lista;
    }


    public Leilao consultarLeilao(int idProcurado) throws Exception {

        File arquivo = new File("leiloes.txt");
        if (!arquivo.exists()) {
            return null;
        }

        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);

        String linha = br.readLine();

        while (linha != null) {

            if (linha.trim().equals("")) {
                linha = br.readLine();
                continue;
            }

            String[] partes = linha.split(";");

            int id = Integer.parseInt(partes[0]);

            if (id == idProcurado) {
                String dataIni = partes[1];
                String horaIni = partes[2];
                String dataFim = partes[3];
                String horaFim = partes[4];
                boolean status = Boolean.parseBoolean(partes[5]);

                br.close();
                return new Leilao(id, dataIni, horaIni, dataFim, horaFim, status);
            }

            linha = br.readLine();
        }

        br.close();
        return null;
    }

  
    public void mostrar() {
        System.out.println("Id leilão : " + idLeilao);
        System.out.println("Início    : " + dataInicioLeilao + " " + horaInicioLeilao);
        System.out.println("Fim       : " + dataFimLeilao + " " + horaFimLeilao);
        System.out.println("Status    : " + statusLeilao);
        System.out.println("----------------------------------");
    }
}
