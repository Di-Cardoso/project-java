import common.services.FuncionarioService;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            FuncionarioService.mostrarMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> FuncionarioService.inserirFuncionarios();
                case 2 -> FuncionarioService.removerJoao();
                case 3 -> FuncionarioService.imprimirFuncionarios();
                case 4 -> FuncionarioService.aumentarSalario();
                case 5 -> FuncionarioService.agruparPorFuncao();
                case 6 -> FuncionarioService.imprimirAgrupados();
                case 8 -> FuncionarioService.imprimirAniversariantes();
                case 9 -> FuncionarioService.imprimirMaisVelho();
                case 10 -> FuncionarioService.imprimirOrdemAlfabetica();
                case 11 -> FuncionarioService.imprimirTotalSalarios();
                case 12 -> FuncionarioService.imprimirSalariosMinimos();
                case 0 -> System.out.println("Encerrando programa...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}
