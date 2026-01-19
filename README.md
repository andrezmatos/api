# 📽️ Movie Awards API – Spring Boot

API REST desenvolvida em **Java 21** com **Spring Boot**, responsável por processar dados de indicações e vencedores de prêmios de cinema a partir de um arquivo CSV, calculando **intervalos mínimos e máximos entre vitórias por produtor**.

O projeto foi desenvolvido como **desafio técnico**, seguindo boas práticas de arquitetura, testes automatizados e código limpo.

---

## 🧩 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database (testes)
- JPA Native Queries
- Lombok
- OpenCSV
- JUnit 5
- Mockito

---

## 📁 Estrutura do Projeto

```
src
 └── main
     ├── java
     │   └── com.framboesa.api
     │       ├── controller
     │       ├── service
     │       ├── repository
     │       ├── model
     │       ├── dto
     │       └── util
     └── resources
 └── test
     └── java
         └── com.framboesa.api
             ├── controller
             ├── service
             └── repository
```

---

## 🚀 Como executar o projeto

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/andrezmatos/api.git
cd api
```

---

### 2️⃣ Pré-requisitos

- Java 21 instalado  
- Maven 3.9+

Verificar versões:

```bash
java -version
mvn -version
```

---

### 3️⃣ Configuração do arquivo CSV

O projeto lê automaticamente um arquivo CSV no startup (fora do profile `test`).

Por padrão, o caminho está definido em:

```java
private String path = "D:\\Documents\\avaliacao\\movielist.csv";
```

⚠️ Ajuste esse caminho conforme seu ambiente local.

---

### 4️⃣ Executar a aplicação

```bash
mvn spring-boot:run
```

Ou:

```bash
mvn clean package
java -jar target/*.jar
```

---

## 🌐 Endpoints Disponíveis

### 🔹 Buscar intervalos de vitórias (mínimo e máximo)

```
GET /nomination
```

---

## 🧪 Executando os Testes

```bash
mvn test
```

Os testes usam o **profile `test`**, que:
- Não executa `@PostConstruct`
- Usa banco em memória (H2)
- Não lê arquivo CSV real

---

## 🧱 Arquitetura e Boas Práticas

- Separação clara entre Controller, Service e Repository
- Uso de DTOs e Projections
- Queries SQL otimizadas
- Testes isolados e de integração
- Código compatível com Java 21

---

## 👤 Autor

Desenvolvido por **[André Luiz Zahn de Matos]**

---

## 📄 Licença

Projeto desenvolvido exclusivamente para fins de avaliação técnica.
