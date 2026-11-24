package bolonhaemilano;

/** Recrutamento e construção, com validação de todos os recursos antes do débito. */
public class Construcoes {
    private static final int PRECO_SOLDADO=5, SERVOS_POR_SOLDADO=1, PRECO_CONSTRUCAO=20;
    public int getPrecoSoldado(){return PRECO_SOLDADO;} public int getPrecoConstrucao(){return PRECO_CONSTRUCAO;}
    public boolean recrutarSoldados(Cidade c,int qtd){
        if(c==null||qtd<=0||(long)qtd*PRECO_SOLDADO>Integer.MAX_VALUE||(long)qtd*SERVOS_POR_SOLDADO>Integer.MAX_VALUE)return false;
        int custo=qtd*PRECO_SOLDADO, servos=qtd*SERVOS_POR_SOLDADO;
        if(c.getFlorins()<custo||c.getServos()<servos)return false;
        c.removerFlorins(custo); c.removerServos(servos); c.adicionarSoldados(qtd); return true;
    }
    public boolean construir(Cidade c,int qtd){
        if(c==null||qtd<=0||(long)qtd*PRECO_CONSTRUCAO>Integer.MAX_VALUE)return false;
        int custo=qtd*PRECO_CONSTRUCAO; if(!c.removerFlorins(custo))return false;
        c.adicionarConstrucoes(qtd); return true;
    }
}
