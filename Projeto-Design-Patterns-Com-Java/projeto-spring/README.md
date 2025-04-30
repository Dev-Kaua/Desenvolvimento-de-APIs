# 🧠 Projeto: Padrões de Projeto com Spring Boot

Este projeto foi desenvolvido como parte de um desafio prático do bootcamp da Digital Innovation One (DIO).  
O objetivo foi aplicar e demonstrar o uso de **padrões de projeto (Design Patterns)** no contexto de uma API REST usando **Java** e **Spring Boot**.

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (banco de dados em memória)
- API ViaCEP (consulta de CEP)
- Maven

---

## 📐 Padrões de Projeto Aplicados

- **Singleton** – Controle de instância única para configurações.
- **Strategy** – Encapsulamento de algoritmos para operações de CRUD.
- **Facade** – Interface simplificada para integração com o serviço ViaCEP.

---

## 📌 Endpoints principais

| Método | Rota              | Ação                            |
|--------|-------------------|---------------------------------|
| POST   | `/clientes`       | Cadastrar novo cliente          |
| GET    | `/clientes`       | Listar todos os clientes        |
| GET    | `/clientes/{id}`  | Buscar cliente por ID           |
| PUT    | `/clientes/{id}`  | Atualizar cliente               |
| DELETE | `/clientes/{id}`  | Remover cliente                 |

---

## 🧪 Como testar

1. Rode a aplicação via IntelliJ (classe `Application.java`)
2. Use o **Postman** ou **Insomnia** para fazer requisições aos endpoints
3. Teste com diferentes CEPs para ver a integração com a API ViaCEP

---

## 💡 Aprendizados

✔ Aplicar padrões de projeto no mundo real  
✔ Criar uma API RESTful com boas práticas  
✔ Integrar serviços externos (API ViaCEP)  
✔ Usar banco de dados em memória para testes rápidos

---

## 📂 Estrutura do projeto

projeto-spring ├── model ├── repository ├── service ├── controller ├── config └── Application.java

---

## 👨‍💻 Autor

Kauã Reis – Estudante de Análise e Desenvolvimento de Sistemas  IFSC –
Bootcamp DIO – Java + Spring Boot  