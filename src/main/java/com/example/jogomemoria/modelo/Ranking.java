package com.example.jogomemoria.modelo;

public class Ranking {
    private String nome;
    private int pontos;
    private int posicao;
    private int tempo;

    public Ranking() {
    }

    public Ranking(String nome, int pontos, int posicao, int tempo) {
        this.nome = nome;
        this.pontos = pontos;
        this.posicao = posicao;
        this.tempo = tempo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "nome='" + nome + '\'' +
                ", pontos=" + pontos +
                ", posicao=" + posicao +
                ", tempo=" + tempo +
                '}';
    }
}
