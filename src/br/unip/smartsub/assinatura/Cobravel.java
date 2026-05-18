package br.unip.smartsub.assinatura;

public interface Cobravel {
    double calcularCustoMensal();
    String getNome();
    Status getStatus();
    void setStatus(Status status);
    int getDiaVencimento();
    double getValorBase();
}