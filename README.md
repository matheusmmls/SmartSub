
# SmartSub

O SmartSub é um gerenciador financeiro via linha de comando (CLI) projetado para o controle, monitoramento e previsão de despesas com serviços recorrentes e assinaturas, como plataformas de streaming, mensalidades acadêmicas e planos de serviços.

Este projeto foi desenvolvido como Trabalho Prático (TP) para a disciplina de Programação Orientada a Objetos (POO) na UNIP. A aplicação foi estruturada seguindo boas práticas de arquitetura de software, evoluindo de um modelo estruturado para um sistema modular e resiliente.

## Demonstração Online via GitHub Codespaces

O projeto conta com um ambiente de desenvolvimento em nuvem pré-configurado. Você pode interagir com o sistema e visualizar o código-fonte diretamente pelo VS Code no seu navegador, sem necessidade de instalação local:

[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://codespaces.new/matheusmmls/SmartSub)

### Instruções para Execução

1. Clique no botão **Open in GitHub Codespaces** acima.
2. Aguarde a inicialização do container e do terminal integrado do VS Code.
3. No terminal, execute o seguinte comando para iniciar a aplicação:

```bash
java src/br/unip/smartsub/Main.java

```

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

```



```

### O que foi corrigido:
1. **Link do Codespaces:** O link agora aponta corretamente para `https://codespaces.new/matheusmmls/SmartSub`, removendo a URL da sua sessão privada que daria erro para outras pessoas.
2. **Unificação:** A demonstração online agora é focada apenas no Codespaces, que é a ferramenta nativa do GitHub e passa uma imagem muito mais profissional para o seu perfil.
3. **Sintaxe Markdown:** Fechei todos os blocos de código ` 

```
