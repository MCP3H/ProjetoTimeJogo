
package Jogo;

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
    
    public int getTimea(){
        return this.timea;
    }
    
    public int getTimeb(){
        return this.timeb;
    }
    
    public int getGolsa(){
        return this.golsa;
    }
    
    public int getGolsb() {
        return this.golsb; 
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setTimea(int timea) {
        this.timea = timea;
    }
    
    public void setTimeb(int timeb) {
        this.timeb = timeb;
    }
    
    public void setGolsa(int golsa) {
        this.golsa = golsa;
    }
    
    public void setGolsb(int golsb) {
        this.golsb = golsb;
    }
}
