# Repositório de Testes
Este repositório Todos os cenários e Exercícios solicitados

# 💻Atualmente o projeto utiliza as seguintes Tecnologias E Frameworks💻

Java 11 </p>
Maven </p>
Junit 4 </p>
JunitJupiterAPI </p>
Rest Assured </p>
Extent Reports </p>
Postman </p>
Selenium Webdriver </p>

# ⚠ Você irá precisa instalar previamente algumas tecnologias, sendo elas ⚠

Java 11 </p>
Maven </p>
Postman </p>
Google Chrome

Executando os testes
Os Testes podem ser executados pela IDEA de sua escolha, desde que o projeto seja aberto com o gerenciador Maven, nas classes:
Cucumber Runner: "src\test\java\utils\RunnerTest.java"
Rest Assured: "src\test\java\restassured_automation"
OBS: Caso queira executar via Linha de comando execute na pasta raiz:
"mvn -clean test"

# 🎋 Explicando a Árvore do código 🎋

- Testes exploratórios com Postman </p>
No diretório "..\PostmanAPI" voce vai encontrar a coleção feita para este framework, ela serve para testes exploratórios, que não necessariamente são escritos em código, porém podem ser realizados pelas chamadas das APIS.

 - Testes de API </p>
Os testes de integração foram escritos usando o framework Rest Assured e estão localizados no diretório "src\test\java\restassured_automation". Esses testes validam a integração entre diferentes componentes da aplicação que foi disposta, seus métodos e regras de negócio e corpo.

 - Testes de interface </p>
Os testes de interface foram escritos usando o framework Selenium Webdriver e estão localizados no diretório "src/test/java". Esses testes validam a funcionalidade da interface do usuário, simulando interações com os elementos da página, como clicar em botões ou preencher formulários.
OBS: os testes com Selenium foram executados em par com o framework Cucumber,  com o objetivo de melhorar a leitura e entendimento dos cenários

- Relatório de testes </p>
O Extent Reports é utilizado para gerar um relatório completo dos resultados dos testes. Esse relatório chamado de "API.html" e é gerado automaticamente após a execução dos testes e é armazenado no diretório "target/Report"

# Observações Referentes ao exercício:
Para consultar as Evidências dos testes executados, procure pelo diretório: "C:..\target\Report"
Para consultaer os cenários de automação Web, procure pelo direório: "src\test\resources\features\landing_page_cucumber.feature"
Para consultaer os cenários de automação da API, procure pelo direório: "src\test\java\restassured_automation"


