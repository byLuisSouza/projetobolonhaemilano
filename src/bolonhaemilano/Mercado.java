package bolonhaemilano;

/** Operações comerciais com preços fixos definidos no enunciado. */
public class Mercado {
    private static final int PRECO_GRAO=2, PRECO_TERRA=1;
    public int getPrecoGrao(){return PRECO_GRAO;} public int getPrecoTerra(){return PRECO_TERRA;}
    public boolean comprarGraos(Cidade c,int qtd){return comprar(c,qtd,PRECO_GRAO,true);}
    public boolean venderGraos(Cidade c,int qtd){return vender(c,qtd,PRECO_GRAO,true);}
    public boolean comprarTerras(Cidade c,int qtd){return comprar(c,qtd,PRECO_TERRA,false);}
    public boolean venderTerras(Cidade c,int qtd){return vender(c,qtd,PRECO_TERRA,false);}
    private boolean comprar(Cidade c,int q,int preco,boolean graos){
        if(c==null||q<=0||(long)q*preco>Integer.MAX_VALUE)return false;
        int custo=q*preco; if(!c.removerFlorins(custo))return false;
        if(graos)c.adicionarGraos(q);else c.adicionarTerras(q); return true;
    }
    private boolean vender(Cidade c,int q,int preco,boolean graos){
        if(c==null||q<=0||(long)q*preco>Integer.MAX_VALUE)return false;
        boolean retirou=graos?c.removerGraos(q):c.removerTerras(q); if(!retirou)return false;
        c.adicionarFlorins(q*preco); return true;
    }
}
