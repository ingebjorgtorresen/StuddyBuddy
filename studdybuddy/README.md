<p align="center">
<img src="https://imgur.com/ye4MIDl.png" width="600"/>
</p>

## Description of the application
The StuddyBuddy application tries to connect students by having a registration platform that gathers information about when and where students/users are working. 

When opening the application the user is taken to a welcome page. Here the user can choose to either log in or register a new user. The first time the user opens the application he or she have to register a user, `StuddyBuddy`, with a name and password on a separte registration page; `"RegisterUser"`. This page is accessable by clicking on `"Register new buddy"` on the welcome page. The password must be written twice when registration. This is to ensure that the password is correct. If the user already is a buddy, he or she can enter the application by clicking on `"Login"` on the welcome page. When the user has either logged in or registerd a new user, he or she will be taken to a forum page; `"StuddyBuddies"`. Here the user is able to register a work/study session, by clicking on `"Add registration"`. The user is then taken to a registration page; `"Registration"`, where he or she can create a new `StuddyBuddyRegistration`. The user have to select a date by cliking on a calander. The user have to fill in which room they will be working in, which course they are working on, and a start and end time for the session. After having filled out all the different fields, the user will have a successfull registration that will be saved to a server, and the user is taken back to the forum page.Here the user will see his or her own registration, in addition to registrations from other users, also know as `StuddyBuddies`. The buddies are sorted out by when they registered a new StuddyBuddyRegistration. The newest registration is located at the bottonm of the forum.

The user is able to log out. This is done through the forum-page, where the user is able to click on a button marked "Log out". The user is then taken back to the login-page.

<br/>

## The user interface

<br/>

<p align="center">
<img src="https://imgur.com/Vs9s0fB.png" width="300"/>
</p>

<p style="text-align: center;">
The login page
</p>

<br/>

<p align="center">
<img align="center" src="https://imgur.com/rDTCfgB.png" width="300"/>
</p>

<p style="text-align: center;">
The registration page before clicking on register
</p>

<br/>

<p align="center">
<img src="https://imgur.com/ks90Ubz.png" width="300"/>
</p>

<p style="text-align: center;">
The registration page after clicking on register, when the registration was successful. The information is saved to a file. The registered information is read from the file and given as a feedback to the user
</p>

<br/>
<p align="center">
<img src="https://imgur.com/sabwPWX.png" width="300"/>
</p>

<p style="text-align: center;">
The registration page after clicking on register, when the input was invalid
</p>


<br/>


## User stories
The following user stories covers the functionality of the application. 

<br/>

### <u> User story 1 - Registrating schoolwork </u>
As a student I would like to be updated and informed when fellow students are on campus and also what they are working on.

The user is in need of knowing when fellow students, specifically their friends, are on campus, at the library or other school-related places. It is practical to be able to register name, course, time period and room, in order to enhance collaboration amongst students. The registration should just consist of a simple text, but it is desirable that every field is used, in order to make the registration as informative and organized as possible. 

#### **Important to be able to see:**
- fields for registration
- what you need to register in each field
- “notification” if an input is invalid in a certain field
- that the registration was successful

#### **Important to be able to do:**
- write text in registration-field
- have several chances to write a valid input in all fields

<br/>

### <u> User story 2 - Forum with overview of registrations </u>
As both a private person and student I wish to have an overview of my own attendance at school as well as what I am working on. This to make it easier to know how much time I spend on schoolwork and with which course. 

The user is in need of having a personal page/profile where he/she can register their attendance at school, with information about date, time, room and course. 

#### **Important to be able to see:**
- The personal page
- Log in
- Username/name
- Fields for registration
- What is registered and that it is complete

#### **Important to be able to do:**
- Be able to log in
- Be transferred from log in/profile to page for registration
- Write registration

<br/>

## Progress in the project

### <u> Overview </u>
The project has been broken down into several different issues, that are connected to the different milestones/deliverables. The issues are sorted out and presented below, by which part of the project they belong to. 


#### <u> Closed issues </u>
Since we only have the Maintainer-role on GitLab we are not able to delete issues. We solved this by closing issues. During the project there were several issues which turned out to not be necessary and was therfore closed. 

This was the case for issue #1, #15, #17, #18, #20, #33, #44, #53, #54, #55 and #58.

<br/>

### **Set up project and making a registration**

#### <u> **Milesstone 1:** First deliverable </u>

Issues: #12, #13, #14, #19, #28 <!--hvor skal disse??-->

**Set up project**

Issues: #3

The project is set up by cloning down the example project from GitLab. It is configurated such that it can build with Maven. To make sure that the application can be opened an run in GitPod, the project is also configurated for GitLab.

<br/>

**Making a registration**

Issues: #6, #7, #8, #9, #10, #11, #16, #21, #22, #23, #24, #25, #26, #27, #31

The project has a three-layer architecture, consisting if domain logic, user interface (JavaFX-GUI) and peristence. At this stage in the project the application consists of a registration-page, which lets the user register a work-/study-session with several tries to write correct input (user story 1).

<br/>

**Documentation**

Issues: #2,#4, #29, #32 

The current state of the application is documentet in two different README-files. These are located in a seperate release folder ([release1](studdybuddy/docs/release1))

<br/>

**Testing and code coverage**

Issues: #5, #30

We have tested the source code and implemented JaCoCo, to check how well the code is being tested. 

<br/>

### **Implementing login-funtionality and persistence using JSON**

#### <u> **Milestone 2:** Seccond deliverable </u>

Issues: #42, #46, #67 <!--hvor skal disse??-->

**Updating current code and further development of the application**

Issues: #34, #36, #43, #45, #47, #48, #51, #52, #56, #59

The projects still has a three-layer architectur, but for this deliverable the persistance is now handled with JSON. The application is in addiotionally updated to consist of two different windows; a login site and a registration site. There has not been any further updates to the application, since the focus also was on doing some maintanance from the first deliverable.

<br/>

**Code quality**

Issues: #35, #37, #50, #65

We have created test for all the modules, meaning that both core and ui are being tested. Both Spotbugs and Checkstyle has been implemented to help with 

<br/>

**Updating documentation and create PlantUML**

Issues: #38, #39, #40, #41, #49, #60, #61, #62, #64, #66

The readme-files have been updated, such that that current state of the application is documentet. The documentation is placed in [release2](studdybuddy/docs/release2). From the last release, we have now also documented the way we are working on the project and have added a new user story (user story 2).

<br/>

**Configurationg GitPod-label**

Issues: #63

We configurated a GitPod-label, clicking on it will lead to opening GitPod in a new window. 

<br/>

### **Implementing forum and REST-api**

#### <u> **Milestone 3:** Third/Last deliverable </u>

issues: #73, #91, #92, # 96, #103 <!--hvor skal disse??-->

**Architecture**

Issues: #57, #83, #84, #85, #86, #87, #110

The project now, in addition to thee three-layer architecture, consist of a REST-api surrounding the source code and offers a web-server. The user interface also uses the REST.api

<br/>

**Update funtionality and new tests**

<u> Updated funtionality </u>

Issues: #68, #69, #70, #71, #72, #75, #76, #77, #78, #80, #81, #82, #93, #95, #97, #98, #100, #107, #109, #112

Since the last deliverable, we have expanded the application. Now StuddyBuddy consists of a login-page, where you also can register a new user, we have also implementet using a password to login and register. Once the user has logged in, he or she is taken to a forum-page. Here alle registrations are displayed and the user is able to scroll through to see his or her own registration, in addition to registration of other users, know as studdyBuddies. In order to add a new registration, the user have to click on a button in the forum-page, that takes the user to the registration-page. The user still have to fill in iinformation about room, course, start time and end time, but now also have to enter a date. Choosing a date is done by clicking on a calander, on the registration page.

<br/>

<u> New tests </u>

Issues: #74, #79, #94, #99, #101, #102, #104, #105, #108, #111 ,#113

Test for the new classes have been added. <!-- skrive noe om hvordan vi tester? skulle vi ikke hente fra server, eller noe?-->

<br/>

**Documentation**

Issues: #88, #89, #90, 

The documentation has been updatet and placed in [release3](studdybuddy/docs/release3). We have created a package-diagram for the application, a class-diagram for the most important parts of the application <!-- hvilke deler blir dette??-->, and sequence-diagram that shows the connection between the user interface and what happens within the system, including a call on REST-api <!-- usikker på om dette blir riktig måte å formulerer det på, må også neven hvilket viktige brukstilfelle vi tar for oss -->