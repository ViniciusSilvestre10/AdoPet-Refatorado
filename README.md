# Adopet API - Refatoração e Padrões de Projeto

## 💻 Sobre o projeto

O **Adopet** é uma API Rest em Java desenvolvida para um ecossistema fictício de adoção de pets, englobando o cadastro de tutores, abrigos, pets e o gerenciamento de solicitações de adoção.

O principal objetivo deste repositório foi realizar uma **refatoração profunda do código legado**, aplicando padrões de projeto (Design Patterns) consolidados para isolar as regras de negócio, eliminar códigos duplicados (como blocos try-catch em cascata) e garantir uma arquitetura flexível, testável e escalável.

---

## ⚙️ Funcionalidades

- [x] Cadastro e atualização de tutores com validações isoladas;
- [x] Cadastro de abrigos com checagem de duplicidade;
- [x] Cadastro de pets vinculados a abrigos (busca inteligente por ID ou Nome);
- [x] Listagem de pets disponíveis para adoção;
- [x] Solicitação de adoção;
- [x] Aprovação e reprovação de adoções;

---

## 🛠️ Padrões de Projeto Aplicados (Design Patterns)

A refatoração transformou o código monolítico e acoplado utilizando os seguintes padrões:

*   **Data Transfer Object (DTO):** Implementado utilizando Java *Records* para blindar as entidades do banco de dados (`@Entity`), controlando estritamente os dados que entram e saem da API e evitando loops de serialização JSON.
*   **Service Layer:** Criação de classes de serviço para centralizar e isolar toda a lógica de negócio, retirando a responsabilidade de processamento dos Controllers.
*   **Strategy (Padrões GoF):** Utilizado para isolar algoritmos de validação específicos (como validações de tutor e abrigo), permitindo que novas regras sejam adicionadas sem modificar o código existente (Open-Closed Principle).
*   **Chain of Responsibility (Padrões GoF):** Aplicado no fluxo de validações de adoção, onde cada validação executa sua regra e passa para o próximo elo da corrente.
*   **Global Exception Handler (`@RestControllerAdvice`):** Centralização do tratamento de erros do sistema (como `EntityNotFoundException` ou exceções customizadas), eliminando blocos `try-catch` repetitivos nos controllers e services e garantindo retornos HTTP limpos (ex: `404 Not Found`).

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3** (Spring Data JPA, Spring Web)
- **Maven**
- **MySQL**
- **Hibernate**
- **Flyway** (Migrações de banco de dados)
