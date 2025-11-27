import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

        int idLeilaoArq;
        if (leilao == null) {
            idLeilaoArq = -1;
        } else {
            idLeilaoArq = leilao.getIdLeilao();
        }

        int idLanceArrem;
        if (lanceArrematante == null) {
            idLanceArrem = -1;
        } else {
            idLanceArrem = lanceArrematante.getIdLance();
        }

        String linha = idItem + ";" +
                       idLeilaoArq + ";" +
                       descricaoItem + ";" +
                       lanceMinimoItem + ";" +
                       itemArrematado + ";" +
                       idLanceArrem;

        bw.write(linha);
        bw.newLine();
        bw.close();

        return true;
    }


    public ArrayList<ItemLeilao> listarItens() throws Exception {

        ArrayList<ItemLeilao> lista = new ArrayList<ItemLeilao>();

        File arquivo = new File("itensLeilao.txt");
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
            int idLeilaoArq = Integer.parseInt(partes[1]);
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


    public ItemLeilao consultarItem(int idProcurado) throws Exception {

        File arquivo = new File("itensLeilao.txt");
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
                String descricaoArq = partes[2];
                double lanceMinArq = Double.parseDouble(partes[3]);
                boolean arrematadoArq = Boolean.parseBoolean(partes[4]);
                br.close();
                return new ItemLeilao(idArquivo, null, descricaoArq, lanceMinArq, arrematadoArq, null);
            }

            linha = br.readLine();
        }

        br.close();
        return null;
    }

  
    public void arrematarItem(Lance lance) throws Exception {

        ArrayList<ItemLeilao> itens = listarItens();

        for (ItemLeilao it : itens) {
            if (it.getIdItem() == this.idItem) {
                it.setItemArrematado(true);
                it.setLanceArrematante(lance);
            }
        }

        FileWriter fw = new FileWriter("itensLeilao.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        for (ItemLeilao it : itens) {

            int idItemArq = it.getIdItem();
            int idLeilaoArq;
            if (it.getLeilao() == null) {
                idLeilaoArq = -1;
            } else {
                idLeilaoArq = it.getLeilao().getIdLeilao();
            }

            int idLanceArq;
            if (it.getLanceArrematante() == null) {
                idLanceArq = -1;
            } else {
                idLanceArq = it.getLanceArrematante().getIdLance();
            }

            String linha = idItemArq + ";" +
                           idLeilaoArq + ";" +
                           it.getDescricaoItem() + ";" +
                           it.getLanceMinimoItem() + ";" +
                           it.getItemArrematado() + ";" +
                           idLanceArq;

            bw.write(linha);
            bw.newLine();
        }

        bw.close();
    }


    public void mostrar() {
        System.out.println("ID Item: " + idItem);
        System.out.println("Descrição: " + descricaoItem);
        System.out.println("Lance mínimo: " + lanceMinimoItem);
        System.out.println("Arrematado: " + itemArrematado);
        System.out.println("----------------------------------");
    }
}
