import br.espm.funcionario.Cargo;
import br.espm.funcionario.Funcionario;

public class GerenciaFunc extends AVLTree<Funcionario>  {
    public void inserirFuncionario(Funcionario funcionario){
        
        if (pesquisarFunc(funcionario.getId()) == null ) {
             this.inserir(funcionario);
        }else{
            System.out.println("Funcionário já existe");
            inserirFuncionario(funcionario);
        }
        

    }

    public String Listarfunc(){
        return this.preOrdem();
    }

    public Funcionario pesquisarFunc(int id) {
        Funcionario func = new Funcionario(null, null);
        func.setId(id);

        try {
            func =  this.pesquisar(func).getDado();
        } catch (Exception e) {
            System.out.println("Funcionario não encontrado");
        }
        return func;

    }

    public void removerFunc(int id){
        Funcionario func = new Funcionario(null, null);
        func.setId(id);

        try {
            this.remover(func);
            System.out.println("Funcionário excluido com sucesso");
        } catch (Exception e) {
            System.out.println("Erro ao excluir o funcionário");
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

    

    

        
}
