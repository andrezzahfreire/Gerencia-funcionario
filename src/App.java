import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) {
        boolean finalizar = false;
        while (!finalizar) {
            String opcaoStr = JOptionPane.showInputDialog(null,
                    "1. Inserir Funcionario\n" +
                            "2. Buscar Funcionario\n" +
                            "3. Listar Funcionario\n" +
                            "4. Remover Funcionario\n" +
                            "5. Alterar dados\n" +
                            "6. Ler Dados\n" +
                            "7. Gravar Dados\n" +
                            "8. Finalizar\n" +
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
                    lerDados();
                    break;
                case 7:
                    gravarDados();
                    break;
                case 8:
                    finalizar = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }

    private static void gravarDados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gravarDados'");
    }

    private static void lerDados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lerDados'");
    }

    private static void alterarDados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alterarDados'");
    }

    private static void removerFunc() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removerFunc'");
    }

    private static void ListarFunc() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ListarFunc'");
    }

    private static void BuscarFunc() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BuscarFunc'");
    }

    private static void inserirFunc() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inserirFunc'");
    }
}