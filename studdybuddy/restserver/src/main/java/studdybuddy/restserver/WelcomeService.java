package studdybuddy.restserver;

/**
 * Class for holding welcome text when visiting localhost:8080.
 */
public class WelcomeService {

  private String welcomeText;

  /**
   * Constructor that sets the text.
   */
  public WelcomeService() {
    this.welcomeText = "Welcome to rest API for StuddyBuddy.";
  }

  /**
   * Method for getting the welcome text.
   *
   * @return the welcome text
   */
  public String getWelcomeText() {
    return this.welcomeText;
  }

}
