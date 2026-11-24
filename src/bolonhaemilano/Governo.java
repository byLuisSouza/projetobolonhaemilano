package bolonhaemilano;

/** Configura impostos e justiça e realiza a arrecadação anual. */
public class Governo {
    private static final int TAXA_MAXIMA=50;
    private static final double MULTIPLICADOR_JUSTA=0.8, MULTIPLICADOR_MODERADA=1.0, MULTIPLICADOR_SEVERA=1.3;
    public int arrecadarImpostos(Cidade c){
        if(c==null||c.isImpostosArrecadados())return -1;
        int base=(int)((long)c.getServos()*c.getTaxaImposto()/100);
        int valor=(int)(base*multiplicadorPara(c.getNivelJustica()));
        c.adicionarFlorins(valor); c.marcarImpostosArrecadados(); return valor;
    }
    public boolean definirTaxaImposto(Cidade c,int taxa){if(c==null||taxa<0||taxa>TAXA_MAXIMA)return false;c.setTaxaImposto(taxa);return true;}
    public boolean definirJustica(Cidade c,String nivel){
        if(c==null||nivel==null)return false;
        String escolhido=nivel.trim();
        if(escolhido.equalsIgnoreCase("Justa"))c.setNivelJustica("Justa");
        else if(escolhido.equalsIgnoreCase("Moderada"))c.setNivelJustica("Moderada");
        else if(escolhido.equalsIgnoreCase("Severa"))c.setNivelJustica("Severa");
        else return false; return true;
    }
    private double multiplicadorPara(String nivel){
        if("Justa".equals(nivel))return MULTIPLICADOR_JUSTA;
        if("Severa".equals(nivel))return MULTIPLICADOR_SEVERA;
        return MULTIPLICADOR_MODERADA;
    }
}
