# FoodTech
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/grupo27-6soat-fiap/FoodTech/blob/ac0b311cf474702ef975e8d188af4a854badcd0f/LICENSE)

## GRUPO 27 - 6SOAT 

## Autores

- Henrique Rodrigues da Silva RM 353376
- Felipe Reis - RM 353932
- Lucas Marques - RM 353910
- Lucas Domingues - RM 353900

## Linkedin

- https://www.linkedin.com/in/felipe-reis-028a38181/
- https://www.linkedin.com/in/henrique-rodrigues-639873173/
- https://www.linkedin.com/in/lucas-domingues-de-souza-565720a1/
- https://www.linkedin.com/in/lucas-marques-a4848a52/


# Tecnologias utilizadas

- ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
- ![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54)
- ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
- ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
- ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
- ![Kubernetes](https://img.shields.io/badge/kubernetes-%23326ce5.svg?style=for-the-badge&logo=kubernetes&logoColor=white)

## Base de dados
- ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
  
# Sobre o projeto

FoodTech é um projeto que desenvolve uma aplicação para um restaurante fast food, com ênfase na parte backend. Utilizamos Docker e aplicamos os conceitos de arquitetura hexagonal. Dentro dessa aplicação, documentamos as APIs usando o Swagger. Vale destacar que os requisitos fornecidos aos alunos não incluíam a parte de frontend.

O objetivo deste projeto é implementar um sistema que atenda às necessidades dos clientes, incluindo funcionários, proprietários e usuários do restaurante. O sistema visa proporcionar autonomia, escalabilidade, resiliência e praticidade em todos os aspectos de sua implementação.

Para desenvolver este software, utilizamos a metodologia DDD (Domain-Driven Design). Aplicando esses conceitos, conseguimos nos aprofundar nos requisitos e proporcionar uma experiência agradável e eficiente aos nossos clientes.

### Link Swagger (Executar após rodar a aplicação no Kubernetes):
[![Swagger](https://img.shields.io/badge/Swagger-%2380BFFF.svg?style=for-the-badge&logo=swagger&logoColor=white)](http://localhost:30002/swagger-ui/index.html)

## Domain-driven design
![image](https://github.com/grupo27-6soat-fiap/foodtech/blob/master/driver.png)

# Para acessar a Collection da API Postman clique na imagem:
[![Postman](https://img.shields.io/badge/Postman-%23FF6C37.svg?style=for-the-badge&logo=postman&logoColor=white)
](https://github.com/grupo27-6soat-fiap/FoodTech/blob/d3f3ef36e8a29110127a1fbbd6bbf869c77d5dfc/CollectionAPIPostman.json)

# Para acessar o Miro clique na imagem:
[![Miro](https://img.shields.io/badge/Miro-%2300B2A9.svg?style=for-the-badge&logo=miro&logoColor=white)
](https://miro.com/app/board/uXjVKStNlQI=/?share_link_id=418037355467)

# Arquitetura Infraestrutura Kubernetes:
Visando atender os requisitos do nosso projeto utilizamos a arquitetura Kubernetes em conjunto com o Docker como provedor de infraestrutura, aproveitando ao máximo os recursos nativos oferecidos pela plataforma. Dentro do cluster Kubernetes, estabelecemos o namespace "food-techchallenge-api" para agrupar todos os recursos diretamente relacionados à nossa aplicação. Além disso, reservamos o namespace "db_techfood" para nosso banco de dados gerenciado internamente pelo Kubernetes. Dentro do namespace "food-techchallenge-api", adotamos uma abordagem de segmentação dos componentes com base em suas responsabilidades específicas, o que facilita a visualização e compreensão da nossa estrutura arquitetônica. Essa prática visa proporcionar uma organização clara e intuitiva dos elementos que compõem a aplicação. Na imagem abaixo ilustramos como está sendo arquitetado o processo e também a comunicação entre eles, onde estamos expondo para a internet na porta 30002 nossa aplicação Java com Springboot e intermante na temos a aplicação MySql sendo executada em um outro pode, para persistir os dados criamos um volume para o banco e toda essa comunicação da aplicação com a base de dados está sendo feita através das services, para provisionar uma escalabilidade ao nosso projeto estamos utilizando o HPA que é responsável por verificar as métricas dos pod's e criar replicas para atender a necessidade de requisições.
![image](https://github.com/grupo27-6soat-fiap/foodtech/blob/master/Arquitetura.drawio.png)

# Integração Mercado Pago:

Como premissa do projeto foi necessário a implementação da integração da aplicação com o Mercado Pago. Isso foi realizado, criando uma conta de desenvolvedor e configurando as informações para que fosse possível realizar o Checkout do pedido, acionando o serviço para geração do QrCode de pagamento. 

## Link Youtube:
[![YouTube](https://img.shields.io/badge/YouTube-%23FF0000.svg?style=for-the-badge&logo=youtube&logoColor=white)](https://www.youtube.com/watch?v=mpwHyEbF_P0)

## Implementação
Para implantar o projeto, utilizamos o conceito de containers com o Docker como ferramenta de gerenciamento. Nosso projeto usa tanto Dockerfile quanto Docker-compose. Utilizamos uma imagem do Java com Spring e uma imagem do Postgres para rodar o banco de dados localmente e realizar as operações de CRUD da nossa aplicação.

# Como executar o projeto

## Back end
Pré-requisitos: Java 17, JDK 17, Gradle, Postgres.

# Como rodar local:

```bash
# clonar repositório
git clone [https://github.com/grupo27-6soat-fiap/foodtech.git]

# entrar na pasta do projeto food-techchallenge-api
cd .\foodtech\

# executar o projeto
./gradlew bootRun
```
# Como rodar usando Docker Desktop:

## Instalar o Docker Desktop:
### Link para download:
[![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/products/docker-desktop/)


```bash
# clonar repositório
git clone [https://github.com/grupo27-6soat-fiap/foodtech.git]

# entrar na pasta do projeto food-techchallenge-api
cd .\foodtech\

# entrar no terminal e executar o seguinte comando
docker compose up --build
```

# Como rodar usando Kubernetes Local:

### Após instalar o Docker Desktop, ativar no Docker Desktop a opção do Kubernetes:

### Clicar em Settings (Configuração)
![image](https://github.com/user-attachments/assets/74403f54-8ec9-45f2-913d-ffb8b6c6e634)

### Selecionar opção a esquerda do menu chamada "Kubernetes", clicar em "Enable Kubernetes" e depois em "Apply & Start:
![image](https://github.com/user-attachments/assets/15e37689-fea7-4691-8a90-8354fbac258c)

### Após o Kubernetes incializar, seguir os próximos passos:

### Clonar repositório:
git clone [https://github.com/grupo27-6soat-fiap/foodtech.git]

### Entrar na pasta do projeto foodtech:
cd .\foodtech\
### Abrir o PowerShell ou o terminal do computador
### Ordem de execução dos arquivos Yaml:
1 - Executar os arquivos da pasta foodtech/k8s:
 - 1.1 - kubectl apply -f ./k8s/secret-postgress.yml
 - 1.2 - kubectl apply -f ./k8s/statefulset-postgress.yml
 - 1.3 - kubectl apply -f ./k8s/service-postgress.yml
 - 1.4 - kubectl apply -f ./k8s/service-app.yml
 - 1.5 - kubectl apply -f ./k8s/secret-payment.yml
 - 1.6 - kubectl apply -f ./k8s/deployment-app.yml
 - 1.7 - kubectl apply -f ./k8s/hpa.yml
   
2 - Alterar a porta da rota no postman quando o Kubernetes estiver rodando
  - Porta: 30002 (Kubernetes)
  - Porta: 8080 (Local)
![image](https://github.com/user-attachments/assets/95f7c9bb-b7bb-4b20-ad6b-60501e4c3905)



# Como Rodar o Projeto Utilizando o Ambiente AWS

Este guia fornece instruções para configurar o ambiente necessário e executar o projeto Foodtech na AWS, utilizando diversas infraestruturas integradas.

## Repositórios Necessários

1. [foodtech-infra-eks](https://github.com/grupo27-6soat-fiap/foodtech-infra-eks.git)
2. [foodtech-infra-rds](https://github.com/grupo27-6soat-fiap/foodtech-infra-rds.git)
3. [foodtech-infra-cognito](https://github.com/grupo27-6soat-fiap/foodtech-infra-cognito.git)
4. [foodtech-infra-dynamoDB](https://github.com/grupo27-6soat-fiap/foodtech-infra-dynamoDB.git)
5. [foodtech-lambda](https://github.com/grupo27-6soat-fiap/foodtech-lambda.git)
6. [foodtech](https://github.com/grupo27-6soat-fiap/foodtech.git)

## Ordem de Execução dos Workflows Terraform

A seguir estão os passos para executar os workflows Terraform, que provisionam toda a infraestrutura necessária:

### Passo 1 - foodtech-infra-eks
- Executar o workflow para provisionar o cluster EKS que será utilizado pela aplicação.

### Passo 2 - foodtech-infra-dynamoDB
- Executar o workflow para provisionar a base de dados no DynamoDB.

### Passo 3 - foodtech-infra-cognito
- Executar o workflow para provisionar o serviço Cognito que gerenciará a autenticação.

### Passo 4 - foodtech-infra-rds
- Executar o workflow para provisionar o banco de dados RDS.

### Passo 5 - foodtech-lambda (criação do API Gateway incluída)
- Executar o workflow para provisionar a Lambda e configurar o API Gateway.
- **Atenção**: Para o workflow da Lambda, é necessário que as seguintes secrets sejam criadas/atualizadas no GitHub Actions:
  - `CLIENT_ID_COGNITO`: client ID da AppIntegration do Cognito.
  - `PASSWORD_COGNITO`: senha do usuário que foi criado no Cognito.
  - `USERNAME_COGNITO`: nome do usuário criado no Cognito.

### Passo 6 - foodtech
- Executar o workflow para rodar a aplicação no cluster EKS provisionado.
- **Atenção**: Para a aplicação rodar corretamente na AWS, é necessário que as seguintes secrets sejam criadas/atualizadas no GitHub Actions:
  - `RDS_HOSTNAME`: endpoint do banco de dados RDS (ex: `rds-foodtech.c5o24sqc6d2p.us-east-1.rds.amazonaws.com`).
  - `REPO_NAME`: nome do repositório no ECR (ex: `foodtech`).
  - **Atualização no Projeto**:
    - Arquivo `.oodtech\k8s\secret-postgress.yml`:
      - Atualizar a seção `data` com os seguintes campos:
        - `username`: usuário criado no RDS (padrão: `dbadmin`, codificado em Base64 - `ZGJhZG1pbg==`).
        - `password`: senha criada no RDS (a senha padrão é gerada automaticamente, necessário obter no console da AWS: RDS -> Databases -> `rds-foodtech` -> Configuration -> Manage in Secrets Manager).







## Linguagem Ubíqua
 ```bash
# Menu:

- Combos: Uma refeição que está agrupada incluindo bebidas, acompanhamentos e o lanche.
- Bebidas: Refrigerantes, sucos, água com e sem gás, chá e etc.
- Lanche: Opções de sanduíches como: x-banco, x-salada, hamburguer.
- Sobremsa: Opções de comida doces como por exemplo: sorvete, brigadeiro, bolo e etc.
- Acompanhamento: Batata frita, Dadinho de tapioca, Onion rings, nuggets.

# Pedido:

- Balcão: Local onde o cliente pode retirar o pedido.
- Cliente: Responsável por realizar o pedido e retirar a comida com o atendente.
- Ticket: Número do pedido gerado após pagamento.

# Cozinha:

- Montagem do pedido: Momento em que o pedido do cliente começa ser montado pelos cozinheiros, para ser entregue ao cliente.
- Cozinheiros: Responsáveis por cozinhar e realizar a montagem do pedido.
- Controle de pedido: Onde os cozinheiros validam quais são os pedidos pendenes e finalizados.
- Gestão de qualidade: Processo onde é validado a qualidade de cada ingrediente antes de inicializar o dia do restaurante.
- Ingredientes: Tudo aquilo que compõem o pedido a ser montado e entregue para o cliente.

# Pagamento:

- Checkout: Onde o pagamento é realizado e validado para finalizar o pedido.
- Gestão de pagamento: Onde é realizado a validação e controle das formas de pagamentos aceitas para o restaurante.
- Cupom fiscal: Documento que é gerado caso o cliente deseje ter o  documento após realizar o pagamento.
- Formas de pagamento: Dinheiro, pix, cartão de crédito, cartã de débito.
```