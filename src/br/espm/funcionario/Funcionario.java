package br.espm.funcionario;

import java.util.Random;

public class Funcionario implements Comparable<Funcionario> {
    private int id;
    private String nome;
    private Cargo cargo;
    

    public Funcionario(String nome, Cargo cargo) {
        this.id = gerarId();
        this.nome = nome;
        this.cargo = cargo;
    }

    public Funcionario(Integer id, String nome, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
    }

    public int gerarId() {
        Random gerador = new Random();
        int numAle = 1000 + gerador.nextInt(8999);
        return numAle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    @Override
    public String toString(){
        return "ID: "+this.getId()+"\n" + 
        "Nome: " + this.getNome() +"\n" 
        + "Cargo: "+this.getCargo()+"\n";
    }
    @Override
    public int compareTo(Funcionario outroFunc) {
        return Integer.compare(this.getId(), outroFunc.getId());
    }
    @Override
    public boolean equals(Object obj) {
        return obj != null
            && (obj instanceof Funcionario)
            && ((Funcionario) obj).getId() == id;
    }

    public void setCargo(String cargo) {
        if (cargo.equals("ATENDENTE")) {
            this.cargo = Cargo.ATENDENTE;
        }else{
            if (cargo.equals("VENDEDOR")) {
                this.cargo = Cargo.VENDEDOR;
            }else if (cargo.equals("GERENTE")) {
                this.cargo = Cargo.GERENTE;
            }
        }
    }

   

    
    
}
