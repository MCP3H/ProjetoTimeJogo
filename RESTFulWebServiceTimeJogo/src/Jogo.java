/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 31806872
 */
public class Jogo {
    private int id;
    private int timea;
    private int timeb;
    private int golsa;
    private int golsb;
    
    public Jogo(){}
    
    public Jogo(int timea, int timeb, int golsa, int golsb){
        this.timea = timea;
        this.timeb = timeb;
        this.golsa = golsa;
        this.golsb = golsb;
    }
    
    public int getId(){
        return this.id;
    }
    
    public int getTimeA(){
        return this.timea;
    }
    
    public int getTimeB(){
        return this.timeb;
    }
    
    public int getGolsA(){
        return this.golsa;
    }
    
    public int getGolsB() {
        return this.golsb; 
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setTimeA(int timea) {
        this.timea = timea;
    }
    
    public void setTimeB(int timeb) {
        this.timeb = timeb;
    }
    
    public void setGolsA(int golsa) {
        this.golsa = golsa;
    }
    
    public void setGolsB(int golsb) {
        this.golsb = golsb;
    }
}
