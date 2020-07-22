package u9;

public class TestArrayQueue {

  static final int SIZE = 5;

  public static void main(String[] args) {

    SBQueue<Character> testQueue = new SBQueue<Character>(new Character[SIZE]);

    testQueue.enqueue('A');
    System.out.println(testQueue.toString());
    testQueue.enqueue('B');
    System.out.println(testQueue.toString());
    testQueue.enqueue('C');
    System.out.println(testQueue.toString());

    try {
      testQueue.dequeue();
      System.out.println(testQueue.toString());
    } catch (EmptyQueueException e) {
      System.out.println("The queue is empty!");
    }

    testQueue.enqueue('D');
    System.out.println(testQueue.toString());

    testQueue.enqueue('E');
    System.out.println(testQueue.toString());

    try {
      testQueue.dequeue();
      System.out.println(testQueue.toString());
    } catch (EmptyQueueException e) {
      System.out.println("The queue is empty!");
    }

  }

}
