# Bolonha e Milano

**Aluno:** Luis Carlos Souza da Silva  
**Linguagem:** Java 17 ou superior  
**Pacote:** `bolonhaemilano`

## Sobre

Projeto acadêmico de estratégia econômica medieval. Cada participante administra uma cidade-estado, negocia recursos, realiza atividades agrícolas, define impostos, recruta soldados e constrói estruturas. A partida suporta de um a quatro jogadores, com rodadas anuais e promoção por prestígio.

## Estrutura do repositório

```text
src/bolonhaemilano/   Código-fonte Java
uml/                  Diagrama de classes e sequência (PlantUML)
casos-de-uso/         Diagrama de casos de uso (PlantUML)
README.md             Instruções do projeto
```

## Compilar e executar

Na raiz do projeto:

```bash
javac -encoding UTF-8 -d bin src/bolonhaemilano/*.java
java -cp bin bolonhaemilano.Main
```

## Executar os testes

```bash
java -cp bin bolonhaemilano.Testes
```

Os testes usam apenas Java padrão, sem JUnit ou dependências externas. Cada verificação prepara o cenário, executa a operação e confere o resultado.

## Regras centrais implementadas

- Recursos iniciais por cidade: 1000 florins, 10000 hectares, 500 grãos e 200 servos; ano inicial 1400.
- Mercado: grão custa 2 florins e terra custa 1 florim por unidade.
- Agricultura: colheita limitada por terras e mão de obra; colheita e arrecadação ocorrem uma vez por ano.
- Governo: imposto entre 0% e 50%; níveis de justiça Justa, Moderada e Severa.
- Construções: soldado custa 5 florins e 1 servo; construção custa 20 florins.
- Prestígio: construções × 10 + soldados × 2 + servos + terras ÷ 100.
- A partida aceita até quatro cidades e termina quando alguém alcança Rei/Rainha ou após o ano de 1410.

## Diagramas

Os arquivos `.puml` podem ser renderizados com PlantUML. Os diagramas estão em `uml/` e `casos-de-uso/`.
