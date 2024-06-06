import br.espm.funcionario.Cargo;
import br.espm.funcionario.Funcionario;

public class Main {
    public static void main(String[] args) throws Exception {
        GerenciaFunc Listafunc = new GerenciaFunc();
        Funcionario teste = new Funcionario("Lorena", null);
        Funcionario teste2 = new Funcionario("Andrezza", null);
        teste2.setId(123);
        Listafunc.inserirFuncionario(teste);
        Listafunc.inserirFuncionario(teste2);
        System.out.println(Listafunc.Listarfunc());
        System.out.println(Listafunc.pesquisarFunc(123));
        //Listafunc.removerFunc(123);
        Listafunc.atualizarFunc(123, "Oi", Cargo.ATENDENTE);
        System.out.println(Listafunc.Listarfunc());
    }
}
