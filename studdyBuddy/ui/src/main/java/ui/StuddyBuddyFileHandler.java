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

    @Override
    public String readRegistrationFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(REGISTRATION_STRING));

        for(int x = 0; x < 1; x++) {
            if(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] registrations = line.split(",");

                StringBuilder registration = new StringBuilder();
                registration.append("Your registration is completed.");
                registration.append("/n");
                registration.append("Name: " + registrations[0]);
                registration.append("/n");
                registration.append("Room: " + registrations[1]);
                registration.append("/n");
                registration.append("Course: " + registrations[2]);
                registration.append("/n");
                registration.append("Start time: " + registrations[3]);
                registration.append("/n");
                registration.append("End time: " + registrations[4]);

                return registration.toString();
            }
            scanner.close();
        }
        return "Could not register.";
    }
}