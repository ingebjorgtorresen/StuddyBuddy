package studdybuddy.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.Reader;
import com.fasterxml.jackson.databind.ObjectMapper;
import studdybuddy.core.StuddyBuddies;

/**
 * Wrapper class for JSON serialization, to avoid direct compile dependecies on Jackson for other
 * modules
 */

public class StuddyBuddiesPersistence {

  private ObjectMapper mapper;
  private Path savePath = null;

  /**
   * Constructor that sets mapper
   */
  public StuddyBuddiesPersistence() {
    mapper = createObjectMapper();
  }

  /**
   * Method for creating object mapper
   *
   * @return ObjectMapper
   */
  public static ObjectMapper createObjectMapper() {
    return new ObjectMapper().registerModule(new StuddyModule());
  }

  /**
   * Method that uses mapper to write studdyBuddies objects
   *
   * @param buddies
   * @param writer
   * @throws IOException
   */
  public void writeStuddyBuddies(StuddyBuddies buddies, Writer writer) throws IOException {
    System.out.println("Skriver dette buddies objektet");
    System.out.println(buddies);
    mapper.writerWithDefaultPrettyPrinter().writeValue(writer, buddies);
  }


  /**
   * Method that uses mapper to read studdyBuddies objects
   *
   * @param reader
   * @return a StuddyBuddies
   * @throws IOException
   */
  public StuddyBuddies readStuddyBuddies(Reader reader) throws IOException {
    return mapper.readValue(reader, StuddyBuddies.class);
  }

  /**
   * Method for setting SavePath
   * 
   * @param saveFile
   */
  public void setSaveFilePath(String saveFile) {
    this.savePath = Paths.get(System.getProperty("user.home"), saveFile);
  }

  public Path getSavePath() {
    return this.savePath;
  }

  /**
   * Method that loads a StuddyBuddies object from saved file in user.home folder.
   * 
   * @return the loaded StuddyBuddies
   * @throws IOException
   * @throws IllegalStateException
   */
  public StuddyBuddies loadStuddyBuddies() throws IOException, IllegalStateException {
    if (savePath == null) {
      throw new IllegalStateException("file path is not set.");
    }
    try (Reader reader = new FileReader(savePath.toFile(), StandardCharsets.UTF_8)) {
      return readStuddyBuddies(reader);
    }
  }

  /**
   * Method that saves a StuddyBuddies object to the savePath in the user.home folder.
   * 
   * @param studdyBuddies the StuddyBuddies to save
   * @throws IOException
   * @throws IllegalStateException
   */
  public void saveStuddyBuddies(StuddyBuddies studdyBuddies) throws IOException, IllegalStateException {
    System.out.println("Savepath: " + savePath);
    if (savePath == null) {
      throw new IllegalStateException("file path is not set");
    }
    try (Writer writer = new FileWriter(savePath.toFile(), StandardCharsets.UTF_8)) {
      writeStuddyBuddies(studdyBuddies, writer);
    }
  }
}
