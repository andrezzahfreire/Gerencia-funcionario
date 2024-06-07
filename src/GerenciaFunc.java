import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import br.espm.funcionario.Cargo;
import br.espm.funcionario.Funcionario;

public class GerenciaFunc extends AVLTree<Funcionario>  {
    public void inserirFuncionario(Funcionario funcionario){
        this.inserir(funcionario);
    }

    public String Listarfunc(){
        return this.preOrdem();
    }

    public Funcionario pesquisarFunc(int id) {
        Funcionario func = new Funcionario(null, null);
        func.setId(id);
        if (func != null) {
            try {
                func =  this.pesquisar(func).getDado();
                return func;
            } catch (Exception e) {
                System.out.println("funcionario não encontrado");     
            }
        }
        return null;

    }

    public void removerFunc(int id){
        Funcionario func = new Funcionario(null, null);
        func.setId(id);

        try {
            this.remover(func);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Funcionário não existente", "Remover Funcionário", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void atualizarFunc(int id,String nome, Cargo cargo){
        Funcionario func = new Funcionario(nome, cargo);
        func = pesquisarFunc(id);
        if (nome != null)
            func.setNome(nome);
        if (cargo != null)
            func.setCargo(cargo); 
        
        //overload
    }

    public void LerDadosArquivo(){
        String caminho = "venda.txt";
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(caminho);
            br = new BufferedReader(fr);
            String linha;
            System.out.println("Valores de Vendas:");
            while ((linha = br.readLine()) != null) {
            // Converte a linha lida para o tipo double e imprime
            double valorVenda = Double.parseDouble(linha);
            System.out.println(valorVenda);
            }
            } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            } catch (NumberFormatException e) {
            System.err.println("Erro ao converter valor para double: " + e.getMessage());
            }
            finally {
                try {
                if (br != null) {
                br.close();
                }
                if (fr != null) {
                fr.close();
                }
                } catch (IOException ex) {
                System.err.println("Erro ao fechar o arquivo: " + ex.getMessage());
                }
                }
                
            
    }

    public void GravarDadosArquivo(){
        String path = "vendas.txt";
        double[] vendas = {259.99, 499.50, 123.45, 987.65};
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
        for (double venda : vendas) {
        bw.write(Double.toString(venda));
        bw.newLine(); // Adiciona uma quebra de linha após cada venda
        }
        } catch (IOException e) {
        System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

    }

    

    

        
}
