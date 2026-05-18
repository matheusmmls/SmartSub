package br.unip.smartsub.assinatura;

public class AssinaturaPadrao extends AssinaturaBase {

    public AssinaturaPadrao(String nome, double valorBase, Status status, int diaVencimento) {
        super(nome, valorBase, status, diaVencimento);
    }

    @Override
    public double calcularCustoMensal() {
        // Se pausada, retorna 0.0, senão retorna o valor real.
        return getStatus() == Status.ATIVA ? getValorBase() : 0.0;
    }
}