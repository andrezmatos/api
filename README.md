# 🎬 Movie Awards API

API REST desenvolvida em **Java 21 + Spring Boot 3** como parte de um **desafio técnico**, responsável por processar indicações e vencedores de prêmios de cinema e calcular os **intervalos mínimos e máximos entre vitórias consecutivas por produtor**.

---

## 🚀 Tecnologias Utilizadas

- Java 21  
- Spring Boot 3.x  
- Spring Data JPA  
- Hibernate  
- H2 Database (em memória)  
- Maven (Maven Wrapper)  
- Swagger / OpenAPI (Springdoc)  
- JUnit 5 + Mockito  

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

## 📦 Como baixar o projeto

```bash
git clone https://github.com/andrezmatos/api.git
cd api
```

---

### Pré-requisitos

- Java 21 instalado  
- Maven 3.9+

Verificar versões:

```bash
java -version
mvn -version
```

---

### Configuração do arquivo CSV

O projeto lê automaticamente um arquivo CSV no startup (fora do profile `test`).

Por padrão, o caminho está definido em:

```java
private String path = "D:\\Documents\\avaliacao\\movielist.csv";
```

⚠️ Ajuste esse caminho conforme seu ambiente local.

---

---

## ▶️ Como rodar o projeto

### Usando Maven Wrapper (recomendado)

Linux / macOS:
```bash
./mvnw spring-boot:run
```

Windows:
```bash
mvnw.cmd spring-boot:run
```

---

## 🧪 Executando os testes

```bash
./mvnw test
```

### Observações sobre testes
- O profile **test** é ativado automaticamente
- O `@PostConstruct` do `StartupService` **não é executado**
- Banco H2 em memória é utilizado

---

## 📖 Documentação da API (Swagger)

A API possui documentação interativa via **Swagger UI**, permitindo explorar e testar os endpoints diretamente pelo navegador.

### 🔗 URLs

- **Swagger UI**  
  http://localhost:8080/swagger-ui.html

- **OpenAPI JSON**  
  http://localhost:8080/v3/api-docs

### 📌 O que está documentado

- Endpoints REST
- Parâmetros e respostas
- DTOs de entrada e saída
- Códigos HTTP
- Descrição funcional de cada operação

---

## 📡 Endpoint principal

### Buscar intervalos entre vitórias

```
GET /nomination
```

### Exemplo de resposta (200 OK)

```json
{
  "max": [
    {
      "producer": "Producer A",
      "previousWin": 2001,
      "productionYear": 2005,
      "yearDiff": 4
    }
  ],
  "min": [
    {
      "producer": "Producer B",
      "previousWin": 2018,
      "productionYear": 2019,
      "yearDiff": 1
    }
  ]
}
```

---

## ⚙️ Carga inicial de dados

Os dados são carregados automaticamente na inicialização da aplicação a partir de um arquivo CSV.

Classe responsável:
```
StartupService
```

- Executada apenas fora do profile `test`
- Realiza leitura do CSV
- Persiste indicações e vencedores no banco

---

## 🛡️ Tratamento de Erros

A API utiliza um `@RestControllerAdvice` para tratamento global de exceções, garantindo:

- Respostas padronizadas
- Mensagens claras de erro
- Códigos HTTP adequados

---

## 🧠 Observações Técnicas

- Uso de **queries nativas** com Window Functions (`LAG`)
- Projections via **interface** no repository
- DTOs específicos para documentação Swagger
- Arquitetura em camadas (Controller → Service → Repository)
- Compatível com **Java 21** e **Spring Boot 3**
- Maven Wrapper para evitar dependência local do Maven

---

## 👨‍💻 Autor

Desenvolvido por **André Luiz Zahn de Matos**

---

## 📄 Licença

Uso acadêmico / desafio técnico.
