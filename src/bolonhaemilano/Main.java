package bolonhaemilano;

/** Inicializa a aplicação e transfere o controle para o menu. */
public final class Main {
    private Main() { }
    public static void main(String[] args) {
        new Menu().executar();
    }
}
