package bruck.gereamento_automotivo;

public class Veiculo {

    private String apelido;

    private String modelo;

    private int kmAtual;

    private String marca;

    private String combustivel;

    private boolean emUso;

    public Veiculo(String apelido, String modelo, int kmAtual, String marca, String combustivel, boolean emUso) {
        this.apelido     = apelido;
        this.modelo      = modelo;
        this.kmAtual     = kmAtual;
        this.marca       = marca;
        this.combustivel = combustivel;
        this.emUso       = emUso;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(int kmAtual) {
        this.kmAtual = kmAtual;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public boolean isEmUso() {
        return emUso;
    }

    public void setEmUso(boolean emUso) {
        this.emUso = emUso;
    }

    @Override
    public String toString() {

        return apelido     + "\n" +
               modelo      + "\n" +
               kmAtual     + "\n" +
               marca       + "\n" +
               combustivel + "\n" +
               emUso;
    }
}
