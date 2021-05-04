/*Memoria cache*/
public class Cache {
    private Conjunto[] conj;
    private int hitLeitura;
    private int missLeitura;
    private int hitEscrita;
    private int missEscrita;
    
    private int numeroLinhas;
    private int linhasPorConjunto;
    private int numConjuntos;
    
    public Cache(){
        this.hitLeitura = 0;
        this.missLeitura = 0;
        this.hitEscrita = 0;
        this.missEscrita = 0;
        this.numeroLinhas = 8;
        this.linhasPorConjunto = 2;
        this.numConjuntos = (this.numeroLinhas / this.linhasPorConjunto);
        
        conj = new Conjunto[this.numConjuntos];
        
        for(int v = 0; v < this.numConjuntos; v++){
            conj[v] = new Conjunto();
        }
    }

    public int getHitEscrita() {
        return hitEscrita;
    }
    
    public void setHitEscrita(int i) {
        this.hitEscrita += i;
    }
    

    public int getMissEscrita() {
        return missEscrita;
    }
    
    public Conjunto leConjunto(int i){
        if(i >= 0 && i < this.numConjuntos){
            return this.conj[i];
        }else{
            return null;
        }
    }
    
    public void listarCache(){
        String tagescrita; 
        String conteudo = null;
        System.out.println("Conjunto - Linha -   Tag   -                  Conteúdo             -    Bit Subs   -   Bit Escr    -   Bit Validade");
        for(int x = 0; x < this.numConjuntos; x++){
            String conjunto = String.format("%2s", Integer.toBinaryString(x)).replaceAll(" ","0");
            Conjunto c3 = this.conj[x];
            for(int y = 0; y < this.linhasPorConjunto; y++){
                String linha = String.format("%2s", Integer.toBinaryString(y)).replaceAll(" ","0");
                Linha l3 = c3.getLinha(y);
                int tag = l3.getTag();
                if(tag != 0){
                    tagescrita = String.format("%3s", Integer.toBinaryString(tag)).replaceAll(" ","0");
                }else{
                    tagescrita = "NULL";
                }
                if(l3.getPossuiBloco() == 0){
                    conteudo = " ";
                    //System.out.println("GetPossuiBloco - " + conj[x].getLinha(y).getPossuiBloco());
                }else{
                    Bloco blocoCache = conj[x].getLinha(y).getLinha();
                    for(int i = 0; i < blocoCache.getNumCelulas(); i++){
                        if(blocoCache.getBloco(i).getCelula() != 0){
                            conteudo += String.format("%8s", Integer.toBinaryString(blocoCache.getBloco(i).getCelula())).replaceAll(" ","0");
                            conteudo += " ";
                        }
                    }

                }
                String bitSubs = Integer.toBinaryString(l3.getCont()).replaceAll(" ","0");
                String bitEscr = Integer.toBinaryString(l3.getEscritaLinha()).replaceAll(" ","0");
                String bitVal = Integer.toBinaryString(l3.getPossuiBloco()).replaceAll(" ","0");
                if(conteudo == " "){
                    System.out.println("    " + conjunto + "      " + linha + "        " + tagescrita + "       " + "                    " + "                     " + bitSubs + "                 " + bitEscr  + "                 " + bitVal);
                }else{
                    System.out.println("    " + conjunto + "      " + linha + "        " + tagescrita + "       " + conteudo + "     " + bitSubs + "                 " + bitEscr  + "                 " + bitVal);
                }
            }
        }
        
    }
    
    public int getHitLeitura() {
        return this.hitLeitura;
    }

    public void setHitLeitura(int value) {
        this.hitLeitura = value;
    }
    
    public int getMissLeitura() {
        return this.missLeitura;
    }
    
    public void incrementaHitLeitura(int value){
        this.hitLeitura += value;
    }
    
    public void incrementaMissLeitura(int value){
        this.missLeitura += value;
    }
    
    public int getNumConjuntos() {
        return this.numConjuntos;
    }
    
    public void incrementaMissEscrita(int i){
        this.missEscrita += i;
    }
}
