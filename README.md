[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2144/gr2144)
 
# StuddyBuddy
This project consists of three modules;
 
* **[core](studdybuddy/core/src/main/java/studdybuddy/core)**
* **[ui](studdybuddy/ui/src/main/java/studdybuddy/ui)**
* **[restserver](studdybuddy/restserver/src/main/java/studdybuddy/restserver)**
 
The core module contains all logic that the application is built on, while the ui module contains the user interface, the layer that is visable to the user, and the restserver module connects the application to a server.
 
## Building and running of the project
The project uses **maven** to build and run the project.
 
To build the project, run mvn install from root project ([studdybuddy-folder](studdybuddy)). This will run all the tests and qualitychecks.
 
The project must run from the ui_module, either with 'mvn -pl ui javafx:run' or first run 'cd ui' and then 'mvn javafx:run'. Know that mvn install must have been ran beforhand on the modules that the ui module is depending on.
 
Running 'mvn spring-boot:run -pl restserver' or 'cd restserver && mvn spring-boot:run' will start up the server. From here you can run the application as described above.
 
It is possible to run the application using jlink and jpackage, but only locally on an IDE, for example VScode. This is because we were not able to install fakeroot in GitPod, which is needed for making the zip file that contains the application. In order to run the application using jlink locally, you have to follow the steps below:
 
- run server with spring boot in restserver (as described above)
- split the terminal and run the command 'mvn javafx:jlink jpackage:jpackage'
- go into the ui folder in your IDE and then into the target folder
- you should now have a zip file called "studdybuddyfx.zip" within the target folder
- right click on this file and select 'Reveal in Finder' (your file library)
- go into the folder called bin, located in studdybuddyfx folder
- lastly choose to run studdybuddyfx in terminal
 
## Group gr2144 repository
The StuddyBuddy project consists of a folder called "studdybuddy" that makes up the coding project. This folder consists of the folders/modules; "config" , "core", "docs", "restserver", "ui" and "UML-diagrams", in addition there is a pom.xml-file and a userstory. At root-level there is a .gitpod.Dockerfile file, -gitpod.yml file and a README file.
 
### config folder
The config folder (studdybuddy/config) consists of the folders:
- [checkstyle](https://checkstyle.sourceforge.io) (**maven-checkstyle-plugin**) which is a tool to help  configure the quility of the code, making sure it adheres to a coding standard
- [spotbugs](https://spotbugs.github.io) (**maven-spotbugs-plugin**) which is a tool that uses static analysis to look for bugs in our code
 
### core module
The [core module](studdybuddy/core) consists of
- **[src/main/java/studdybuddy/core](studdybuddy/core/src/main/java/studdybuddy/core)**: the source code for the application
- **[src/main/java/studdybuddy/dataaccess](studdybuddy/core/src/main/java/studdybuddy/dataaccess)**: code handling the connection to a server <!--vet egt ikke helt hva som er riktig her-->
- **[src/main/java/studdybuddy/json](studdybuddy/core/src/main/java/studdybuddy/json)**: writing to and reading from a json-file
- **[src/test/java/studdybuddy/core](studdybuddy/core/src/test/java/studdybuddy/core)**: testing of the source code
- **[src/test/java/studdybuddy/dataaccess](studdybuddy/core/src/test/java/studdybuddy/dataaccess)**: testing of the connection to a server and a moch server
- **[src/test/java/studdybuddy/json](studdybuddy/core/src/test/java/studdybuddy/json)**: testing of writing to and reading from a json-file
- **[pom.xml file](studdybuddy/core/pom.xml)**
- **[target folder](studdybuddy/core/target)**
 
Within [src/main/java/studdybuddy/core](studdybuddy/core/src/main/java/studdybuddy/core) you can find the classes
- StuddyBuddy.java
- StuddyBuddyRegistration.java
- StuddyBuddyModel.java
- StuddyBuddyValidation.java
 
Within [src/main/java/studdybuddy/dataaccess](studdybuddy/core/src/main/java/studdybuddy/dataaccess) you can find the classes
- DataAccess.java
- DirectDataAccess.java
- RemoteDataAccess.java
 
Within [src/main/java/studdybuddy/json](studdybuddy/core/src/main/java/studdybuddy/json) you can find the classes
- StuddyBuddiesDeserializer.java
- StuddyBuddiesPersistence.java
- StuddyBuddiesSerializer.java
- StuddyBuddyDeserializer.java
- StuddyBuddyRegistrationDeserializer.java
- StuddyBuddyRegistrationSerializer.java
- StuddyBuddySerializer.java
- StuddyModule.java
 
Within [src/test/java/studdybuddy/core](studdybuddy/core/src/test/java/studdybuddy/core) you can fint the test classes
- StuddyBuddiesTest.java
- StuddyBuddyRegistrationTest.java
- StuddyBuddyTest.java
- StuddyBuddyValidationTest.java
 
 
Within [src/test/java/studdybuddy/dataaccess](studdybuddy/core/src/test/java/studdybuddy/dataaccess) you can find the test classes
- RemoteDataAccessTest.java
 
Within [src/test/java/studdybuddy/json](studdybuddy/core/src/test/java/studdybuddy/json) you can find the test class
- StuddyBuddyPersistenceTest.java
 
### restserver module
The [restserver module](studdybuddy/restserver) consists of
- **[src/main/java/studdybuddy/restserver](studdybuddy/restserver/src/main/java/studdybuddy/restserver)**: contains the folder restserver and the file module-info.java
- **[src/main/resources/studdybuddy/restserver](studdybuddy/restserver/src/main/resources/studdybuddy/restserver)**: contains json file with initial StuddyBuddies used when loading the server
- **[src/test/java/studdybuddy/restserver](studdybuddy/restserver/src/test/java/studdybuddy/restserver)**: testing of the restserver
- **[pom.xml file](studdybuddy/restserver/pom.xml)**
- **[target folder](studdybuddy/restserver/target)**
 
Within [src/main/java/studdybuddy/restserver](studdybuddy/restserver/src/main/java/studdybuddy/restserver) you can find the files
- StuddiesBuddyApllication.java
- StuddyBuddiesController.java
- StuddyBuddiesService.java
 
Within [src/test/java/studdybuddy](studdybuddy/restserver/src/test/java/studdybuddy) you can find the files
- StuddyBuddiesRestControllerTest.java
- StuddyBuddyApplicationTest.java
 
### ui module
The [ui module](studdybuddy/ui) consists of
- **[src/main/java/studdybuddy](studdybuddy/ui/src/main/java/studdybuddy)**: contains all the controllers and the app-class, which handles the users interaction with the user interface. The folder also contains the file module-info.java
- **[src/main/resources/studdybuddy/ui](studdybuddy/ui/src/main/resources/studdybuddy/ui)**: contains fxml-files that creates the user interface and that are connected to the controllers in src/main/java
- **[src/test/java/studdybuddy/ui](studdybuddy/ui/src/test/java/studdybuddy/ui)**: contains test for the controllers
- **[src/test/studdybuddy/resources/studdybuddy/ui](studdybuddy/ui/src/test/studdybuddy/resources/studdybuddy/ui)** : contains fxml files that are used in the ui-tests
- **[target folder](studdybuddy/ui/target)**
- **[a pom.xml file](studdybuddy/ui/pom.xml)**
 
Within src/main/java/studdybuddy you can find the folder [ui](studdybuddy/ui/src/main/java/studdybuddy/ui), which contains the files
- AppController.java
- LoginStuddyBuddyController.java
- RegisterStuddyBuddyController.java
- StuddyBuddiesController.java
- SuddyBuddyApp.java
- StuddyBuddyRegistrationController.java
- StuddyBuddyRemoteApp.java

The folder also contains the sequence diagram as a puml and png.
 
Within [src/main/resources/studdybuddy/ui](studdybuddy/ui/src/main/resources/studdybuddy/ui) you can find the files
- directBuddies.json
- LoginStuddyBuddy.fxml
- RegisterStuddyBuddy.fxml
- StuddyBuddies.fxml
- StuddyBuddy.fxml
- StuddyBuddyRegistration.fxml
 
The folder also contains the images StuddyBuddy1.png (the applications logo) and usericon.png.
 
Within [src/test/java/studdybuddy/ui](studdybuddy/ui/src/test/java/studdybuddy/ui) you can find the test classes that test the controllers and the app class:
- AppControllerTest.java
- LoginStuddyBuddyControllerTest.java
- RegisterStuddyBuddyControllerTest.java
- StuddyBuddiesControllerTest.java
- StuddyBuddyRegistrationControllerTest.java
 
Within [src/test/studdybuddy/resources](studdybuddy/ui/src/test/studdybuddy/resources) you can find the fxml files that are used in the tests
- LoginStuddyBuddy_test.fxml
- RegisterStuddyBuddy_test.fxml
- StuddyBuddies_test.fxml
- StuddyBuddy_test.fxml
- StuddyBuddyRegistration_test.fxml
 
The folder also contains the images StuddyBuddy1.png (the applications logo) and usericon.png.
 
## UML-diagrams
The UML-diagrams folder contains classdiagrams, a sequence diagram and a packagediagram.
 
## README
This README file contains a user story with information that describes the application and what it is meant to do. It contains screenshots of the application´s GUI which makes it easier to understand the function of the application. In addition there is a user story for the current functionality of the application "StuddyBuddy". There is also an overview all the issues. This file is located in **[studdybuddy/README.md](studdybuddy/README.md)**
 
## Work documentation
We started the work process by getting an overview over what had to be done. Then we made issues to every task and assigned them between the group members. These issues are described in more detail in [README.md](studdybuddy/README.md). Even though only one person had responsibility for a task we usually sat together and helped each other. In some cases we also worked together in pairs with the programming. Each issue was handled on a separate branch and was then merged to the master branch when the issue was finished. We wrote descripted commitment messages with issue number. Usually on format "(#issue number) description". The seperate branches were named with the issues name. Before merging we always made sure to get the work approved by another group member. Since we sat together most of the time we gave eachother oral feedback before merging. As we gained more and more experience we became better at writing commit messages and documenting our work.
 
The quality of the code has been the highest priority throughout the work process. Therefore we chose a simple design to begin with. For the first release we chose to only make a registration function. We also made sure that everything was working properly before implementing more functionality. In release 2 we implemented persistence using json, and for release 3 we connected the application to a server using spring boot. In both releases we also implemented more functionality and expanded the application.
 
We used a number of programs to check the code quality. We used “Spotbugs” to look for bugs in the code. To make sure that the style of the code was done right and continuous we used the program “checkstyle”, which we found very useful.
 
We made tests for all the vital classes to make sure that code was working correctly. We used “Jacoco” to help us keep track of the test coverage. We were able to get the test coverage displayed by running the command 'mvn verify' within a module. Then a file called "index.html" appeared in target/site/jacoco.
 
