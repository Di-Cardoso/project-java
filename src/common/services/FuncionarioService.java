package common.services;

import common.classes.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioService {

    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static Map<String, List<Funcionario>> map = new HashMap<>();
    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat FORMATADOR_NUMERO = NumberFormat.getInstance(new Locale("pt", "BR"));
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void mostrarMenu() {
        System.out.println("\nSelecione uma opção:");
        System.out.println("1 – Inserir todos os funcionários");
        System.out.println("2 – Remover o funcionário João");
        System.out.println("3 – Imprimir todos os funcionários formatados");
        System.out.println("4 – Dar aumento de 10% a todos");
        System.out.println("5 – Agrupar funcionários por função em um MAP");
        System.out.println("6 – Imprimir os funcionários agrupados por função");
        System.out.println("8 – Imprimir aniversariantes dos meses 10 e 12");
        System.out.println("9 – Imprimir funcionário com maior idade");
        System.out.println("10 – Imprimir lista em ordem alfabética");
        System.out.println("11 – Imprimir total dos salários");
        System.out.println("12 – Imprimir quantos salários mínimos ganha cada funcionário");
        System.out.println("0 – Sair");
        System.out.print("Opção: ");
    }

    public static void inserirFuncionarios() {
        funcionarios = new ArrayList<>(List.of(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));
        System.out.println("Funcionários inseridos com sucesso!");
    }

    public static void removerJoao() {
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));
        System.out.println("Funcionário João removido, se existia.");
    }

    public static void imprimirFuncionarios() {
        funcionarios.forEach((System.out::println));
    }

    public static void aumentarSalario() {
        funcionarios.forEach(f ->
                f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));
        System.out.println("Todos os salários foram aumentados em 10%.");
    }

    public static void agruparPorFuncao() {
        map = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        System.out.println("Funcionários agrupados em Map por função.");
    }

    public static void imprimirAgrupados() {
        map.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println("   " + f.getNome()));
        });
    }

    public static void imprimirAniversariantes() {
        Set<Integer> meses = Set.of(10, 12);

        funcionarios.stream()
                .filter(f -> meses.contains(f.getDataNascimento().getMonthValue()))
                .forEach(f -> System.out.println(f.getNome() + " - Nascimento: " + f.getDataNascimento().format(FORMATADOR_DATA)));
    }

    public static void imprimirMaisVelho() {
        funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .ifPresent(f -> {
                    int idade = Period.between(f.getDataNascimento(), LocalDate.now()).getYears();
                    System.out.println("Mais velho: " + f.getNome() + " - Idade: " + idade);
                });
    }

    public static void imprimirOrdemAlfabetica() {
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()));
    }

    public static void imprimirTotalSalarios() {
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: " + FORMATADOR_NUMERO.format(total));
    }

    public static void imprimirSalariosMinimos() {
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " ganha " + qtd + " salários mínimos.");
        });
    }
}



