# Tilbakemelding til innlevering 2

# Bygging

- the project builds and runs  but one needs to skip spotbug checks
- lots of checkstyle warnings 
- spotbugs errors
- exceptions during tests 
```
Running core.StuddyBuddyRegistrationTest
java.time.format.DateTimeParseException: Text '8:00' could not be parsed at index 0 ...
....

```

-  the test coverage is sufficient only for core

## Brukerhistorier

- the user stories are no sufficient to produce even a MVP

## Dokumentasjon

- comments are missing or they are in the wrong format - it is recommended to use javadoc for methods, classes etc
- the application is very limited  - the user can only add data but there are no means to consult the saved data
- release 1  is incomplete
- release 2 is missing
- required plantuml diagram is missing
- descriptions of important decisions are missing (workflow, work practices etc)
- userstory.md is duplicated
- why do you need an empty  .gitignore in docs/release2 ? the convention is to use .gitkeep if you want to keep an empty folder in the repository

## Misc

- the usefulness of the application is no apparent from the implementation - we can only add data for a user  regarding rooms and courses
- once we "log in" there is no way out except closing the app
- UI does not allow exiting the application in a normal expected way - we need to use CTRL+C
- cannot resize the window - so one needs to use whatver size of window you have set, which can be either too big or too small for the used screen

## Kodegjennomgang

- disabled code should not be in the release
- rooms and courses should probably be part of the model
- there should be some model element that manages the collection of registrations, making matches etc
- rooms normaly have a capacity
 

### json

- what is the use of havinng all these json classes when the persistence seems to be performed by StuddyBuddyFileHandler

### core

- using raw types `ArrayList<>()`
- some validation methods do not make much sense
  - checkNotNull - does this need to exist ?
  - checkName - is that the best way to validate a name ? what about names with a dash or an apostrophe ?
  - similar validation issues are for the course and room - one can add still invalid or meaningless names  like "  " or  "-" but cannot add some real valid names 
 

### ui

- starttime and endtime should be entered with specific widgets - why use a free text input field that allows any input and thus gives more opportunities for failure
- why is ui layer having a pesistence related class ?
- some validation errors end in unhandled exceptions 

```

java.time.format.DateTimeParseException: Text '' could not be parsed at index 0
        at java.base/java.time.format.DateTimeFormatter.parseResolved0(DateTimeFormatter.java:2052)
        at java.base/java.time.format.DateTimeFormatter.parse(DateTimeFormatter.java:1954)

```
