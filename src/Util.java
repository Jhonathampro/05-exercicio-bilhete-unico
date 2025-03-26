import java.text.DecimalFormat;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Double.parseDouble;
// Esse import são para não precisar escrever, elses são metodos estaticos
// se eu não import eu teria que repetir o nome
public class Util {
    private Bilhete[] bilhete = new Bilhete[3];
    private int index = 0;
    public void menuPrincipal(){
        int opcao;
        String menu = "1. Administrador\n2. usuário\n3. Finalizar\n";

        do {
            opcao = parseInt(showInputDialog(menu));
            if(opcao < 1 || opcao > 3){
                showMessageDialog(null, "Opção inválida");
            }
            else {
                switch (opcao){
                    case 1:
                        menuAdministrador();
                        break;
                    case 2:
                        menuUsauario();
                        break;
                }

            }
        }while(opcao != 3);
    }

    // metodo com as funcinalidades do adim
    private void menuAdministrador(){
        int opcao;
        String menu = "1. Emitir bilhete\n2. listar bilhte\n3." +
                " Remover bilhte\n4 Sair.";
        do {
            opcao = parseInt(showInputDialog(menu));
            if (opcao < 1 || opcao > 4){
                showMessageDialog(null, "Opção inválida");
            }
            else {
                switch (opcao){
                    case 1:
                        emitirBilhete();
                        break;
                    case 2:
                        listarBilhete();
                        break;
                }
            }
        }while(opcao != 4);


    }

    // metodo com as funcionalidades do bilhete
    private void menuUsauario(){
       int opcao;
       String menu = "1. Consultar saldo\n2. Carregar bilhete\n3. Passar na catraca\n4. sair";
       do {
           opcao = parseInt(showInputDialog(menu));
           if(opcao < 1 || opcao > 4) {
               showMessageDialog(null, "Opção inválida");
           }
           else {
               switch (opcao) {
                   case 1:
                       consutartarSaldo();
                       break;
                   case 2:
                       carregarBilhete();
                       break;
                   case 3:
                       passarNaCatraca();
                       break;
               }
           }
       } while(opcao != 4);
    }

    // gerar um objeto e armazenar no vertor
    private void emitirBilhete(){
      String nome, perfil;
      long cpf;

      if (index < bilhete.length){
          nome = showInputDialog("Nome");
          cpf = parseLong(showInputDialog("cpf"));
          perfil = showInputDialog("Tipo de trifa (perfil) --> professor ou estudante ou comum");
          bilhete[index] = new Bilhete(new Usuario(nome, cpf, perfil));
          index++;
      } else {
          showMessageDialog(null, "Procure a adimistriação");
      }
    }

    private void listarBilhete(){
        DecimalFormat df = new DecimalFormat("0.00");
        String aux = "";
        for (int i = 0; i < index; i++){
            aux += "Nome do usáurio: " + bilhete[i].usuario.nome + "\n";
            aux += "CPF do usáurio: " + bilhete[i].usuario.cpf + "\n";
            aux += "Número do bilhete: " + bilhete[i].numero + "\n";
            aux += "Saldo: " +df.format( bilhete[i].saldo) + "\n";
            aux += "perfil (tipo bilhete): " + bilhete[i].usuario.perfil + "\n";
            aux += "---------------------------------------------------------\n";
        }
        showMessageDialog(null, aux);
    }

    // metodo para carregar o bilhete com valor informado pelo usúario
    private void carregarBilhete(){
        double valor;
        int posicao = pesquisar();
        if (posicao != -1){
            valor = parseDouble(showInputDialog("Valor para carregar o bilhete"));
            bilhete[posicao].carregar(valor);
        }
    }

    // método para consultar saldo
    private void consutartarSaldo(){
        int posicao = pesquisar();
        if (posicao != -1){
          showMessageDialog(null, "Saldo = R$ " + bilhete[posicao].consultarSaldo());
        }
    }

    // método para passar na catraca
    private void passarNaCatraca(){
        int posicao = pesquisar();
        if (posicao != -1){
          showMessageDialog(null, bilhete[posicao].catraca());
        }
    }

    // metodo auxiliar para os outros metodos da aplicação
    private int pesquisar(){
        long cpf;
        cpf = parseLong(showInputDialog("cpf"));
        for (int i = 0; i < index; i++) {
            if (cpf == bilhete[i].usuario.cpf) {
                return i;
            }
        }
        showMessageDialog(null, cpf + " Não encontrado");
        return -1;

    }
}

// metodo para remover, a ordem não importa então pego o último indece jogo para o primeiro
