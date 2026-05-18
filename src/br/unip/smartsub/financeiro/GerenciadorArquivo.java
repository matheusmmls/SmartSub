package br.unip.smartsub.financeiro;

import br.unip.smartsub.assinatura.AssinaturaPadrao;
import br.unip.smartsub.assinatura.Cobravel;
import br.unip.smartsub.assinatura.Status;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivo {
    private static final String NOME_ARQUIVO = "assinaturas.txt";

    public void salvar(List<Cobravel> assinaturas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Cobravel sub : assinaturas) {
                writer.println(sub.getNome() + "," + sub.getValorBase() + "," + sub.getStatus() + "," + sub.getDiaVencimento() + "," + sub.isPago());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar as assinaturas no arquivo: " + e.getMessage());
        }
    }

    public List<Cobravel> carregar() {
        List<Cobravel> lista = new ArrayList<>();
        File arquivo = new File(NOME_ARQUIVO);

        if (!arquivo.exists()) {
            return lista;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 5) {
                    String nome = dados[0];
                    double valor = Double.parseDouble(dados[1]);
                    Status status = Status.valueOf(dados[2]);
                    int dia = Integer.parseInt(dados[3]);
                    boolean pago = Boolean.parseBoolean(dados[4]);

                    Cobravel assinatura = new AssinaturaPadrao(nome, valor, status, dia);
                    assinatura.setPago(pago);
                    lista.add(assinatura);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar assinaturas: " + e.getMessage());
        }
        return lista;
    }
}