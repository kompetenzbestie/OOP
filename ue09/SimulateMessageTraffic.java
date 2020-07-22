package u9;

import java.util.Random;

// Diese Klasse verhält sich analog zu unserer auf Blatt 4 programmierten sim_message_traffic()-Funktion.
public class SimulateMessageTraffic {

  // Eine neue queue wird initialisiert.
  private static PriorityQueue<Prio, StringData> testHeap = new PriorityQueue<Prio, StringData>();

  public static void main(String[] args) throws InterruptedException {

    Random rand = new Random();

    while(true) {
      // Eine zufällige Zeitspanne zwischen 1 und 5 Sekunden verstreicht.
      Thread.sleep(rand.nextInt(4000)+1000);

      // Es wird zufällig eines von zwei Szenarien ausgewählt: entweder wird eine neue Nachricht empfangen oder es soll eine beantwortet (gelöscht) werden.
      if(rand.nextBoolean()) {
        // Eine neue Nachricht wird "empfangen" und in die queue eingefügt.
        Prio priority = new Prio(rand.nextInt(100)+1);
        StringData data = new StringData("Message");
        testHeap.enqueue(priority, data);
        System.out.println("> New message with priority " + priority.getValue());
        printHeap();
      } else {
        // Es wird versucht, eine Nachricht zu "beantworten". Ist dies nicht möglich, wird eine EmptyQueueException abgefangen.
        try {
          StringData deq = testHeap.dequeue();
          System.out.println("> Dequeued message with data: " + deq.getValue());
          if(testHeap.getSize()>0) {
            printHeap();
          } else {
            System.out.println("The queue is now empty.");
          }
        } catch (EmptyQueueException e) {
          System.out.println("The queue is empty!\n");
        }
      }
    }
  }

  // Diese Funktion geht alle Elemente der Array durch und gibt für jedes die zugehörige Priorität und Nachricht aus.
  private static void printHeap() {
    System.out.println("Current queue:\n");
    System.out.println("Priority\t Message");
    for (int i=1;i<testHeap.getSize()+1;i++) {
      System.out.println(testHeap.getPriorityAt(i).getValue() + "\t\t\"" + testHeap.getDataAt(i).getValue() + "\"");
    }

    System.out.println();
  }

}
