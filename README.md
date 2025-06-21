# API de Autenticação e Autorização JWT (Emissão e Validação Interna) #

## Descrição: ##
Esta API foi desenvolvida com Spring Boot e implementa um sistema de autenticação e autorização baseado em JWT (JSON Web Tokens). Ela exigem segurança robusta, escalabilidade e um modelo stateless, protegendo recursos e endpoints com eficiência.

A aplicação segue as melhores práticas de desenvolvimento, com foco em:

- **🔐 Segurança:** Controle de acesso com validação de tokens, roles e endpoints protegidos.

- **🧪 Testabilidade:** Cobertura de testes automatizados para garantir estabilidade e confiança.

- **📚 Documentação automática:** Integrada com Swagger/OpenAPI, permitindo explorar e testar os endpoints de forma interativa.

_______________________________________________________________________________________________________________________________________


## 🛠️ Tecnologias e Dependências Utilizadas# ##

- **Spring Boot Starter Web:** Criação de APIs REST de forma rápida e simplificada.

- **Spring Boot Starter Security:** Implementação de autenticação e autorização com filtros de segurança integrados.

- **Spring Boot Starter OAuth2 Resource Server:** Validação de tokens JWT com suporte nativo ao padrão OAuth2.

- **Spring Boot Starter Data JPA:** Abstração para persistência de dados com JPA e integração com bancos de dados relacionais.

- **H2 Database (em memória):** Banco de dados leve e rápido, ideal para desenvolvimento e testes.

- **Lombok:** Reduz a verbosidade do código, eliminando boilerplate como getters, setters e construtores.

- **Springdoc OpenAPI:** Geração automática de documentação Swagger interativa e atualizada.

- **Spring Boot DevTools:** Suporte a hot reload para acelerar o desenvolvimento.

- **JUnit 5 e Mockito** Ferramentas modernas para criação de testes automatizados e simulação de dependências.

- **Auth0 Java JWT:** Biblioteca para criação, assinatura e verificação de tokens JWT de forma segura.
_________________________________________________________________________________________________________________________________________

## 🧩Estrutura do Projeto ##
O projeto segue a estrutura do Spring Boot:

- **Model:**
  
User – Entidade JPA que representa o usuário autenticado, contendo username, senha codificada e role (perfil de acesso).

- **Repository:**
  
UserRepository – Interface responsável por operações de acesso a dados com Spring Data JPA, incluindo busca por username.

- **Service:**

JwtService – Responsável pela geração, validação e extração de informações do token JWT.

AuthService – Implementa a lógica de autenticação e orquestra a emissão de tokens JWT.

- **Controller:**

AuthController – Expõe endpoints públicos para login e validação de tokens.

TestProtectedController – Contém endpoints protegidos, acessíveis apenas com autenticação e autorização via JWT.

- **Config**

SecurityConfig – Define as regras de segurança da aplicação, como filtros JWT, autenticação baseada em roles, e criação de usuários iniciais para testes.
__________________________________________________________________________________________________________________________________________________________________

## ⚙️ Configuração do Ambiente de Desenvolvimento

A configuração da API — incluindo comportamento da aplicação, propriedades de segurança e parâmetros dos tokens JWT — é centralizada no arquivo application.yml.

Nele, são definidos elementos essenciais como:

Porta do servidor

Chave secreta para assinatura dos tokens JWT

Tempo de expiração dos tokens

Configurações específicas do banco de dados e do ambiente

Essa abordagem facilita a manutenção, promove a segurança e permite ajustes dinâmicos sem necessidade de alterar o código-fonte.

_______________________________________________________________________________________________________________________________________


# Principais Desenvolvimento das Funcinalidade da API 

## ✅ Autenticação ##
  
 - Endpoint: POST /auth/login
 - Entrada: username e password
 - Saída: Retorna um token JWT assinado caso as credenciais estejam corretas.
 - Utiliza codificação de senha segura e autenticação stateless.

## 🔍 Validação de Token ##
  
- Endpoint: POST /auth/validate
- Verifica se um token JWT é válido e não expirou.
- Retorna confirmação de validade ou mensagem de erro em caso de token inválido/expirado.

## 🔐 Proteção de Endpoints ##

- Integração com o Spring Security para proteger rotas da API.
- Stateless: não utiliza sessões. Toda requisição deve conter um token JWT válido.
- Suporte a controle de acesso baseado em roles (ex.: ADMIN, USER) com anotação @PreAuthorize.
______________________________________________________________________________________________________________________

# 🌐 Acessando e Executando a API via Swagger ##

Após iniciar a aplicação, você pode acessar a documentação interativa da API no seguinte endereço:

🔗 http://localhost:8081/swagger-ui/index.html#/

Nesse portal, você poderá:

- Visualizar todos os endpoints disponíveis

- Ler descrições detalhadas de cada operação (login, validação, recursos protegidos, etc.)

- Executar requisições diretamente da interface (como envio de credenciais e obtenção de JWT)

- Testar o comportamento dos endpoints protegidos com tokens válidos

  **💡 Dica: Para acessar rotas protegidas, clique no botão "Authorize" no topo da interface Swagger e forneça o token JWT no formato**


![API](https://github.com/user-attachments/assets/1d6ba933-4766-477d-96a4-0b08bfc0d4a1)

_______________________________________________________________________________________________________________________________________________

## 🧑‍⚖️ Testes Automatizados ##

Testes de integração foram implementados utilizando JUnit 5 e MockMvc para garantir o correto funcionamento dos principais fluxos da API:

-**✅ Login com credenciais válidas**

Verifica se o token JWT é gerado corretamente quando o login é bem-sucedido.

-**❌ Login com credenciais inválidas**

Garante que a API retorne status HTTP 401 (Unauthorized) ao fornecer senha incorreta.

-**🔐 Acesso negado a endpoints protegidos sem token**

Confirma que endpoints seguros exigem autenticação e retornam erro quando acessados sem JWT.

-**🔄 Acesso autorizado com token válido**

Valida que usuários autenticados conseguem acessar endpoints protegidos normalmente.

-**⛔ Restrições de acesso por perfil (role)**

Testa se usuários com perfil USER são impedidos de acessar rotas restritas a ADMIN.


Esses testes asseguram a confiabilidade da autenticação, autorização e segurança da aplicação em diferentes cenários reais.

________________________________________________________________________________________________________________________________________________________


## 📈 Testes de Carga (JMeter) ##

Foi implementado um plano de teste com o Apache JMeter para simular múltiplos usuários acessando simultaneamente o endpoint de login da API. 
O objetivo é avaliar a performance, estabilidade e capacidade de resposta do sistema sob carga.

-**Cenário de teste configurado:**

- 🔄 Endpoint testado: POST /auth/login

- 👥 Usuários simultâneos (Threads): 200

- ⏱️ Ramp-up period: 20 segundos (os usuários iniciam gradualmente nesse tempo)

- 🔁 Loop Count: 10 (cada usuário executa 10 logins consecutivos)
  

-**Ferramentas de análise configuradas:**

- ✅ View Results Tree: permite visualizar requisições e respostas detalhadas

- 📊 Summary Report: apresenta métricas consolidadas como:

- Tempo médio de resposta (Average)

- Vazão (Throughput)

- Porcentagem de erros (Error %)
- 

- **Objetivo**

Identificar gargalos, avaliar a performance do login em condições reais de uso e verificar a robustez da autenticação com JWT sob carga pesada.

![Summary Report](https://github.com/user-attachments/assets/f4c6269c-23e9-49cb-ad37-9aa08597e45d)

________________________________________________________________________________________________________________________________________________________



## 🔧 Como Executar o Projeto

- **Clone o repositório:**

git clone https://github.com/seu-usuario/sua-api-jwt.git
cd sua-api-jwt

- **Configure a variável de ambiente (em produção):**

Defina a chave secreta do JWT de forma segura:

export JWT_SECRET=suachavesecreta  # Linux/macOS
set JWT_SECRET=suachavesecreta     # Windows

💡 Em desenvolvimento, a chave pode ser definida diretamente no application.yml.

- **Execute a aplicação:**

Via Maven:

./mvnw spring-boot:run

Ou diretamente pela sua IDE (IntelliJ, Eclipse, VS Code, etc.) executando a classe DemojwttApplication.

_______________________________________________________________________________________________________________________________


## Usuários Criados no startup no desenvolvimento do Projeto

- admin / senha: 123456 (role ADMIN)
- user / senha: password (role USER) 

_________________________________________________________________________________________________________________________________

## Links dos portais para Acesso

- API em: http://localhost:8081
- H2 Console em: http://localhost:8081/h2-console
- Swagger UI em: http://localhost:8081/swagger-ui/index.html#/

__________________________________________________________________________________________________________________________________
# Conclusão

Este projeto demonstra a construção de uma API RESTful segura e moderna utilizando Spring Boot 3.x com autenticação e autorização baseadas em JWT. 
Ao implementar melhores práticas de segurança stateless, validação de tokens, e controle de acesso por roles, garantimos um sistema robusto 
e escalável, adequado para arquiteturas de microsserviços.

Além disso, a integração com ferramentas como Swagger para documentação automática, JUnit para testes automatizados e JMeter para testes 
de carga assegura qualidade, manutenibilidade e desempenho confiável da aplicação.

Esse projeto serve como uma base sólida para sistemas que demandam autenticação segura, fácil expansão e monitoramento contínuo, 
podendo ser adaptado para diversos cenários de negócio.

__________________________________________________________________________________________________________________________________________________

## Autor

Daiane Dos Santos Andrade.

https://www.linkedin.com/in/daiane-dos-santos-17732183/
