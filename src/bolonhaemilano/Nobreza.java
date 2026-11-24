package bolonhaemilano;

/** Calcula prestígio e promove o governante no máximo um nível por ano. */
public class Nobreza {
    private static final int PESO_CONSTRUCAO=10, PESO_SOLDADO=2, PESO_SERVO=1, HECTARES_POR_PONTO=100;
    private static final int[] PRESTIGIO_MINIMO={0,400,550,700,850,1000};
    public int calcularPrestigio(Cidade c){return c.getConstrucoes()*PESO_CONSTRUCAO+c.getSoldados()*PESO_SOLDADO+c.getServos()*PESO_SERVO+c.getTerras()/HECTARES_POR_PONTO;}
    public int prestigioParaProximoTitulo(Jogador j){if(j==null||j.ehMonarca())return -1;return PRESTIGIO_MINIMO[j.getNivelTitulo()+1];}
    public boolean verificarPromocao(Cidade c){
        if(c==null)return false; Jogador j=c.getDono(); int alvo=prestigioParaProximoTitulo(j);
        return alvo!=-1&&calcularPrestigio(c)>=alvo&&j.promover();
    }
}
