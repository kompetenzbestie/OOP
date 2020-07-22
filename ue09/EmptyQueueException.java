package u9;

public class EmptyQueueException extends Exception {

  // Da nicht angegeben wurde, dass EmptyQueueException irgendwelche Argumente nehmen soll, wird hier nur Exception mit einer Botschaft aufgerufen.
  public EmptyQueueException() {
    super("The queue is empty!");
  }

}
