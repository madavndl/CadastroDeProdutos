import java.util.ArrayList;
import java.util.List;

public class Produto {
    private String codigo;
    private String nome;
    private double altura;
    private double largura;
    private double profundidade;
    private List<String> comentarios;

    public Produto(String codigo, String nome, double altura, double largura, double profundidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.altura = altura;
        this.largura = largura;
        this.profundidade = profundidade;
        this.comentarios = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(double profundidade) {
        this.profundidade = profundidade;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void adicionarComentario(String comentario) {
        comentarios.add(comentario);
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Nome: " + nome + ", Altura: " + altura +
                ", Largura: " + largura + ", Profundidade: " + profundidade;
    }
}
