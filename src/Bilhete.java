import java.util.Random;

public class Bilhete {
    double saldo;
    static final double tarifaBase = 5.20;
    long numero;
    Usuario usuario;

    public Bilhete(Usuario usuario){
        this.numero = gerarNumero();
        this.usuario = usuario;
    }

    private long gerarNumero(){
        Random rd = new Random();
        return  rd.nextLong(1000, 10000);
    }

    // metodo carregar o bilhete
    public void carregar(double valor){
        saldo += valor;
    }

    // metedo para consultar o saldo
    public double consultarSaldo(){
          return saldo;
    }

    // metodo para passar na catraca
    public String catraca(){
        double debito = tarifaBase / 2;
        if (usuario.perfil.equalsIgnoreCase("comum")){
            debito = tarifaBase;
        }
        if (saldo >= debito ){
            saldo -= debito;
            return "Catraca liberada";
        }
        return "Saldo  insuficiente";
    }
}
