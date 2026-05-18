
# SmartSub

O SmartSub é um gerenciador financeiro via linha de comando (CLI) projetado para o controle, monitoramento e previsão de despesas com serviços recorrentes e assinaturas, como plataformas de streaming, mensalidades acadêmicas e planos de serviços.

Este projeto foi desenvolvido como Trabalho Prático (TP) para a disciplina de Programação Orientada a Objetos (POO) na UNIP. A aplicação foi estruturada seguindo boas práticas de arquitetura de software, evoluindo de um modelo estruturado para um sistema modular e resiliente.

## Demonstração Online via GitHub Codespaces

O projeto conta com um ambiente de desenvolvimento em nuvem pré-configurado. Você pode interagir com o sistema e visualizar o código-fonte diretamente pelo VS Code no seu navegador, sem necessidade de instalação local:

[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://codespaces.new/matheusmmls/SmartSub)

### Instruções para Execução

1. Clique no botão **Open in GitHub Codespaces** exibido acima.
2. Caso solicitado pela interface do GitHub, clique no botão **Create a codespace**.
3. Aguarde a inicialização completa do container e o carregamento do terminal integrado do VS Code no navegador.
4. No menu lateral esquerdo de navegação de arquivos, abra a pasta `src`.
5. Clique no arquivo `Main.java` para abrir o código-fonte na tela.
6. Clique no botão de execução (ícone de reprodução/Play) localizado no canto superior direito da interface da IDE.
7. Quando a notificação do sistema exibir a mensagem *"Run/Debug feature requires Java language server to run in Standard mode..."*, clique no botão verde **Yes**.
8. O terminal interativo do SmartSub será iniciado automaticamente no painel inferior para testes.

 ### Tutorial Visual


<img width="2278" height="1080" alt="codespacetutorial" src="https://github.com/user-attachments/assets/209c3a34-c457-47bc-9a3c-ee82d43793e8" />

---

## Funcionalidades Principais

* **CRUD Resiliente:** Permite o cadastro, listagem, atualização de status e exclusão definitiva de assinaturas. O sistema conta com tratamento rigoroso contra entradas inválidas e validação travada na etapa atual em caso de erro.
* **Painel Financeiro Consolidado:** Centraliza em uma única tela o fluxo de caixa do mês vigente, exibindo de forma clara o total já pago, o total restante a pagar e o valor consolidado da fatura.
* **Inteligência Temporal Nativa:** Utiliza a API java.time para identificar o período atual do sistema, calculando prazos de vencimento dinâmicos e exibindo alertas reais de inadimplência ou proximidade de vencimento.
* **Controle Transacional Manual:** Separa logicamente o estado contratual da assinatura (Ativa ou Pausada) da baixa transacional do mês corrente (Paga ou Não Paga).
* **Mecanismo de Escape:** Oferece uma rota de fuga amigável. O usuário pode abortar a ação atual com segurança e retornar ao menu principal digitando a palavra "sair" em qualquer etapa numérica.
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

1. Clone o repositório:
```bash
git clone [https://github.com/matheusmmls/SmartSub.git](https://github.com/matheusmmls/SmartSub.git)

```


2. Navegue até o diretório raiz do projeto.
3. Execute a aplicação via terminal:
```bash
java src/br/unip/smartsub/Main.java



