import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Lance {
    private int idLance;
    private Participante participante;
    private ItemLeilao itemLeilao;
    private double valorLance;
    private String dataLance;
    private String horaLance;

    public Lance(int idLance, Participante participante, ItemLeilao itemLeilao, double valorLance, String dataLance, String horaLance){
        this.idLance = idLance;
        this.participante = participante;
        this.itemLeilao = itemLeilao;
        this.valorLance = valorLance;
        this.dataLance = dataLance;
        this.horaLance = horaLance;
    }

    public int getIdLance(){
        return idLance;
    }

    public Participante getParticipante(){
        return participante;
    }

    public ItemLeilao getItemLeilao(){
        return itemLeilao;
    }

    public double getValorLance(){
        return valorLance;
    }

    public String getDataLance(){
        return dataLance;
    }

    public String getHoraLance(){
        return horaLance;
    }

    public void setIdLance(int idLance){
        this.idLance = idLance;
    }

    public void setParticipante(Participante participante){
        this.participante = participante;
    }

    public void setItemLeilao(ItemLeilao itemLeilao){
        this.itemLeilao = itemLeilao;
    }

    public void setValorLance(double valorLance){
        this.valorLance = valorLance;
    }

    public void setDataLance(String dataLance){
        this.dataLance = dataLance;
    }

    public void setHoraLance(String horaLance){
        this.horaLance = horaLance;
    }


 public boolean registrarLance() throws Exception {

        FileWriter fw = new FileWriter("lances.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);

        int idItem;
        if (itemLeilao == null) {
            idItem = -1;
        } else {
            idItem = itemLeilao.getIdItem();
        }

        int idParticipante;
        if (participante == null) {
            idParticipante = -1;
        } else {
            idParticipante = participante.getIdParticipante();
        }

        String linha = idLance + ";" +
                       idParticipante + ";" +
                       idItem + ";" +
                       valorLance + ";" +
                       dataLance + ";" +
                       horaLance;

        bw.write(linha);
        bw.newLine();
        bw.close();

        return true;
    }


    public ArrayList<Lance> listarLances() throws Exception {

        ArrayList<Lance> lista = new ArrayList<Lance>();

        File arquivo = new File("lances.txt");
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

            int idArquivo = Integer.parseInt(partes[0]);
            int idPartArq = Integer.parseInt(partes[1]);
            int idItemArq = Integer.parseInt(partes[2]);
            double valorArq = Double.parseDouble(partes[3]);
            String dataArq = partes[4];
            String horaArq = partes[5];

            Participante p = new Participante(idPartArq, "", "", "", "", "", "");
            ItemLeilao it = new ItemLeilao(idItemArq, null, "", 0.0, false, null);

            Lance l = new Lance(idArquivo, p, it, valorArq, dataArq, horaArq);
            lista.add(l);

            linha = br.readLine();
        }

        br.close();
        return lista;
    }


    public Lance consultarLance(int idProcurado) throws Exception {

        File arquivo = new File("lances.txt");
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

            int idArquivo = Integer.parseInt(partes[0]);

            if (idArquivo == idProcurado) {

                int idPartArq = Integer.parseInt(partes[1]);
                int idItemArq = Integer.parseInt(partes[2]);
                double valorArq = Double.parseDouble(partes[3]);
                String dataArq = partes[4];
                String horaArq = partes[5];

                br.close();

                Participante p = new Participante(idPartArq, "", "", "", "", "", "");
                ItemLeilao it = new ItemLeilao(idItemArq, null, "", 0.0, false, null);

                return new Lance(idArquivo, p, it, valorArq, dataArq, horaArq);
            }

            linha = br.readLine();
        }

        br.close();
        return null;
    }

    
    public void mostrar() {
        System.out.println("ID do Lance: " + idLance);
        System.out.println("Valor: " + valorLance);
        System.out.println("Data: " + dataLance);
        System.out.println("Hora: " + horaLance);
        System.out.println("----------------------------------");
    }
}
