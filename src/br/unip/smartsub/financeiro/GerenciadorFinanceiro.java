package br.unip.smartsub.financeiro;

import br.unip.smartsub.assinatura.Cobravel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GerenciadorFinanceiro {
    private List<Cobravel> assinaturas;

    public GerenciadorFinanceiro() {
        this.assinaturas = new ArrayList<>();
    }

    public void setAssinaturas(List<Cobravel> assinaturasCarregadas) {
        this.assinaturas = assinaturasCarregadas;
    }

    public void adicionarAssinatura(Cobravel assinatura) {
        this.assinaturas.add(assinatura);
    }

    public List<Cobravel> getAssinaturas() {
        return this.assinaturas;
    }

    public double calcularTotalMesAtual() {
        double total = 0.0;
        for (Cobravel assinatura : assinaturas) {
            total += assinatura.calcularCustoMensal();
        }
        return total;
    }

    public double calcularProjecaoProximoMes() {
        double totalProjetado = 0.0;
        for (Cobravel assinatura : assinaturas) {
            totalProjetado += assinatura.getValorBase();
        }
        return totalProjetado;
    }

    public void ordenarPorVencimento() {
        assinaturas.sort(Comparator.comparingInt(Cobravel::getDiaVencimento));
    }
}