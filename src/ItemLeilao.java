import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ItemLeilao{
    private int idItem;
    private Leilao leilao;
    private String descricaoItem;
    private double lanceMinimoItem;
    private boolean itemArrematado;
    private Lance lanceArrematante;

    public ItemLeilao(int idItem, Leilao leilao, String descricaoItem, double lanceMinimoItem, boolean itemArrematado, Lance lanceArrematante){
        this.idItem = idItem;
        this.leilao = leilao;
        this.descricaoItem = descricaoItem;
        this.lanceMinimoItem = lanceMinimoItem;
        this.itemArrematado = itemArrematado;
        this.lanceArrematante = lanceArrematante;
    }

    public int getIdItem(){
        return idItem;
    }

    public Leilao getLeilao(){
        return leilao;
    }

    public String getDescricaoItem(){
        return descricaoItem;
    }

    public double getLanceMinimoItem(){
        return lanceMinimoItem;
    }

    public boolean getItemArrematado(){
        return itemArrematado;
    }

    public Lance getLanceArrematante(){
        return lanceArrematante;
    }

    public void setIdItem(int idItem){
        this.idItem = idItem;
    }

    public void setLeilao(Leilao leilao){
        this.leilao = leilao;
    }

    public void setDescricaoItem(String descricaoItem){
        this.descricaoItem = descricaoItem;
    }

    public void setLanceMinimoItem(double lanceMinimoItem){
        this.lanceMinimoItem = lanceMinimoItem;
    }

    public void setItemArrematado(boolean itemArrematado){
        this.itemArrematado = itemArrematado;
    }

    public void setLanceArrematante(Lance lanceArrematante){
        this.lanceArrematante = lanceArrematante;
    }

    public boolean registrarItem() throws Exception {

    FileWriter fw = new FileWriter("itensLeilao.txt", true);
    BufferedWriter bw = new BufferedWriter(fw);

    int idLeilao;
    int idLance;

    if (leilao == null) {
        idLeilao = -1;
    } else {
        idLeilao = leilao.getIdLeilao();
    }

    if (lanceArrematante == null) {
        idLance = -1;
    } else {
        idLance = lanceArrematante.getIdLance();
    }

    String linha = idItem + ";" +
                   idLeilao + ";" +
                   descricaoItem + ";" +
                   lanceMinimoItem + ";" +
                   itemArrematado + ";" +
                   idLance;

    bw.write(linha);
    bw.newLine();
    bw.close();

    return true;
}


public ItemLeilao consultarItem(int idProcurado) throws Exception {

    FileReader fr = new FileReader("itensLeilao.txt");
    BufferedReader br = new BufferedReader(fr);

    String linha = br.readLine();

    while (linha != null) {

        String[] partes = linha.split(";");

        int idArquivo = Integer.parseInt(partes[0]);
        String descricaoArq = partes[2];
        double lanceMinArq = Double.parseDouble(partes[3]);
        boolean arrematadoArq = Boolean.parseBoolean(partes[4]);

        if (idArquivo == idProcurado) {
            br.close();
            return new ItemLeilao(idArquivo, null, descricaoArq, lanceMinArq, arrematadoArq, null);
        }

        linha = br.readLine();
    }

    br.close();
    return null;
}


public ArrayList<ItemLeilao> listarItens() throws Exception {

    ArrayList<ItemLeilao> lista = new ArrayList<ItemLeilao>();

    FileReader fr = new FileReader("itensLeilao.txt");
    BufferedReader br = new BufferedReader(fr);

    String linha = br.readLine();

    while (linha != null) {

        String[] partes = linha.split(";");

        int idArquivo = Integer.parseInt(partes[0]);
        String descricaoArq = partes[2];
        double lanceMinArq = Double.parseDouble(partes[3]);
        boolean arrematadoArq = Boolean.parseBoolean(partes[4]);

        ItemLeilao item = new ItemLeilao(idArquivo, null, descricaoArq, lanceMinArq, arrematadoArq, null);
        lista.add(item);

        linha = br.readLine();
    }

    br.close();
    return lista;
}


public void arrematarItem(Lance lance) throws Exception {
    this.itemArrematado = true;
    this.lanceArrematante = lance;
    registrarItem();
}


public void mostrar() {
    System.out.println("ID Item: " + idItem);
    System.out.println("Descrição: " + descricaoItem);
    System.out.println("Lance mínimo: " + lanceMinimoItem);
    System.out.println("Arrematado: " + itemArrematado);
    System.out.println("----------------------------------");
    }
}