package u9;

public class TestArrayQueue {

  public static void main(String[] args) {

    SBQueue<Character>[] testQueue = new SBQueue[32]];

    testQueue.enqueue('H');
    testQueue.enqueue('E');
    testQueue.enqueue('L');
    testQueue.enqueue('L');
    testQueue.enqueue('O');
    testQueue.enqueue('!');

    System.out.println(testQueue.toString());

  }

}
