import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CadastroProdutos {
    private Map<String, Produto> produtos;
    private Scanner scanner;

    public CadastroProdutos() {
        this.produtos = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public boolean adicionarProduto(Produto produto) {
        if (produtos.containsKey(produto.getCodigo())) {
            System.out.println("Produto com código já cadastrado.");
            return false;
        }

        produtos.put(produto.getCodigo(), produto);
        return true;
    }

    public void adicionarComentario(String codigoProduto, String comentario) {
        Produto produto = produtos.get(codigoProduto);
        if (produto != null) {
            produto.adicionarComentario(comentario);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public Produto buscarProduto(String codigo) {
        return produtos.get(codigo);
    }

    public Map<String, Produto> getProdutos() {
        return produtos;
    }

    public void editarProduto(String codigo, String novoNome, double novaAltura, double novaLargura, double novaProfundidade) {
        Produto produto = produtos.get(codigo);
        if (produto != null) {
            produto.setNome(novoNome);
            produto.setAltura(novaAltura);
            produto.setLargura(novaLargura);
            produto.setProfundidade(novaProfundidade);
            System.out.println("Produto editado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public boolean excluirProduto(String codigo) {
        if (produtos.containsKey(codigo)) {
            produtos.remove(codigo);
            return true;
        } else {
            System.out.println("Produto não encontrado.");
            return false;
        }
    }

    public void listarProdutos() {
        for (Produto produto : produtos.values()) {
            System.out.println(produto);
            System.out.println("Comentários:");
            for (String comentario : produto.getComentarios()) {
                System.out.println("- " + comentario);
            }
            System.out.println();
        }
    }
}
