package ui;

import core.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

public class StuddyBuddyFileHandler implements StuddyBuddyFileHandlerInterface {

    private static final String REGISTRATION_STRING = "/workspace/gr2144/studdyBuddy/ui/src/main/resources/ui/Registrations.txt"; 

    //read registration from file and turn it into string

    @Override
    public String readRegistrationFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(REGISTRATION_STRING));
        StringBuilder registration = new StringBuilder();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (!scanner.hasNextLine()) {
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

    @Override
    public void saveRegistrationToFile(StuddyBuddyRegistration registration) throws FileNotFoundException {
        String name = registration.getUsername();
        String room = registration.getRoom();
        String course = registration.getCourse();
        String startTime = registration.getStartTime();
        String endTime = registration.getEndTime();

        String registrationAsString = name + "," + room + "," + course + "," + startTime + "," + endTime;

        try (PrintWriter writer = new PrintWriter(new FileWriter(REGISTRATION_STRING, true))) {
            writer.println(registrationAsString);

        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}