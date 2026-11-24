package bolonhaemilano;

import java.util.List;
import java.util.Scanner;

/** Interface textual. As regras do jogo ficam nas classes de domínio e serviço. */
public class Menu {
    private final Scanner teclado=new Scanner(System.in);
    private boolean emExecucao=true;
    private Partida partida;
    private Cidade cidade;
    private final Mercado mercado=new Mercado();
    private final Agricultura agricultura=new Agricultura();
    private final Governo governo=new Governo();
    private final Construcoes construcoes=new Construcoes();
    private final Nobreza nobreza=new Nobreza();

    public void executar(){
        while(emExecucao){
            mostrarMenu(); int op=lerInteiro("Opção: ");
            switch(op){case 1: iniciarPartida();break;case 2: mostrarStatus();break;case 3: menuMercado();break;case 4: menuAgricultura();break;case 5: menuGoverno();break;case 6: menuConstrucoes();break;case 7: encerrarTurno();break;case 8: mostrarClassificacao();break;case 9: sobre();break;case 0: emExecucao=false;break;default:System.out.println("Opção inválida.");}
        }
        teclado.close(); System.out.println("Até a próxima!");
    }
    private void mostrarMenu(){System.out.println("\n========== BOLONHA E MILANO ==========");System.out.println("Ano: "+(partida==null?"—":partida.getAnoAtual())+" | Cidade da vez: "+(cidade==null?"—":cidade.getNome()));System.out.println("1 Novo jogo | 2 Status | 3 Mercado | 4 Agricultura | 5 Governo");System.out.println("6 Construções | 7 Encerrar turno | 8 Classificação | 9 Sobre | 0 Sair");}
    private int lerInteiro(String prompt){System.out.print(prompt);String linha=teclado.nextLine();try{return Integer.parseInt(linha.trim());}catch(NumberFormatException e){return Integer.MIN_VALUE;}}
    private boolean exigirPartida(){if(partida==null||cidade==null){System.out.println("Inicie um jogo antes de usar esta opção.");return false;}return true;}
    private void iniciarPartida(){
        if(partida!=null){System.out.println("Já existe uma partida em andamento.");return;}
        int n=lerInteiro("Quantidade de jogadores (1 a 4; inválido inicia com 2): ");if(n<1||n>4)n=2;
        Partida nova=new Partida();String[] cidades={"Bolonha","Milano","Florença","Veneza"};
        for(int i=0;i<n;i++){System.out.print("Nome do jogador "+(i+1)+": ");String nome=teclado.nextLine().trim();if(nome.isEmpty())nome="Jogador "+(i+1);Jogador j=new Jogador(nome);nova.adicionarCidade(new Cidade(cidades[i],j));}
        partida=nova;cidade=partida.getCidadeAtual();System.out.println("Partida iniciada! É a vez de "+cidade.getDono().getNome()+".");
    }
    private void mostrarStatus(){if(exigirPartida())cidade.exibirStatus();}
    private int quantidade(){return lerInteiro("Quantidade: ");}
    private void menuMercado(){if(!exigirPartida())return;System.out.println("Mercado: 1 comprar grãos, 2 vender grãos, 3 comprar terras, 4 vender terras");int op=lerInteiro("Opção: "),q;if(op<1||op>4)return;q=quantidade();boolean ok=op==1?mercado.comprarGraos(cidade,q):op==2?mercado.venderGraos(cidade,q):op==3?mercado.comprarTerras(cidade,q):mercado.venderTerras(cidade,q);System.out.println(ok?"Operação concluída.":"Operação recusada: quantidade ou recursos inválidos.");}
    private void menuAgricultura(){if(!exigirPartida())return;System.out.println("Agricultura: 1 colher, 2 distribuir alimentos");int op=lerInteiro("Opção: ");if(op==1){int area=agricultura.processarColheita(cidade);System.out.println(area<0?"A colheita já foi realizada neste ano.":"Área colhida: "+area+" hectares.");}else if(op==2){int p=lerInteiro("Percentual de grãos (20 a 80): ");System.out.println(agricultura.distribuirAlimentos(cidade,p)?"Alimentos distribuídos.":"Percentual inválido.");}}
    private void menuGoverno(){if(!exigirPartida())return;System.out.println("Governo: 1 arrecadar, 2 definir imposto, 3 definir justiça");int op=lerInteiro("Opção: ");if(op==1){int v=governo.arrecadarImpostos(cidade);System.out.println(v<0?"Impostos já arrecadados neste ano.":"Arrecadados "+v+" florins.");}else if(op==2){int t=lerInteiro("Taxa (0 a 50): ");System.out.println(governo.definirTaxaImposto(cidade,t)?"Taxa atualizada.":"Taxa inválida.");}else if(op==3){System.out.println("1 Justa | 2 Moderada | 3 Severa");int j=lerInteiro("Nível: ");String nivel=j==1?"Justa":j==2?"Moderada":j==3?"Severa":"";System.out.println(governo.definirJustica(cidade,nivel)?"Justiça atualizada.":"Nível inválido.");}}
    private void menuConstrucoes(){if(!exigirPartida())return;System.out.println("Construções: 1 recrutar soldados, 2 construir");int op=lerInteiro("Opção: "),q;if(op!=1&&op!=2)return;q=quantidade();boolean ok=op==1?construcoes.recrutarSoldados(cidade,q):construcoes.construir(cidade,q);System.out.println(ok?"Operação concluída.":"Recursos insuficientes ou quantidade inválida.");}
    private void encerrarTurno(){if(!exigirPartida())return;boolean fechou=partida.passarTurno();if(fechou){List<Cidade> promovidas=partida.encerrarAno(nobreza);System.out.println("Ano encerrado. Novo ano: "+partida.getAnoAtual());for(Cidade c:promovidas)System.out.println(c.getDono().getNome()+" foi promovido(a) a "+c.getDono().getTitulo()+".");}if(partida.terminou()){Cidade vencedor=partida.determinarVencedor(nobreza);System.out.println("Fim da partida! Vencedor: "+vencedor.getDono().getNome()+" ("+vencedor.getDono().getTitulo()+").");partida=null;cidade=null;}else{cidade=partida.getCidadeAtual();System.out.println("Agora é a vez de "+cidade.getDono().getNome()+" — "+cidade.getNome()+".");}}
    private void mostrarClassificacao(){if(!exigirPartida())return;System.out.println("\nCLASSIFICAÇÃO — ano "+partida.getAnoAtual());for(Cidade c:partida.getCidades())System.out.println(c.getNome()+" | "+c.getDono().getNome()+" | "+c.getDono().getTitulo()+" | prestígio "+nobreza.calcularPrestigio(c)+" | próximo título "+nobreza.prestigioParaProximoTitulo(c.getDono()));}
    private void sobre(){System.out.println("Bolonha e Milano: jogo de estratégia econômica medieval. Administre recursos, população e governo para alcançar o maior prestígio.");}
}
