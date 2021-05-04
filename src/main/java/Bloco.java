public class Bloco implements Cloneable{
    public int numCelulas;
    public Celula[] bloco;
    
    public Bloco(){
        this.numCelulas = 4;
        bloco = new Celula[this.numCelulas];
        for(int i = 0; i < this.numCelulas; i++){
            bloco[i] = new Celula();
        }
    }
    
    public Celula lerCelula(int x){
        if(x >= 0 && x < this.numCelulas){
            return this.bloco[x];
        }else{
            return null;
        }
    }
    
    public boolean escreverCelula(int x, int y){
        if(x >= 0 && x < this.numCelulas){
            this.bloco[x].setCelula(y);
            return true;
        }else{
            return false;
        }
    }
    
    public Celula getBloco(int a){
        return this.bloco[a];
    }

    public int getNumCelulas(){
        return this.numCelulas;
    }
    public void setNumCelulas(int i){
        this.numCelulas  = i;
    }
    
    
    public Bloco clone() {
        Bloco clone = new Bloco();
        clone.numCelulas = this.numCelulas;
        System.arraycopy(this.bloco, 0, clone.bloco, 0, this.numCelulas);
        return clone;
    }
    
}
