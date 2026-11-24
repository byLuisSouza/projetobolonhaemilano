package bolonhaemilano;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Controla a ordem dos turnos e o encerramento dos anos. */
public class Partida {
    public static final int MAXIMO_JOGADORES=4;
    private static final int ANO_FINAL=1410;
    private final List<Cidade> cidades=new ArrayList<>();
    private int indiceAtual;
    public boolean adicionarCidade(Cidade c){if(c==null||cidades.size()>=MAXIMO_JOGADORES)return false;for(Cidade existente:cidades)if(existente.getDono()==c.getDono())return false;cidades.add(c);return true;}
    public List<Cidade> getCidades(){return Collections.unmodifiableList(new ArrayList<>(cidades));}
    public Cidade getCidadeAtual(){return cidades.isEmpty()?null:cidades.get(indiceAtual);}
    public int getAnoAtual(){return cidades.isEmpty()?1400:cidades.get(0).getAnoAtual();}
    public int getAnoFinal(){return ANO_FINAL;}
    public boolean passarTurno(){if(cidades.isEmpty())return false;indiceAtual++;if(indiceAtual==cidades.size()){indiceAtual=0;return true;}return false;}
    public List<Cidade> encerrarAno(Nobreza nobreza){List<Cidade> promovidas=new ArrayList<>();for(Cidade c:cidades){c.avancarAno();if(nobreza.verificarPromocao(c))promovidas.add(c);}return promovidas;}
    public boolean terminou(){if(cidades.isEmpty())return false;for(Cidade c:cidades)if(c.getDono().ehMonarca())return true;return getAnoAtual()>ANO_FINAL;}
    public Cidade determinarVencedor(Nobreza nobreza){if(cidades.isEmpty())return null;Cidade melhor=cidades.get(0);for(Cidade c:cidades){int nivel=c.getDono().getNivelTitulo(), nivelMelhor=melhor.getDono().getNivelTitulo();if(nivel>nivelMelhor||(nivel==nivelMelhor&&nobreza.calcularPrestigio(c)>nobreza.calcularPrestigio(melhor)))melhor=c;}return melhor;}
}
