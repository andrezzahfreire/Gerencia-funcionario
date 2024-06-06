package br.espm.venda;

import br.espm.funcionario.Funcionario;

public class Venda {
    Funcionario funcionario;
    Double valor;
    public Venda(Funcionario funcionario, Double valor) {
        this.funcionario = funcionario;
        this.valor = valor;
    }

    public double calcularComissao(){
        switch (funcionario.getCargo()) {
            case ATENDENTE:
                return valor*.1;
            case VENDEDOR:
                return valor*.15 + 5;
            case GERENTE:
                return valor*.2 + 10;
            default:
                return valor;
        }
    }
    
}
