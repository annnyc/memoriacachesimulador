import java.util.Random;

/*Memoria Principal*/
public class Principal {
    private Bloco[] memPrincipal;
    private Celula[] celula;
    private Bloco[] bloco;
    private int numCelulas;
    private int numCelulasBloco;
    private int numBlocos;
    private int tamCelula;
    
    public Principal(){
        this.numCelulas = 128;
        this.numCelulasBloco = 4;
        this.numBlocos = (this.numCelulas/ this.numCelulasBloco);
        this.tamCelula = 8;
        
        bloco = new Bloco[numBlocos];
        
        memPrincipal = new Bloco[this.numBlocos];
        
        Random gerador = new Random();
        
        for(int i = 0; i < this.numBlocos; i++){
            bloco[i] = new Bloco();
            
            for(int a = 0; a < numCelulasBloco; a++){
                int n = gerador.nextInt(255) + 1;
                bloco[i].escreverCelula(a, n);
            }
            
            memPrincipal[i] = bloco[i];
        }
    }
    
    public Bloco lerBloco(int i){
        if(i >= 0 && i < this.numBlocos){
            return memPrincipal[i];
        }else{
            return null;
        }
    }
    
    public Bloco escreverBloco(int bloco, Bloco b){
        if(bloco >= 0 && bloco < this.numBlocos){
            return this.memPrincipal[bloco];
        }
        return null;
    }
    
    public void mostrarMemoria(){
        
        System.out.println("Bloco  -   Celula  -  Conteudo   ");
        
        for(int b = 0; b < this.numBlocos; b++){
            for(int c = 0; c < this.numCelulasBloco; c++){
                System.out.println(String.format("%5s", Integer.toBinaryString(b)).replaceAll(" ","0") + "         " + String.format("%2s", Integer.toBinaryString(c)).replaceAll(" ","0") + "     " + String.format("%8s", Integer.toBinaryString(memPrincipal[b].lerCelula(c).getCelula())).replaceAll(" ","0"));
            }
            if(b < this.numBlocos - 1){
                System.out.println("###############################");
            }
        }
    }
}
