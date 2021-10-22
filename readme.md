[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2144/gr2144) 

# Building and running of the project

The project uses maven to build and run the project.

To build the project, run mvn install from root-project (studdyBuddy-folder). This will run all the tests and qualitychecks.

The project must run from the ui_module, either with mvn -pl ui javafx:run or first run cd ui and then mvn javafx:run. Know that mvn install must have been ran beforhand on the modules that the ui-module is depending on.

# Group gr2144 repository 
The StuddyBuddy-project consists of a folder called "studdyBuddy" that makes up the coding project. Within this folder there are five folders; "config" , "core", "docs" and "ui", in addition there is a pom.xml-file and a userstory. At root-level there is a .gitpod.Dockerfile-file, -gitpod.yml-file and a README-file.

## config
the config-folder consists of checkstyle and spotbugs that are tools to help to configure the style and check for bugs in our code.

## core-folder
The core-folder consists of src/main/java/core, src/main/java/json and src/test/java/core. In addition to these folders there is a pom.xml-file and a target-folder.

Within src/main/java/core you can find the classes StuddyBuddy.java, StuddyBuddyRegistration.java and StuddyBuddyModel. 

within src/main/java/json you can find the classes StuddyBuddyDeserializer.java, StuddyBuddyPersistence.java, StuddyBuddyRegistrationDeserializer.java, StuddyBuddyRegistrationSerializer.java, StuddyBuddySerializer.java and StuddyModule.java.

Within src/test/java/core you can fint the test-classes StuddyBuddyTest.java and StuddyBuddyRegistrationTest.java which tests the Studdybuddy-class and the StuddyBuddyRegistration-class. 

## docs-folder
The docs folder consists of release1/resources and release1/resources. The images in each folder shows the applications GUI at the moment of release1 and release2 and documents what has been done. 

## ui-folder
The ui-folder consists of src/main and src/test/java/ui. In addition to these folders there is a pom.xml-file.

Within src/main/java/ui you can find the folders java/ui and resources/ui. Within src/main/java/ui you can find the files SuddyBuddyApp.java, StuddyBuddyController.java, and StuddyBuddyRegistrationController.java.

within src/test you can find the classes test-classes StuddyBuddyControllerTest.java and StuddyBuddyRegistrationControllerTest.java which tests the two controllers.

Within src/main/resources you can find the file module-info.java. Within src/main/resources/ui you can fint the text-file Registration.txt, which is the file that StuddyBuddyFileHandler.java writes to. In addtion there is also a fxml.file located within this folder; StuddyBuddy.fxml, which creates the GUI.

## userstory
The userstory have information that describes the application and what it is meant to do. It contains screenshots of the application´s GUI which makes it easier to understand the function of the application. In addition there is a user story for the current functionality of the application StuddyBuddy.

## work documentation 
We started the work process by getting an overview over what had to be done. Then we made issues to every task and assigned them between the group members. Even though only one person had responsibility for a task we usually sat together and helped each other. In some cases we also worked together in pairs with the programming. Each issue was handled on a separate branch and was then merged to the master-branch when the issue was finished. Before merging we always made sure to get the work approved by another group member. 

The quality of the code has been the highest priority throughout the work process. Therefore we chose a simple design to begin with. For the first deliverable we chose to only make a registration function. We also made sure that everything was working properly before implementing more functions. 

We used a number of programs to check the code quality. We used “Spotbugs” to look for bugs in the code. To make sure that the style of the code was done right and continuous we used the program “checkstyle”, which we found very useful.

We made a test for every class to make sure that code was working correctly. We used “Jacoco” to help us keep track of the test coverage.  
