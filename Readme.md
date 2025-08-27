# Resumo de Textos com IA - Protótipo em Java

Este projeto é um **protótipo de API REST** desenvolvido em **Java** com **Spring Boot** para **resumir textos automaticamente** utilizando a API do **Hugging Face** (modelo `facebook/bart-large-cnn`). A aplicação segue **arquitetura Hexagonal**, separando claramente as responsabilidades de Adapter, Application, Domain e Config.

---

## Tecnologias e Bibliotecas Utilizadas

* Java 17
* Spring Boot 3.5.5
* FeignClient (para chamadas HTTP à API externa)
* Lombok
* JUnit 5 + Mockito (testes unitários)
* Swagger/OpenAPI (documentação)
* Hugging Face API (IA para sumarização de textos)

---

## Estrutura do Projeto

```
src/main/java
 ├─ adapter
 │   ├─ input            # Controller da API REST
 │   └─ output           # FeignClient para comunicação com Hugging Face
 ├─ application
 │   ├─ mapper           # Mapeamento de DTOs
 │   ├─ service          # Lógica de negócio
 │   └─ input            # Interfaces que o Service implementa
 ├─ config               # Configurações da aplicação
 ├─ domain
 │   └─ exception        # Exceções personalizadas
 └─ dto
     ├─ request          # DTOs de requisição
     └─ response         # DTOs de resposta
```

### Principais Classes

* **GenerateSummaryService**: Serviço que gerencia chamadas à API de IA, tratamento de erros e retorno de resultados.
* **SummaryMapper**: Classe responsável por mapear a resposta externa (`SummaryItemResponse`) para o DTO da aplicação (`SummaryResponseDto`).
* **ISummaryFeignClient**: Interface FeignClient que envia os textos para a Hugging Face.
* **SummaryRequestDto**: DTO de requisição contendo lista de textos a serem resumidos.
* **SummaryResponseDto**: DTO de resposta retornando o resumo do primeiro texto enviado.
* **SummaryItemResponse**: Representa cada item retornado pela Hugging Face.

---

## Endpoints

### POST `api-generate/summary`

Gera resumo para um ou mais textos.

**Request Header:**

```
Authorization: Bearer <SEU_TOKEN>
Content-Type: application/json
```

**Request Body:**

```json
{
  "inputs": [
    "Texto para resumir"
  ]
}
```

**Response:**

```json
{
  "summaryTextResponse": "Texto resumido"
}
```

> ⚠️ Atualmente, a API retorna apenas o resumo do primeiro texto enviado. Futuras versões podem processar múltiplos resumos simultaneamente.

---

## Funcionalidades do Serviço

* Recebe múltiplos textos via JSON (`inputs`).
* Envia textos para a API do Hugging Face e recebe os resumos.
* Mapeia o resultado para `SummaryResponseDto`.
* Trata erros de conexão, resposta nula ou lista vazia.
* Registra logs das operações.
* Testes unitários implementados com JUnit 5 e Mockito.

---

## Testes

Foram criados testes unitários que:

* Validam o mapeamento correto das respostas.
* Simulam falhas na chamada à API externa.
* Garantem que exceções específicas sejam lançadas quando apropriado.


## Documentação Swagger

* Swagger/OpenAPI configurado para geração automática de documentação.
* Acesse via `http://localhost:8080/swagger-ui.html`.

---

## Como Executar

1. Clone o repositório:

```bash
git clone <URL_DO_REPOSITORIO>
```

2. Configure seu token da Hugging Face como variável de ambiente ou header.
3. Execute a aplicação com Maven:

```bash
mvn spring-boot:run
```

4. Teste a API via Postman ou outro cliente HTTP.

---

## Observações

* Protótipo voltado para **aprendizado e estudo de integração com IA**.
* Futuras melhorias podem incluir:

    * Processamento de múltiplos resumos.
    * Melhor tratamento de erros e logs.
    * Implementação de cache para desempenho.
    * Suporte a outros modelos de IA e personalização de prompts.

---
