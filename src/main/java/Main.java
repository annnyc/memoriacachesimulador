import java.util.Scanner; 
public class Main {
    public static void main(String[] args) {
        boolean continua = true;
        int opcao;
        String end;
        
        Processador p = new Processador();

        while(continua){
            System.out.println("Menu:");
            System.out.println("1 - Ler endereco");
            System.out.println("2 - Escrever em um endereco");
            System.out.println("3 - Exibir memoria principal");
            System.out.println("4 - Exibir a memoria cache");
            System.out.println("5 - Exibir estatisticas");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opcao: ");

            Scanner s = new Scanner(System.in);
            opcao = s.nextInt();

            switch (opcao) {
                case 1: {
                    System.out.print("Escolha um endereco da Memoria Principal para ler: ");
                    end = s.next();
                    p.leitura(end);
                    System.out.println();
                    break;
                }
                case 2: {
                    System.out.print("Escolha um endereco da Memoria Principal para escrever: ");
                    String ender = s.next();
                    System.out.print("Digite o dado para ser armazenado no endereço: ");
                    String dado = s.next();
                    p.escrita(ender, dado);
                    break;
                }
                case 3: {
                    System.out.println("Mostrando memoria principal: ");
                    p.mp.mostrarMemoria();
                    System.out.println();
                    break;
                }
                case 4: {
                    System.out.println("Mostrando memoria cache: ");
                    p.mc.listarCache();
                    System.out.println();
                    break;
                }
                case 5: {
                    System.out.println("Mostrando estatisticas: ");
                    System.out.println();
                    System.out.println("Total de acessos: " +( p.mc.getHitLeitura() + p.mc.getMissLeitura() + p.mc.getHitEscrita() + p.mc.getMissEscrita()));
                    System.out.println("Leituras: " + (p.mc.getHitLeitura() + p.mc.getMissLeitura()));
                    System.out.println("Escritas: " + (p.mc.getHitEscrita() + p.mc.getMissEscrita()));
                    System.out.print("Leituras-acertos: " + p.mc.getHitLeitura() + " - ");
                    if(p.mc.getHitLeitura() + p.mc.getMissLeitura() > 0){
                        float porcent = (float)p.mc.getHitLeitura() / ((float)p.mc.getHitLeitura() + (float)p.mc.getMissLeitura())*100;
                        System.out.println(porcent + "%");
                    }else{
                       System.out.println("0%"); 
                    }
                    System.out.print("Leituras-erros: " + p.mc.getMissLeitura() + " - ");
                    if(p.mc.getHitLeitura() + p.mc.getMissLeitura() > 0){
                        float porcent1 = (float)p.mc.getMissLeitura() / ((float)p.mc.getHitLeitura() + (float)p.mc.getMissLeitura())* 100;
                        System.out.println(porcent1 + "%");
                    }else{
                       System.out.println("0%"); 
                    }
                    System.out.print("Escrita-acertos: " + p.mc.getHitEscrita() + " - ");
                    if(p.mc.getHitEscrita() + p.mc.getMissEscrita() > 0){
                        float porcent2 = (float)p.mc.getHitEscrita() / ((float)p.mc.getHitEscrita() + (float)p.mc.getMissEscrita()) * 100;
                        System.out.println(porcent2 + "%");
                    }else{
                        System.out.println("0%"); 
                    }
                    System.out.print("Escrita-erros: " + p.mc.getMissEscrita() + " - ");
                    if(p.mc.getHitEscrita() + p.mc.getMissEscrita() > 0){
                        float porcent3 = (float)p.mc.getMissEscrita() / ((float)p.mc.getHitEscrita() + (float)p.mc.getMissEscrita()) * 100;
                        System.out.println(porcent3 + "%");
                    }else{
                        System.out.println("0%"); 
                    }
                    System.out.println();
                    break;
                }
                case 6: {
                    continua = false;
                    break;
                }
                default: {
                    System.out.println("Opcao invalida! Por favor tente novamente.");
                }
            }
                
        }

        System.out.println("Programa encerrado!");

    }
}
