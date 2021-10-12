#REST Assured Demo Task

1. Create a test automation framework skeleton.
2. To perform the validations for the comments for the post made by a
   specific user

##OVERVIEW

The project is Java based, implementing:
- Maven
- Rest Assured
- jUnit
- Allure Reports
- CircleCi 

##Running the test suites

The tests can be run directly by your IDE or by command line. If you run mvn test `-Denv=qaEnv`

##Generating the test report

This project uses Allure Report to automatically generate the test report.
Reports are automatically generated in a folder `"../target/allure-report"`.
To generated reports run test with tha command `mvn test -Denv=qaEnv allure:report` the open the exact report file
`"../target/allure-report/index.html"`.

##Pipeline
This project uses CircleCi to run the all the tests. You can find it at https://app.circleci.com/pipelines/github/tuxxomup/RestAssuredTask




