# Finance Test API

- This is a test project, provides 2 api below:
1. /acount-balance   
2. /transfer-money

## how to build and run
1. setup JDK 11 , maven 3.3 , Tomcat 9.0.22 or above on local
2. modify value of "server_url" to local Tomcat url in {project_location}/src/main/resources/application.properties if neccessary
2. run command "mvn clean install" to generate war under /{project_location}/target
3. copy finance-test-XX.war to {Tomcat_location}/webapps
4. test api by browser or postman with raml specification below
https://github.com/universe48/finance-test/blob/master/src/main/api/finace-test.raml

## 3rd party library
###Nitrite Database
an open source nosql embedded document store written in Java
https://github.com/dizitart/nitrite-database