package common.classes;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Funcionario extends Pessoa {

    private BigDecimal salario;

    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        setNome(nome);
        setDataNascimento(dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat formatador = NumberFormat.getInstance(new Locale("pt", "BR"));

        return String.format("Nome: %s | Data Nascimento: %s | Salário: %s | Função: %s",
                getNome(),
                getDataNascimento().format(formatoData),
                formatador.format(salario),
                funcao);
    }
}
