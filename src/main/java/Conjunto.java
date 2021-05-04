public class Conjunto {
    private int numLinhas;
    private Linha[] linha;
    
    public Conjunto(){
        this.numLinhas = 2;
        linha = new Linha[this.numLinhas];
        for(int a = 0; a < this.numLinhas; a++){
            linha[a] = new Linha();
        }
    }
    
    public Linha leLinha(int i){
        int i2;
        if(i >= 0 && i < this.numLinhas){
            this.linha[i].setSubsLinha(1);
            if(i == 0){
                i2 = 1;
            }else{
                i2 = 0;
            }
            this.linha[i2].setSubsLinha(0);
            return this.linha[i];
        }else{
            return null;
        }
    }
    
    public boolean escritaLinha(int i, Bloco valBloco, int bitEscritaLinha, boolean n){ //terminar
        if(i >= 0 && i < this.numLinhas){
            int i2;
            linha[i].setLinha(valBloco);
            if(i == 0){
                i2 = 1;
            }else{
                i2 = 0;
            }
            linha[i].setPossuiBloco(1);
            linha[i].setCont(1);
            linha[i2].setCont(0);
            
            if(n == true){
                linha[i].setEscritaLinha(1);
            }else{
                if(bitEscritaLinha == 1){
                    linha[i].setEscritaLinha(1);
                }
            }
            return true;
        }else{
            return false;
        }
    }
    
    public Linha getLinha(int i) {
        return this.linha[i];
    }
    public int getNumLinha() {
        return this.numLinhas;
    }
    

}
