package bruck.gereamento_automotivo;

public class Manutencao {

    private int tipoServico;

    private String data;

    private int km;

    private double custo;

    private int kmProximaTroca;

    public Manutencao(int tipoServico, String data, int km, double custo, int kmProximaTroca) {
        this.tipoServico    = tipoServico;
        this.data           = data;
        this.km             = km;
        this.custo          = custo;
        this.kmProximaTroca = kmProximaTroca;
    }

    public int getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(int tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public int getKmProximaTroca() {
        return kmProximaTroca;
    }

    public void setKmProximaTroca(int kmProximaTroca) {
        this.kmProximaTroca = kmProximaTroca;
    }

    @Override
    public String toString() {

        return tipoServico    + "\n" +
               data           + "\n" +
               km             + "\n" +
               custo          + "\n" +
               kmProximaTroca;
    }
}
