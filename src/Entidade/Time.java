
package Entidade;

public class Time {
    private int id;
    private String nome;
    private int ano;
    private String cidade;
    private String estado;
    
    public Time(){}
    
    public Time(String nome, int ano, String cidade, String estado){
        this.nome = nome;
        this.ano = ano;
        this.cidade = cidade;
        this.estado = estado;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public int getAno(){
        return this.ano;
    }
    
    public String getCidade(){
        return this.cidade;
    }
    
    public String getEstado(){
        return this.estado;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
