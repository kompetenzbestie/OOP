package u8;

public class UndrinkableException extends Exception {

  // UndrinkableException() nimmt keine Argumente, sondern gibt nur eine Warnmeldung aus.
  public UndrinkableException() {
    super("You can't drink this liquid! Or at least you shouldn't.");
  }
}
