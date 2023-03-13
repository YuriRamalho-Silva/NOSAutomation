
# Test Repository
This repository contains all requested scenarios and Exercises!

# 💻Currently the project uses the following Technologies And Frameworks💻

Java 11 </p>
Maven </p>
Junit 4 </p>
JunitJupiterAPI </p>
Rest Assured </p>
Extended Reports </p>
Postman </p>
Selenium Webdriver </p>

# ⚠ You will need to previously install some technologies, namely ⚠

Java 11 </p>
Maven </p>
Postman </p>

# 🚀 Running the tests 🚀
The Tests can be executed by the IDEA of your choice, as long as the project is opened with the Maven manager, in the classes: </p>
Cucumber Runner: "src\test\java\utils\RunnerTest.java" </p>
Rest Assured: "src\test\java\restassured_automation" </p>
NOTE: If you want to run it via the Command Line, run it in the root folder: </p>
"mvn -clean test" </p>

# 🎋 Explaining the Code Tree 🎋

- Exploratory tests with Postman </p>
In the directory "..\PostmanAPI" you will find the collection made for this framework, it serves for exploratory tests, which are not necessarily written in code, but can be performed by APIS calls.

 - API Tests </p>
The integration tests were written using the Rest Assured framework and are located in the "src\test\java\restassured_automation" directory. These tests validate the integration between different components of the application that was laid out, its methods and business and body rules.

 - Interface Tests </p>
Interface tests were written using the Selenium Webdriver framework and are located in the "src/test/java" directory. These tests validate user interface functionality by simulating interactions with page elements, such as clicking buttons or filling out forms.
OBS: the tests with Selenium were executed in pair with the Cucumber framework, with the objective of improving the reading and understanding of the scenarios

- Test report </p>
Extent Reports is used to generate a complete report of test results. This report is called "API.html" and is automatically generated after running the tests and is stored in the "target/Report" directory

# 🧾 Observations regarding the exercise 🧾
To consult the Evidences of the executed tests, look for the directory: "C:..\target\Report" </p>
To consult the web automation scenarios, search for the directory: "src\test\resources\features\landing_page_cucumber.feature" </p>
To query the API automation scenarios, look for the directory: "src\test\java\restassured_automation" </p>

# Thank you for your time and oportunity, please if you have some advice for the project contribute!
