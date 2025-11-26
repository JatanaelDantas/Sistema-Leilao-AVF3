import java.io.BufferedReader;
import java.io.BufferedWriter;
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


public Lance consultarLance(int idProcurado) throws Exception {

    FileReader fr = new FileReader("lances.txt");
    BufferedReader br = new BufferedReader(fr);

    String linha = br.readLine();

    while (linha != null) {

        String[] partes = linha.split(";");

        int idArquivo = Integer.parseInt(partes[0]);
        int idParticipanteArq = Integer.parseInt(partes[1]); 
        int idItemArq = Integer.parseInt(partes[2]);         
        double valorArquivo = Double.parseDouble(partes[3]);
        String dataArquivo = partes[4];
        String horaArquivo = partes[5];

        if (idArquivo == idProcurado) {
            br.close();
            return new Lance(idArquivo, null, null, valorArquivo, dataArquivo, horaArquivo);
        }

        linha = br.readLine();
    }

    br.close();
    return null;
}


public ArrayList<Lance> listarLances() throws Exception {

    ArrayList<Lance> lista = new ArrayList<Lance>();

    FileReader fr = new FileReader("lances.txt");
    BufferedReader br = new BufferedReader(fr);

    String linha = br.readLine();

    while (linha != null) {

        String[] partes = linha.split(";");

        int idArquivo = Integer.parseInt(partes[0]);
        int idParticipanteArq = Integer.parseInt(partes[1]); 
        int idItemArq = Integer.parseInt(partes[2]);       
        double valorArquivo = Double.parseDouble(partes[3]);
        String dataArquivo = partes[4];
        String horaArquivo = partes[5];

        Lance lance = new Lance(idArquivo, null, null, valorArquivo, dataArquivo, horaArquivo);
        lista.add(lance);

        linha = br.readLine();
    }

    br.close();
    return lista;
}


public void mostrar() {
    System.out.println("ID do Lance: " + idLance);
    System.out.println("Valor: " + valorLance);
    System.out.println("Data: " + dataLance);
    System.out.println("Hora: " + horaLance);
    System.out.println("----------------------------------");
}

}
