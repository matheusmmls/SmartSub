# SmartSub

O SmartSub é um gerenciador financeiro via linha de comando (CLI) projetado para o controle, monitoramento e previsão de despesas com serviços recorrentes e assinaturas, como plataformas de streaming, mensalidades acadêmicas e planos de serviços.

Este projeto foi desenvolvido como Trabalho Prático (TP) para a disciplina de Programação Orientada a Objetos (POO) na UNIP. A aplicação foi estruturada seguindo boas práticas de arquitetura de software, evoluindo de um modelo estruturado para um sistema modular e resiliente.

## Demonstração Online

O projeto está configurado e pronto para ser executado diretamente no navegador, sem a necessidade de instalação local do Java:

[Clique aqui para executar o SmartSub no Replit](https://replit.com/@matheusmmls2006/SmartSub)

### Instruções para Execução no Replit

1. Acesse o link da demonstração online fornecido acima.
2. Clique no botão verde "Project".
3. O terminal interativo do SmartSub será iniciado automaticamente no painel lateral.

<img width="810" height="522" alt="reptilit" src="https://github.com/user-attachments/assets/94775600-6791-4c5b-96c8-3778c122ee48" />

---

## Funcionalidades Principais

* **CRUD Resiliente:** Permite o cadastro, listagem, atualização de status e exclusão definitiva de assinaturas. O sistema conta com tratamento rigoroso contra entradas inválidas (como suporte a ponto e vírgula em valores decimais) e validação travada na etapa atual em caso de erro.
* **Painel Financeiro Consolidado:** Centraliza em uma única tela o fluxo de caixa do mês vigente, exibindo de forma clara o total já pago, o total restante a pagar e o valor consolidado da fatura.
* **Inteligência Temporal Nativa:** Utiliza a API java.time para identificar o período atual do sistema, calculando prazos de vencimento dinâmicos e exibindo alertas reais de inadimplência (contas atrasadas) ou proximidade de vencimento.
* **Controle Transacional Manual:** Separa logicamente o estado contratual da assinatura (Ativa ou Pausada) da baixa transacional do mês corrente (Paga ou Não Paga), impedindo que o sistema assuma pagamentos falsos por decurso de tempo.
* **Mecanismo de Escape:** Oferece uma rota de fuga amigável em todas as etapas numéricas de cadastro e gerenciamento. O usuário pode abortar a ação atual com segurança e retornar ao menu principal digitando a palavra "sair".
* **Persistência de Dados Local:** Armazenamento automático e leitura de dados estruturados em arquivo de texto local (assinaturas.txt).

---

## Decisões Arquiteturais e POO

O design do software demonstra a aplicação prática de padrões de desenvolvimento modernos e conceitos maduros de Orientação a Objetos:

* **Arquitetura em Camadas (Visão Desacoplada):** Aplicação estrita do Princípio da Responsabilidade Única (SRP). A interface de usuário (SmartSubConsole) está isolada das regras de negócio e motores de cálculo (GerenciadorFinanceiro) e da persistência em disco (GerenciadorArquivo).
* **Abstração e Encapsulamento:** Uso da interface Cobravel e da classe abstrata AssinaturaBase para proteger os estados dos objetos e garantir flexibilidade para extensões futuras do sistema.
* **Polimorfismo:** Métodos de cálculo de custos que variam dinamicamente de acordo com o estado do contrato da assinatura, eliminando estruturas condicionais rígidas e repetitivas.

---

## Como Executar Localmente

### Pré-requisitos
* Java JDK 17 ou superior instalado na máquina.

### Passo a Passo
1. Clone o repositório para o seu ambiente local:
   ```bash
   git clone [https://github.com/matheusmmls/SmartSub.git](https://github.com/matheusmmls/SmartSub.git)
   
2. Navegue até o diretório raiz do projeto.
   
3. Compile e execute a classe principal através da sua IDE de preferência ou diretamente pelo terminal:
   ```bash
   java src/br/unip/smartsub/Main.java
   
