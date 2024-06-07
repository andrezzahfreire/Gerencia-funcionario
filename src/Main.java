import javax.swing.*;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.espm.funcionario.Cargo;
import br.espm.funcionario.Funcionario;

public class Main  {
   static GerenciaFunc Listafunc = new GerenciaFunc();
    public static void main(String[] args) {
        
        boolean finalizar = false;
        lerDados();
        while (!finalizar) {
            String opcaoStr = JOptionPane.showInputDialog(null,
                    "1. Inserir Funcionario\n" +
                           "2. Buscar Funcionario\n" +
                            "3. Listar Funcionario\n" +
                            "4. Remover Funcionario\n" +
                            "5. Alterar dados\n" +
                            "6. Finalizar\n" + 
                            "Escolha uma opção:");
            int opcao = Integer.parseInt(opcaoStr);
            switch (opcao)

            {
                case 1:
                    inserirFunc();
                    break;
                case 2:
                    BuscarFunc();
                    break;
                case 3:
                    ListarFunc();
                    break;
                case 4:
                    removerFunc();
                    break;
                case 5:
                    alterarDados();
                    break;
                case 6:
                    finalizar = true;
                    gravarDados();    
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
        
    }

    
    private static void lerDados() {
            String caminho = "venda.txt";

        try (FileReader fr = new FileReader(caminho);
            BufferedReader br = new BufferedReader(fr)) {
            String linha;
            Funcionario funcT = new Funcionario(null, null);

            while ((linha = br.readLine()) != null) {
                if (linha.isEmpty()) {
                    continue; 
                }

                String[] att = linha.split(";"); 

                if (att.length < 3) {
                    System.err.println("Erro: Formato de linha inválido!");
                    continue; 
                }

                try {
                    funcT.setId(Integer.parseInt(att[0]));
                } catch (NumberFormatException e) {
                    System.err.println("Erro: ID inválido: " + att[0]);
                    continue; 
                }

                if (att.length >= 2) {
                    funcT.setNome(att[1]);
                }

                if (att.length >= 3) {
                    funcT.setCargo(att[2]);
                }

                if (funcT.getId() >= 1000 && funcT.getNome() != null && funcT.getCargo() != null) {
                    Listafunc.inserirFuncionario(funcT);
                    funcT = new Funcionario(null, null); 
                }
        }

        
        if (funcT.getId() >= 1000 && funcT.getNome() != null && funcT.getCargo() != null) {
            Listafunc.inserirFuncionario(funcT);
        }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }



    private static void gravarDados() {
        String path = "venda.txt";
        String funcionarios = Listafunc.Listarfunc(); 
        
        funcionarios = funcionarios.replaceAll("ID:\\s*", ""); 
        funcionarios = funcionarios.replaceAll("Nome:\\s*", ""); 
        funcionarios = funcionarios.replaceAll("Cargo:\\s*", "");
        funcionarios = funcionarios.replaceAll("\n", ";");
        funcionarios = funcionarios.replaceAll(";;", "\n");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(funcionarios);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }              
    }
    private static void alterarDados() {
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 5, 5));
    
        
        painel.add(new JLabel("Código do funcionário:"));
        JTextField codigoField = new JTextField(15);
        painel.add(codigoField);
    
        int opcao = JOptionPane.showConfirmDialog(null, painel, "Alterar Dados Funcionário", JOptionPane.OK_CANCEL_OPTION);
    
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                int codigoFuncionario = Integer.parseInt(codigoField.getText());
    
                Funcionario funcAlt = Listafunc.pesquisarFunc(codigoFuncionario);
    
                if (funcAlt != null) {
                    alterarDadosCod(funcAlt);
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Código inválido. Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                System.err.println("Erro ao alterar dados do funcionário: " + e.getMessage());
            }
        }
    }

    private static void alterarDadosCod(Funcionario funcAlt) {
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 5, 5));
    
        JTextField campoNome = new JTextField(15);
        campoNome.setText(funcAlt.getNome());
        painel.add(campoNome);
    
        JCheckBox caixaSelecaoAten = new JCheckBox("Atendente");
        JCheckBox caixaSelecaoVend = new JCheckBox("Vendedor");
        JCheckBox caixaSelecaoGen = new JCheckBox("Gerente");
    
        ButtonGroup tpFuncionario = new ButtonGroup();
        tpFuncionario.add(caixaSelecaoAten);
        tpFuncionario.add(caixaSelecaoVend);
        tpFuncionario.add(caixaSelecaoGen);
    
        painel.add(caixaSelecaoAten);
        painel.add(caixaSelecaoVend);
        painel.add(caixaSelecaoGen);
    
        if (funcAlt.getCargo().equals(Cargo.ATENDENTE)) {
            caixaSelecaoAten.setSelected(true);
        } else if (funcAlt.getCargo().equals(Cargo.VENDEDOR)) {
            caixaSelecaoVend.setSelected(true);
        } else {
            caixaSelecaoGen.setSelected(true);
        }
        int opcao = JOptionPane.showConfirmDialog(null, painel, "Alterar Dados Funcionário", JOptionPane.OK_CANCEL_OPTION);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                if (caixaSelecaoAten.isSelected()) {
                    funcAlt.setCargo(Cargo.ATENDENTE);
                }else{
                    if (caixaSelecaoVend.isSelected()) {
                        funcAlt.setCargo(Cargo.VENDEDOR);
                    }else{
                        if (caixaSelecaoGen.isSelected()) {
                            funcAlt.setCargo(Cargo.GERENTE);
                        }
                    }
                }

                String mensagem = "Funcionário atualizado com sucesso";
                JOptionPane.showMessageDialog(null, mensagem, "Atualizar Funcionário", JOptionPane.INFORMATION_MESSAGE);


                Listafunc.atualizarFunc(funcAlt.getId(), campoNome.getText().toString(), funcAlt.getCargo());
            } catch (Exception e) {
                System.err.println("Erro ao atualizar funcionário: " + e.getMessage()); // More informative error handling
            }
        }
        
    }

    private static void removerFunc() {
        JPanel painel = new JPanel();
        String mensagem = new String();
        painel.setLayout(new GridLayout(5, 2, 5, 5)); 

    
        painel.add(new JLabel("Código do funcionário:"));
        painel.add(new JTextField(15));


        int opcao = JOptionPane.showConfirmDialog(null, painel, "Remover funcionário", JOptionPane.OK_CANCEL_OPTION);
        if (opcao == JOptionPane.OK_OPTION ) {
            try {
                Listafunc.removerFunc(Integer.parseInt(((JTextField) painel.getComponent(1)).getText()));
                mensagem = "Funcionário removido com sucesso";
                JOptionPane.showMessageDialog(null, mensagem, "Remover funcionário", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                mensagem = "funcionario não encontrado";
                JOptionPane.showMessageDialog(null, mensagem, "Remover funcionário", JOptionPane.INFORMATION_MESSAGE);         
            }
        }
    }

    private static void ListarFunc() {
        String mensagem = new String();
        mensagem = Listafunc.Listarfunc();
        if (mensagem != "") {
            JOptionPane.showMessageDialog(null, mensagem, "Listar Funcionários", JOptionPane.INFORMATION_MESSAGE);
            
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado", "Listar Funcionários", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }

    private static void BuscarFunc() {
        JPanel painel = new JPanel();
        String mensagem = new String();
        painel.setLayout(new GridLayout(5, 2, 5, 5)); 

    
        painel.add(new JLabel("Código do funcionário:"));
        painel.add(new JTextField(15));


        int opcao = JOptionPane.showConfirmDialog(null, painel, "Buscar funcionário", JOptionPane.OK_CANCEL_OPTION);
        if (opcao == JOptionPane.OK_OPTION ) {
            try {
                mensagem = Listafunc.pesquisarFunc(Integer.parseInt(((JTextField) painel.getComponent(1)).getText())).toString();
                JOptionPane.showMessageDialog(null, mensagem, "Funcionário encontrado", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Funcionario não encontrado", "Funcionário não encontrado", JOptionPane.INFORMATION_MESSAGE);     
            }
            
            
        }

    }

    private static void inserirFunc() {
        Funcionario funcionario = new Funcionario(null, null);
        if (Listafunc.pesquisarFunc(funcionario.getId())!= null) {
            funcionario.gerarId();
        }
        String mensagem = new String();
       JPanel painel = new JPanel();
       painel.setLayout(new GridLayout(5, 2, 5, 5)); 

    
       painel.add(new JLabel("Nome:"));
       painel.add(new JTextField(15));

       JCheckBox caixaSelecaoAten = new JCheckBox("Atendente");
       JCheckBox caixaSelecaoVend = new JCheckBox("vendedor");
       JCheckBox caixaSelecaoGen = new JCheckBox("Gerente");

       ButtonGroup tpFuncionario = new ButtonGroup();
       tpFuncionario.add(caixaSelecaoAten);
       tpFuncionario.add(caixaSelecaoVend);
       tpFuncionario.add(caixaSelecaoGen);

       painel.add(caixaSelecaoAten);
       painel.add(caixaSelecaoVend);
       painel.add(caixaSelecaoGen);


       int opcao = JOptionPane.showConfirmDialog(null, painel, "Cadastrar funcionário", JOptionPane.OK_CANCEL_OPTION);


       if (opcao == JOptionPane.OK_OPTION) {
    
           funcionario.setNome(((JTextField) painel.getComponent(1)).getText());
           
           boolean opcaoAtenSelecionada = caixaSelecaoAten.isSelected();
           boolean opcaoVendjSelecionada = caixaSelecaoVend.isSelected();
           boolean opcaoGenjSelecionada = caixaSelecaoGen.isSelected();

                if (opcaoAtenSelecionada) {
                    funcionario.setCargo(Cargo.ATENDENTE);
                }else{
                    if (opcaoVendjSelecionada) {
                        funcionario.setCargo(Cargo.VENDEDOR);
                    }else{
                        if (opcaoGenjSelecionada) {
                            funcionario.setCargo(Cargo.GERENTE);
                        }
                    }
                }

                mensagem = "Funcionário cadastrado com sucesso";
                JOptionPane.showMessageDialog(null, mensagem, "Inserir Funcionário", JOptionPane.INFORMATION_MESSAGE);
               Listafunc.inserirFuncionario(funcionario);
       }
    
    }

}