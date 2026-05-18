package br.unip.smartsub;

import br.unip.smartsub.assinatura.*;
import br.unip.smartsub.financeiro.GerenciadorArquivo;
import br.unip.smartsub.financeiro.GerenciadorFinanceiro;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorFinanceiro gerenciador = new GerenciadorFinanceiro();
        GerenciadorArquivo arquivo = new GerenciadorArquivo();
        Scanner scanner = new Scanner(System.in);

        gerenciador.setAssinaturas(arquivo.carregar());

        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n--- GERENCIADOR SMARTSUB ---");
            System.out.println("1. Cadastrar Assinatura");
            System.out.println("2. Gerenciar / Dar Baixa em Assinatura");
            System.out.println("3. Exibir Painel Financeiro Consolidado");
            System.out.println("4. Sair e Salvar Dados");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcaoMenu(scanner);

            switch (opcao) {
                case 1:
                    System.out.print("Nome do serviço (ou digite 'sair' para cancelar): ");
                    String nome = scanner.nextLine();
                    if (nome.equalsIgnoreCase("sair")) {
                        System.out.println("❌ Cadastro cancelado pelo usuário.");
                        break;
                    }

                    System.out.print("Valor Mensal (R$) (ou digite 'sair' para cancelar): ");
                    double valor = lerValorValido(scanner);
                    if (valor == -1.0) {
                        System.out.println("❌ Cadastro cancelado pelo usuário.");
                        break;
                    }

                    System.out.print("Dia do Vencimento (1 a 31) (ou digite 'sair' para cancelar): ");
                    int dia = lerDiaValido(scanner);
                    if (dia == -1) {
                        System.out.println("❌ Cadastro cancelado pelo usuário.");
                        break;
                    }

                    gerenciador.adicionarAssinatura(new AssinaturaPadrao(nome, valor, Status.ATIVA, dia));
                    System.out.println("✔ Assinatura cadastrada com sucesso!");
                    break;

                case 2:
                    if (gerenciador.getAssinaturas().isEmpty()) {
                        System.out.println("Nenhuma assinatura cadastrada.");
                    } else {
                        System.out.println("\n--- GERENCIAR ASSINATURA ---");
                        for (int i = 0; i < gerenciador.getAssinaturas().size(); i++) {
                            Cobravel sub = gerenciador.getAssinaturas().get(i);
                            System.out.printf("%d. %s [%s]\n", (i + 1), sub.getNome(), sub.getStatus());
                        }
                        System.out.print("Digite o número da assinatura (ou digite 'sair' para cancelar): ");

                        int index = lerIndexValido(scanner, gerenciador.getAssinaturas().size());
                        if (index == -2) {
                            System.out.println("❌ Ação cancelada.");
                            break;
                        }

                        Cobravel selecionada = gerenciador.getAssinaturas().get(index);

                        System.out.println("\nO que deseja fazer com '" + selecionada.getNome() + "'?");
                        System.out.println("1. Marcar como Paga (Dar baixa este mês)");
                        System.out.println("2. Pausar / Reativar Serviço (Contrato)");
                        System.out.println("3. EXCLUIR Assinatura Permanentemente 🗑");
                        System.out.println("4. Voltar ao Menu Principal");
                        System.out.print("Escolha uma opção: ");

                        int subOpcao = lerSubMenuValido(scanner);

                        if (subOpcao == 1) {
                            selecionada.setPago(true);
                            System.out.println("✔ Assinatura marcada como PAGA!");
                        } else if (subOpcao == 2) {
                            selecionada.setStatus(selecionada.getStatus() == Status.ATIVA ? Status.PAUSADA : Status.ATIVA);
                            System.out.println("✔ Status alterado para: " + selecionada.getStatus());
                        } else if (subOpcao == 3) {
                            gerenciador.getAssinaturas().remove(index);
                            System.out.println("🔥 '" + selecionada.getNome() + "' foi excluída permanentemente!");
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- PAINEL FINANCEIRO: " + gerenciador.obterNomeMesAtual() + " ---");
                    gerenciador.ordenarPorVencimento();
                    int diaAtual = LocalDate.now().getDayOfMonth();

                    for (Cobravel sub : gerenciador.getAssinaturas()) {
                        String statusTempo;
                        double valorExibido = sub.calcularCustoMensal();

                        if (sub.getStatus() == Status.PAUSADA) {
                            statusTempo = "PAUSADA";
                        } else if (sub.isPago()) {
                            statusTempo = "PAGA";
                        } else if (sub.getDiaVencimento() < diaAtual) {
                            statusTempo = "ATRASADA / NÃO PAGA";
                        } else if (sub.getDiaVencimento() == diaAtual) {
                            statusTempo = "VENCE HOJE";
                        } else {
                            int diasRestantes = sub.getDiaVencimento() - diaAtual;
                            statusTempo = "PENDENTE - Vence em " + diasRestantes + " dias";
                        }

                        System.out.printf("Dia %02d | %s [%s]: R$ %.2f\n", sub.getDiaVencimento(), sub.getNome(), statusTempo, valorExibido);
                    }
                    System.out.println("------------------------------------");
                    double pago = gerenciador.calcularTotalPagoMesAtual();
                    double total = gerenciador.calcularTotalMesAtual();
                    double restante = total - pago;

                    System.out.printf("Total já pago este mês: R$ %.2f\n", pago);
                    System.out.printf("Total restante a pagar: R$ %.2f\n", restante);
                    System.out.printf("FATURA TOTAL DE %s: R$ %.2f\n", gerenciador.obterNomeMesAtual(), total);
                    System.out.println();
                    System.out.printf("🔮 PREVISÃO PARA %s: R$ %.2f\n", gerenciador.obterNomeProximoMes(), gerenciador.calcularProjecaoProximoMes());
                    break;

                case 4:
                    arquivo.salvar(gerenciador.getAssinaturas());
                    System.out.println("Dados salvos em 'assinaturas.txt'. Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida! Selecione uma opção de 1 a 4.");
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

    private static int lerSubMenuValido(Scanner scanner) {
        while (true) {
            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                if (opcao >= 1 && opcao <= 4) {
                    return opcao;
                }
                System.out.print("❌ Opção inválida! Digite 1, 2, 3 ou 4: ");
            } catch (NumberFormatException e) {
                System.out.print("❌ Opção inválida! Digite um número correspondente: ");
            }
        }
    }

    private static double lerValorValido(Scanner scanner) {
        while (true) {
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("sair")) {
                return -1.0;
            }
            try {
                entrada = entrada.replace(",", ".");
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.print("❌ Valor inválido! Digite o valor novamente (ou 'sair' para cancelar): ");
            }
        }
    }

    private static int lerDiaValido(Scanner scanner) {
        while (true) {
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("sair")) {
                return -1;
            }
            try {
                int dia = Integer.parseInt(entrada);
                if (dia >= 1 && dia <= 31) {
                    return dia;
                }
                System.out.print("❌ Dia inválido! Digite um número de 1 a 31 (ou 'sair' para cancelar): ");
            } catch (NumberFormatException e) {
                System.out.print("❌ Entrada inválida! Digite um número inteiro para o dia (ou 'sair' para cancelar): ");
            }
        }
    }

    private static int lerIndexValido(Scanner scanner, int tamanhoLista) {
        while (true) {
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("sair")) {
                return -2;
            }
            try {
                int index = Integer.parseInt(entrada) - 1;
                if (index >= 0 && index < tamanhoLista) {
                    return index;
                }
                System.out.print("❌ Número fora da lista! Digite um número correspondente (ou 'sair' para cancelar): ");
            } catch (NumberFormatException e) {
                System.out.print("❌ Entrada inválida! Digite o número desejado (ou 'sair' para cancelar): ");
            }
        }
    }
}