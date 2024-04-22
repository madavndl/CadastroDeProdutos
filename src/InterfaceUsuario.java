import java.util.Map;
import java.util.Scanner;

public class InterfaceUsuario {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CadastroProdutos cadastro = new CadastroProdutos();

    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    adicionarComentario();
                    break;
                case 3:
                    buscarProduto();
                    break;
                case 4:
                    listarProdutos();
                    break;
                case 5:
                    editarProduto();
                    break;
                case 6:
                    excluirProduto();
                    break;
                case 7:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Adicionar Comentário");
        System.out.println("3. Buscar Produto");
        System.out.println("4. Listar Produtos");
        System.out.println("5. Editar Produto");
        System.out.println("6. Excluir Produto");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números.");
            }
        }
    }

    private static void cadastrarProduto() {
        boolean codigoRepetido = true;
        while (codigoRepetido) {
            System.out.print("Digite o código do produto: ");
            String codigo = scanner.nextLine();

            if (cadastro.getProdutos().containsKey(codigo)) {
                System.out.println("Código já cadastrado. Por favor, digite um código único.");
            } else {
                codigoRepetido = false;
                System.out.print("Digite o nome do produto: ");
                String nome = scanner.nextLine();
                double altura = lerNumero("Digite a altura do produto: ");
                double largura = lerNumero("Digite a largura do produto: ");
                double profundidade = lerNumero("Digite a profundidade do produto: ");

                Produto produto = new Produto(codigo, nome, altura, largura, profundidade);
                boolean cadastrado = cadastro.adicionarProduto(produto);
                if (cadastrado) {
                    System.out.println("Produto cadastrado com sucesso.");
                }
            }
        }
    }

    private static double lerNumero(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números.");
            }
        }
    }

    private static void adicionarComentario() {
        boolean codigoValido = false;

        while (!codigoValido) {
            System.out.print("Digite o código do produto: ");
            String codigo = scanner.nextLine();

            if (!cadastro.getProdutos().containsKey(codigo)) {
                System.out.println("Código de produto inválido. Por favor, adicione um código válido.");
            } else {
                codigoValido = true;
                System.out.print("Digite o comentário: ");
                String comentario = scanner.nextLine();

                cadastro.adicionarComentario(codigo, comentario);
                System.out.println("Comentário adicionado ao produto.");
            }
        }
    }

    private static void buscarProduto() {
        System.out.print("Digite o código do produto: ");
        String codigo = scanner.nextLine();

        Produto produto = cadastro.buscarProduto(codigo);
        if (produto != null) {
            System.out.println("Produto encontrado: " + produto);
            System.out.println("Comentários:");
            for (String comentario : produto.getComentarios()) {
                System.out.println("- " + comentario);
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void listarProdutos() {
        System.out.println("Lista de Produtos:");

        Map<String, Produto> produtos = cadastro.getProdutos();

        for (Produto produto : produtos.values()) {
            System.out.println(produto.getCodigo() + " - " + produto.getNome());
        }

        System.out.print("Digite o código do produto para ver detalhes ou digite 0 para sair: ");
        String codigoEscolhido = scanner.nextLine();

        if (!codigoEscolhido.equals("0")) {
            Produto produtoEscolhido = produtos.get(codigoEscolhido);
            if (produtoEscolhido != null) {
                System.out.println("Produto selecionado: " + produtoEscolhido);
                System.out.println("Comentários:");
                for (String comentario : produtoEscolhido.getComentarios()) {
                    System.out.println("- " + comentario);
                }
            } else {
                System.out.println("Produto não encontrado.");
            }
        } else {
            System.out.println("Saindo...");
        }
    }

    private static void editarProduto() {
        System.out.print("Digite o código do produto que deseja editar: ");
        String codigo = scanner.nextLine();
        System.out.print("Digite o novo nome do produto: ");
        String novoNome = scanner.nextLine();
        double novaAltura = lerNumero("Digite a nova altura do produto: ");
        double novaLargura = lerNumero("Digite a nova largura do produto: ");
        double novaProfundidade = lerNumero("Digite a nova profundidade do produto: ");

        cadastro.editarProduto(codigo, novoNome, novaAltura, novaLargura, novaProfundidade);
    }

    private static void excluirProduto() {
        boolean codigoValido = false;
        while (!codigoValido) {
            System.out.print("Digite o código do produto que deseja excluir: ");
            String codigo = scanner.nextLine();

            boolean excluido = cadastro.excluirProduto(codigo);
            if (excluido) {
                codigoValido = true;
                System.out.println("Produto excluído com sucesso.");
            } else {
                System.out.println("Produto não encontrado. Por favor, digite um código válido.");
            }
        }
    }
}
