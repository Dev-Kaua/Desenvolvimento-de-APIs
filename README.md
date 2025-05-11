# 🗂️ Projeto: Board de Gerenciamento de Tarefas

## 🧠 Descrição

Este projeto simula um **quadro de tarefas (board)** inspirado em ferramentas como Trello.  
Permite gerenciar tarefas em colunas personalizadas, com movimentação controlada, bloqueio/desbloqueio de cartões e persistência no banco de dados MySQL.

---

## 🚀 Como executar

### ✅ Pré-requisitos
- Java 17 ou superior
- IntelliJ IDEA (ou outra IDE compatível com Gradle)
- XAMPP (ou MySQL local)
- DBeaver (opcional, para visualizar o banco de forma amigável)

### 🛠️ Configurando o banco de dados
1. Inicie o XAMPP e ative o **MySQL**
2. No `phpMyAdmin`, crie um banco de dados com o nome:
   board

yaml
Copiar
Editar
3. No projeto, abra:
   src/main/java/br/com/dio/persistence/config/ConnectionConfig.java
   E configure com seu usuário e senha do MySQL:
```java
String url = "jdbc:mysql://localhost:3306/board";
String user = "root";
String password = "SUA_SENHA_AQUI";
```
### ▶️ Executando o projeto
Abra o projeto no IntelliJ IDEA

Aguarde a sincronização do Gradle

Rode a classe:

Copiar
Editar
br.com.dio.Main

## 🎯 Funcionalidades
📌 Criar múltiplos boards com colunas customizáveis

🧩 Criar cards com título, descrição, data e status de bloqueio

🔀 Mover cards entre colunas (respeitando regras de fluxo)

🔒 Bloquear e desbloquear cards com justificativa

❌ Cancelar tarefas em qualquer etapa

💾 Persistência no banco de dados (MySQL)

🕐 (Opcional) Relatórios com tempo gasto e histórico de bloqueios

## 📐 Requisitos Atendidos
✅ Menu de navegação (Criar, Selecionar, Excluir board)

✅ Regras de colunas (inicial, pendente, final e cancelamento)

✅ Controle de movimentação entre colunas

✅ Bloqueio e desbloqueio com justificativa

✅ Armazenamento no banco de dados

✅ (Opcional) Relatórios com estatísticas por card e coluna

## 🗂️ Estrutura do Projeto
```
📦 src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂br.com.dio
 ┃ ┃ ┃ ┣ 📜Main.java
 ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┣ 📂persistence
 ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┗ 📂utils
 ┣ 📜build.gradle
 ┣ 📜settings.gradle
 ┗ 📜README.md ✅
```

## 👨‍💻 Autor
Kauã Reis
Desenvolvedor Java em formação
Bootcamp Java Cloud Native – DIO & Bradesco
Curso Técnico – IFSC / Análise e Desenvolvimento de Sistemas

## 📎 Observações
Caso a conexão MySQL falhe, revise porta, usuário/senha e se o XAMPP está rodando

Dúvidas ou bugs? Verifique o terminal para mensagens de erro e revise ConnectionConfig.java

Feito com 💻 + ☕ + muita força de vontade.