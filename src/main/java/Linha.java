public class Linha {
    private int tag;
    private Bloco linha;
    private int possuiBloco; //bitVal
    private int cont; //bitSubs
    private int escritaLinha; //bitEscr
    
    public Linha(){
        this.tag = 0; 
        this.possuiBloco = 0;
        this.cont = 0;
        this.escritaLinha = 0;
    }
    
    public Bloco getLinha() {
        return this.linha;
    }
    
    
    public int getEscritaLinha() {
        return this.escritaLinha;
    }
    
    public void setEscritaLinha(int value) {
        this.cont = value;
    }
    
    public void setSubsLinha(int value) {
        this.cont = value;
    }
    
    public int getCont() {
        return this.cont;
    }

    public void setCont(int value) {
        this.cont = value;
    }
    
    public int getTag() {
        return this.tag;
    }

    public void setTag(int value) {
        this.tag = value;
    }
    public int getPossuiBloco() {
        return this.possuiBloco;
    }

    public void setPossuiBloco(int value) {
        this.possuiBloco = value;
    }
    
    public void setLinha(Bloco linha) {
        this.linha = linha;
    }

}
