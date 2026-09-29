package src.models;
public enum Cor {

    VERMELHO("Vermelho", 1), AZUL("Azul", 2), 
    VERDE("Verde", 3), BRANCO("Branco", 4), 
    PRETO( "Preto", 5), AMARELO("Amarelo", 6);

    private String tipoCor;
    private int numCor;

    Cor(String tipoCor, int numCor){
        this.tipoCor = tipoCor;
        this.numCor = numCor;
    }

    public int getNumCor(){
        return this.numCor;
    }

    public String getTipoCor(){
        return this.tipoCor;
    }
    
}
