package bolonhaemilano;

/** Guarda os recursos e o estado anual de uma cidade-estado. */
public class Cidade {
    private static final int ANO_INICIAL=1400, FLORINS_INICIAIS=1000, TERRAS_INICIAIS=10000;
    private static final int GRAOS_INICIAIS=500, SERVOS_INICIAIS=200, IMPOSTO_INICIAL=10;
    private final String nome;
    private final Jogador dono;
    private int anoAtual, florins, terras, graos, servos, taxaImposto, soldados, construcoes;
    private String nivelJustica;
    private boolean colheitaFeita, impostosArrecadados;

    public Cidade(String nome, Jogador dono) {
        if (nome == null || nome.trim().isEmpty() || dono == null) throw new IllegalArgumentException("Cidade e governante são obrigatórios.");
        this.nome=nome.trim(); this.dono=dono; anoAtual=ANO_INICIAL; florins=FLORINS_INICIAIS;
        terras=TERRAS_INICIAIS; graos=GRAOS_INICIAIS; servos=SERVOS_INICIAIS;
        taxaImposto=IMPOSTO_INICIAL; nivelJustica="Moderada"; soldados=0; construcoes=0;
    }
    public String getNome(){return nome;} public int getAnoAtual(){return anoAtual;} public Jogador getDono(){return dono;}
    public int getFlorins(){return florins;} public int getTerras(){return terras;} public int getGraos(){return graos;}
    public int getServos(){return servos;} public int getSoldados(){return soldados;} public int getConstrucoes(){return construcoes;}
    public int getTaxaImposto(){return taxaImposto;} public String getNivelJustica(){return nivelJustica;}
    public boolean isColheitaFeita(){return colheitaFeita;} public boolean isImpostosArrecadados(){return impostosArrecadados;}
    public void setTaxaImposto(int taxa){if(taxa>=0 && taxa<=50) taxaImposto=taxa;}
    public void setNivelJustica(String nivel){if(nivel!=null && (nivel.equals("Justa")||nivel.equals("Moderada")||nivel.equals("Severa"))) nivelJustica=nivel;}
    public void marcarColheitaFeita(){colheitaFeita=true;} public void marcarImpostosArrecadados(){impostosArrecadados=true;}
    public void avancarAno(){anoAtual++; colheitaFeita=false; impostosArrecadados=false;}
    private boolean adicionar(int qtd){return qtd>0;}
    public void adicionarFlorins(int q){if(adicionar(q) && florins<=Integer.MAX_VALUE-q) florins+=q;}
    public boolean removerFlorins(int q){if(q<=0||q>florins)return false; florins-=q;return true;}
    public void adicionarTerras(int q){if(adicionar(q)&&terras<=Integer.MAX_VALUE-q)terras+=q;}
    public boolean removerTerras(int q){if(q<=0||q>terras)return false;terras-=q;return true;}
    public void adicionarGraos(int q){if(adicionar(q)&&graos<=Integer.MAX_VALUE-q)graos+=q;}
    public boolean removerGraos(int q){if(q<=0||q>graos)return false;graos-=q;return true;}
    public void adicionarServos(int q){if(adicionar(q)&&servos<=Integer.MAX_VALUE-q)servos+=q;}
    public boolean removerServos(int q){if(q<=0||q>servos)return false;servos-=q;return true;}
    public void adicionarSoldados(int q){if(adicionar(q)&&soldados<=Integer.MAX_VALUE-q)soldados+=q;}
    public boolean removerSoldados(int q){if(q<=0||q>soldados)return false;soldados-=q;return true;}
    public void adicionarConstrucoes(int q){if(adicionar(q)&&construcoes<=Integer.MAX_VALUE-q)construcoes+=q;}
    public boolean removerConstrucoes(int q){if(q<=0||q>construcoes)return false;construcoes-=q;return true;}
    public void exibirStatus(){
        System.out.println("\n--- CIDADE: "+nome+" ---");
        System.out.println("Governante: "+dono.getTitulo()+" "+dono.getNome()+" | Ano: "+anoAtual);
        System.out.println("Florins: "+florins+" | Terras: "+terras+" ha | Grãos: "+graos);
        System.out.println("Servos: "+servos+" | Soldados: "+soldados+" | Construções: "+construcoes);
        System.out.println("Imposto: "+taxaImposto+"% | Justiça: "+nivelJustica);
        System.out.println("Colheita: "+(colheitaFeita?"realizada":"pendente")+" | Impostos: "+(impostosArrecadados?"arrecadados":"pendentes"));
    }
}
