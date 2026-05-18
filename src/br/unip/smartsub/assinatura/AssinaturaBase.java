package br.unip.smartsub.assinatura;

public abstract class AssinaturaBase implements Cobravel {
    private final String nome;
    private final double valorBase;
    private final int diaVencimento;
    private Status status;

    public AssinaturaBase(String nome, double valorBase, Status status, int diaVencimento) {
        this.nome = nome;
        this.valorBase = valorBase;
        this.status = status;
        this.diaVencimento = diaVencimento;
    }

    @Override
    public String getNome() { return this.nome; }

    @Override
    public double getValorBase() { return this.valorBase; }

    @Override
    public Status getStatus() { return this.status; }

    @Override
    public void setStatus(Status status) { this.status = status; }

    @Override
    public int getDiaVencimento() { return this.diaVencimento; }
}