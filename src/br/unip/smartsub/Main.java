package br.unip.smartsub;

import br.unip.smartsub.assinatura.*;
import br.unip.smartsub.financeiro.GerenciadorArquivo;
import br.unip.smartsub.financeiro.GerenciadorFinanceiro;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorFinanceiro gerenciador = new GerenciadorFinanceiro();
        GerenciadorArquivo arquivo = new GerenciadorArquivo();
        Scanner scanner = new Scanner(System.in);

        gerenciador.setAssinaturas(arquivo.carregar());

        int opcao = 0;

        while (opcao != 5) {
            System.out.println("\n--- GERENCIADOR SMARTSUB ---");
            System.out.println("1. Cadastrar Assinatura");
            System.out.println("2. Alterar Status (Ativar/Pausar)");
            System.out.println("3. Relatório: Mês Atual (Fatura Fechada)");
            System.out.println("4. Relatório: Próximo Mês (Previsibilidade)");
            System.out.println("5. Sair e Salvar Dados");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcaoMenu(scanner);

            switch (opcao) {
                case 1:
                    System.out.print("Nome do serviço (Ex: Netflix): ");
                    String nome = scanner.nextLine();

                    System.out.print("Valor Mensal (R$): ");
                    double valor = lerValorValido(scanner);

                    System.out.print("Dia do Vencimento (1 a 31): ");
                    int dia = lerDiaValido(scanner);

                    gerenciador.adicionarAssinatura(new AssinaturaPadrao(nome, valor, Status.ATIVA, dia));
                    System.out.println("✔ Assinatura cadastrada com sucesso!");
                    break;

                case 2:
                    if (gerenciador.getAssinaturas().isEmpty()) {
                        System.out.println("Nenhuma assinatura cadastrada.");
                    } else {
                        System.out.println("\n--- ALTERAR STATUS ---");
                        for (int i = 0; i < gerenciador.getAssinaturas().size(); i++) {
                            Cobravel sub = gerenciador.getAssinaturas().get(i);
                            System.out.printf("%d. %s (Status: %s)\n", (i + 1), sub.getNome(), sub.getStatus());
                        }
                        System.out.print("Digite o número da assinatura: ");

                        int index = lerIndexValido(scanner, gerenciador.getAssinaturas().size());

                        Cobravel selecionada = gerenciador.getAssinaturas().get(index);
                        selecionada.setStatus(selecionada.getStatus() == Status.ATIVA ? Status.PAUSADA : Status.ATIVA);
                        System.out.println("✔ Status alterado para: " + selecionada.getStatus());
                    }
                    break;

                case 3:
                    System.out.println("\n--- FATURA DESTE MÊS ---");
                    gerenciador.ordenarPorVencimento();
                    for (Cobravel sub : gerenciador.getAssinaturas()) {
                        System.out.printf("Dia %02d | %s [%s]: R$ %.2f\n", sub.getDiaVencimento(), sub.getNome(), sub.getStatus(), sub.calcularCustoMensal());
                    }
                    System.out.println("------------------------------------");
                    System.out.printf("TOTAL A PAGAR NESTE MÊS: R$ %.2f\n", gerenciador.calcularTotalMesAtual());
                    break;

                case 4:
                    System.out.println("\n--- PREVISIBILIDADE PRÓXIMO MÊS ---");
                    System.out.println("Simulação caso você reative TODAS as assinaturas pausadas:");
                    gerenciador.ordenarPorVencimento();
                    for (Cobravel sub : gerenciador.getAssinaturas()) {
                        System.out.printf("Dia %02d | %s : R$ %.2f\n", sub.getDiaVencimento(), sub.getNome(), sub.getValorBase());
                    }
                    System.out.println("------------------------------------");
                    System.out.printf("GASTO TOTAL PROJETADO: R$ %.2f\n", gerenciador.calcularProjecaoProximoMes());
                    break;

                case 5:
                    arquivo.salvar(gerenciador.getAssinaturas());
                    System.out.println("Dados salvos em 'assinaturas.txt'. Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida! Selecione uma opção de 1 a 5.");
            }
        }
        scanner.close();
    }

    private static int lerOpcaoMenu(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("❌ Opção inválida! Digite um número correspondente ao menu: ");
            }
        }
    }

    private static double lerValorValido(Scanner scanner) {
        while (true) {
            try {
                String entrada = scanner.nextLine().replace(",", ".");
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.print("❌ Valor inválido! Digite novamente o valor (ex: 55,90 ou 55.90): ");
            }
        }
    }

    private static int lerDiaValido(Scanner scanner) {
        while (true) {
            try {
                int dia = Integer.parseInt(scanner.nextLine());
                if (dia >= 1 && dia <= 31) {
                    return dia;
                }
                System.out.print("❌ Dia inválido! Digite um número de 1 a 31: ");
            } catch (NumberFormatException e) {
                System.out.print("❌ Entrada inválida! Digite um número inteiro para o dia: ");
            }
        }
    }

    private static int lerIndexValido(Scanner scanner, int tamanhoLista) {
        while (true) {
            try {
                int index = Integer.parseInt(scanner.nextLine()) - 1;
                if (index >= 0 && index < tamanhoLista) {
                    return index;
                }
                System.out.print("❌ Número fora da lista! Digite um número válido correspondente: ");
            } catch (NumberFormatException e) {
                System.out.print("❌ Entrada inválida! Digite o número da assinatura desejada: ");
            }
        }
    }
}