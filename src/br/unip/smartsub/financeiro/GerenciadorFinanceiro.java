package br.unip.smartsub.financeiro;

import br.unip.smartsub.assinatura.Cobravel;
import br.unip.smartsub.assinatura.Status;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

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

    public double calcularTotalPagoMesAtual() {
        double totalPago = 0.0;
        for (Cobravel assinatura : assinaturas) {
            if (assinatura.getStatus() == Status.ATIVA && assinatura.isPago()) {
                totalPago += assinatura.calcularCustoMensal();
            }
        }
        return totalPago;
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

    public String obterNomeMesAtual() {
        YearMonth mesAtual = YearMonth.now();
        return mesAtual.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")).toUpperCase() + "/" + mesAtual.getYear();
    }

    public String obterNomeProximoMes() {
        YearMonth proximoMes = YearMonth.now().plusMonths(1);
        return proximoMes.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")).toUpperCase() + "/" + proximoMes.getYear();
    }
}