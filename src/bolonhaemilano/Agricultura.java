package bolonhaemilano;

/** Colheita e distribuição anual de alimentos. */
public class Agricultura {
    private static final int HECTARES_POR_SERVO=5, RENDIMENTO_POR_HECTARE=2, SERVOS_POR_GRAO=5;
    private static final int CRESCIMENTO_PERCENTUAL=5, PERCENTUAL_MINIMO=20, PERCENTUAL_MAXIMO=80;
    public int getPercentualMinimo(){return PERCENTUAL_MINIMO;} public int getPercentualMaximo(){return PERCENTUAL_MAXIMO;}
    public int processarColheita(Cidade c){
        if(c==null||c.isColheitaFeita())return -1;
        int area=(int)Math.min(c.getTerras(),(long)c.getServos()*HECTARES_POR_SERVO);
        int sementes=(area+1)/2;
        if(sementes>c.getGraos()){sementes=c.getGraos();area=sementes*2;}
        if(sementes>0)c.removerGraos(sementes);
        long produzido=(long)area*RENDIMENTO_POR_HECTARE;
        if(produzido>Integer.MAX_VALUE)return -1;
        c.adicionarGraos((int)produzido); c.marcarColheitaFeita(); return area;
    }
    public boolean distribuirAlimentos(Cidade c,int percentual){
        if(c==null||percentual<PERCENTUAL_MINIMO||percentual>PERCENTUAL_MAXIMO)return false;
        int distribuido=(int)((long)c.getGraos()*percentual/100);
        if(distribuido>0)c.removerGraos(distribuido);
        int necessidade=(c.getServos()+SERVOS_POR_GRAO-1)/SERVOS_POR_GRAO;
        if(distribuido>=necessidade)c.adicionarServos(c.getServos()*CRESCIMENTO_PERCENTUAL/100);
        else c.removerServos(Math.min(c.getServos(),necessidade-distribuido));
        return true;
    }
}
