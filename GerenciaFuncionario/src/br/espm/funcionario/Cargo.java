package br.espm.funcionario;

public enum Cargo {
    ATENDENTE(1),VENDEDOR(2),GERENTE(1);
    private int cod;
    Cargo(int d) {
        this.cod = d;
    }

    public int getCod() {
        return this.cod;
    }
    
}
