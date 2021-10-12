package ui;

import core.StuddyBuddy;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

public class StuddyBuddyFileHandler implements StuddyBuddyFileHandlerInterface {

    private static final String REGISTRATION_STRING = "/workspace/gr2144/studdyBuddy/ui/src/main/resources/ui/Registrations.txt"; 

    //saves registration to file
    @Override
    public void saveRegistrationToFile(StuddyBuddy studdybuddy) throws FileNotFoundException {
        String name = studdybuddy.getName();
        String room = studdybuddy.getRoom();
        String course = studdybuddy.getCourse();
        String startTime = studdybuddy.getStartTime();
        String endTime = studdybuddy.getEndTime();

        String registration = name + "," + room + "," + course + "," + startTime + "," + endTime;

        try(PrintWriter writer = new PrintWriter(new FileWriter(REGISTRATION_STRING, true))) { 
            writer.println(registration);

        } catch (IOException e) {  
            e.getStackTrace();
        }
    }

    //read registration from file and turn it into string

    @Override
    public String readRegistrationFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(REGISTRATION_STRING));
        StringBuilder registration = new StringBuilder();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if(!scanner.hasNextLine()) {
                String[] registrations = line.split(",");

                registration.append("Name: " + registrations[0]);
                registration.append("\n");
                registration.append("Room: " + registrations[1]);
                registration.append("\n");
                registration.append("Course: " + registrations[2]);
                registration.append("\n");
                registration.append("Start time: " + registrations[3]);
                registration.append("\n");
                registration.append("End time: " + registrations[4]);
            }
        }
        scanner.close();
        return registration.toString();
    }
}