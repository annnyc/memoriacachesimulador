import java.io.*;
/*CPU*/

public class Processador {
    
    Cache mc = new Cache();
    Principal mp = new Principal();
    
    public boolean leitura(String endereco){
        String end = endereco;
        if(end.length() != 7){
            System.out.println("Endereco invalido!\n");
            return false;
        }else{
            boolean estaNaCache = false;
            String bloco = end.substring(0,5);
            String tag = end.substring(0,3);
            String conj = end.substring(3,5);
            String cel = end.substring(5,7);
            int numbloco = Integer.parseInt(bloco, 2);
            int numtag = Integer.parseInt(tag, 2);
            int numconj = Integer.parseInt(conj, 2);
            int numcel = Integer.parseInt(cel, 2);
            
            for(int x = 0; x < 2; x++){
                int taglinha = mc.leConjunto(numconj).getLinha(x).getTag();
                if(taglinha == numtag){
                    estaNaCache = true;
                    this.mc.incrementaHitLeitura(1);
                    System.out.println("Ocorreu Hit!");
                    System.out.println("Valor do bloco - " + mc.leConjunto(numconj).leLinha(x).getTag());
                    System.out.println("Conjunto - " + String.format("%2s", Integer.toBinaryString(numconj)).replaceAll(" ","0"));
                    System.out.println("Bloco - " + String.format("%5s", Integer.toBinaryString(numbloco)).replaceAll(" ","0"));
                    System.out.println("Linha - " + String.format("%2s", Integer.toBinaryString(x)).replaceAll(" ","0"));
                    break;  
                }
            }
            
            if(!estaNaCache){
                Bloco valBloco = mp.lerBloco(numbloco);
                int valorPrint = valBloco.lerCelula(numcel).getCelula();
                String valPrintStr = String.format("%7s", Integer.toBinaryString(valorPrint)).replaceAll(" ","0");
                System.out.println();
                System.out.println("Ocorreu Miss!");
                System.out.println("Valor - " + valPrintStr);
                System.out.println("Conjunto - " + Integer.toBinaryString(numconj));
                System.out.println("Bloco - " + Integer.toBinaryString(numbloco));
                
                boolean linhaVazia = false;
                //percorre para procurar linha vazia no conjunto da cache
                for(int x = 0; x < mc.leConjunto(numconj).getNumLinha(); x++){
                    if(mc.leConjunto(numconj).leLinha(x).getTag() == 0){
                        mc.leConjunto(numconj).leLinha(x).setTag(numtag);
                        System.out.println("Linha - " + x);
                        mc.leConjunto(numconj).escritaLinha(x, valBloco, 0, false);
                        linhaVazia = true;
                        break;
                    }
                }
                
                if(!linhaVazia){
                    for(int x = 0; x < mc.leConjunto(numconj).getNumLinha(); x++){
                        if(mc.leConjunto(numconj).leLinha(x).getCont() == 0){
                            System.out.println("Linha - " + Integer.toBinaryString(x));
                            
                            if(mc.leConjunto(numconj).leLinha(x).getEscritaLinha() == 1){
                                String bloco1 = tag + conj;
                                int blocoint = Integer.parseInt(bloco1);
                                mp.escreverBloco(blocoint, mc.leConjunto(numconj).leLinha(x).getLinha());
                            }
                            
                            mc.leConjunto(numconj).escritaLinha(x, valBloco, 0, true);
                            mc.leConjunto(numconj).getLinha(x).setTag(numtag);
                            break;
                        }
                    }
                }
                
                mc.incrementaMissLeitura(1);
                System.out.println();
            }
            
            return true;
        }   
    }

    public void escrita(String end, String dado){
        int dad1 = Integer.parseInt(dado, 2);
        if(dado.length() != 7 || (dad1 < 0 || dad1 > 255)){
            System.out.println("Endereço inválido!");
        }else{
            System.out.println();
            String bloco = end.substring(0,5);
            String tag = end.substring(0,3);
            String conj = end.substring(3,5);
            String cel = end.substring(5,7);
            int numbloco = Integer.parseInt(bloco, 2);
            int numtag = Integer.parseInt(tag, 2);
            int numconj = Integer.parseInt(conj, 2);
            int numcel = Integer.parseInt(cel, 2);
            
            Bloco blo = this.mp.lerBloco(numbloco);
            Conjunto conjunto = this.mc.leConjunto(numconj);
            Bloco newBloco = blo.clone();
            newBloco.escreverCelula(numcel, dad1);
            boolean linhaTemBloco = false;
            for(int a =0; a < this.mc.leConjunto(numconj).getNumLinha(); a++){
                if((conjunto.getLinha(a).getTag()) == numtag){
                    conjunto.getLinha(a).getLinha().escreverCelula(numcel, dad1);
                    mc.leConjunto(numconj).escritaLinha(a, conjunto.getLinha(a).getLinha(), 1, linhaTemBloco);
                    this.mc.setHitEscrita(1);
                    System.out.println("");
                    System.out.println("Ocorreu hit!");
                    System.out.println("Bloco - " + String.format("%5s", Integer.toBinaryString(numbloco)).replaceAll(" ","0"));
                    System.out.println("Conjunto - " + String.format("%2s", Integer.toBinaryString(numconj)).replaceAll(" ","0"));
                    System.out.println("Linha - " + a);
                    linhaTemBloco = true;
                    break;
                }
            } 
            boolean linhaVazia = false;
            if(!linhaTemBloco){
                for(int a =0; a < this.mc.leConjunto(numconj).getNumLinha(); a++){
                    if((conjunto.getLinha(a).getPossuiBloco()) == 0){
                       conjunto.getLinha(a).setTag(numtag); 
                       mc.leConjunto(numconj).escritaLinha(a, newBloco, 1, linhaVazia);
                       this.mc.incrementaMissEscrita(1);
                        System.out.println("");
                        System.out.println("Ocorreu Miss!");
                        System.out.println("Bloco - " + String.format("%5s", Integer.toBinaryString(numbloco)).replaceAll(" ","0"));
                        System.out.println("Conjunto - " + String.format("%2s", Integer.toBinaryString(numconj)).replaceAll(" ","0"));
                        System.out.println("Linha - "+ a);
                        linhaVazia = true;
                    }
                }
            }
            
            if(!linhaVazia){
                System.out.println("");
                System.out.println("Ocorreu Miss!");
                System.out.println("Conjunto - " + String.format("%2s", Integer.toBinaryString(numconj)).replaceAll(" ","0"));
                System.out.println("Bloco - " + String.format("%5s", Integer.toBinaryString(numbloco)).replaceAll(" ","0"));
                for(int a =0; a < this.mc.leConjunto(numconj).getNumLinha(); a++){
                    if((conjunto.getLinha(a).getCont()) == 0){
                        System.out.println("Linha - " + a);
                        System.out.println("");
                        
                        //escrita no retorno
                        if((conjunto.getLinha(a).getEscritaLinha()) == 0){
                            String block = String.format("%3s", Integer.toBinaryString(conjunto.getLinha(a).getTag())).replaceAll(" ","0");
                            String block1 = String.format("%2s", Integer.toBinaryString(numconj)).replaceAll(" ","0");
                            String block2 = block + block1;
                            int bloco2 = Integer.parseInt(block2 , 2);
                            this.mp.escreverBloco(bloco2, conjunto.getLinha(a).getLinha().clone());
                        
                            //tentativa de substituir
                            Bloco novo = conjunto.getLinha(a).getLinha().clone();
                            novo.escreverCelula(bloco2, dad1);
                            conjunto.getLinha(a).setTag(numtag);
                            this.mc.leConjunto(numconj).escritaLinha(a, novo, 1, true);
                            break;
                        }
                    }
                }
            }
        }
        this.mc.incrementaMissEscrita(1);
        System.out.println();
    }
}
