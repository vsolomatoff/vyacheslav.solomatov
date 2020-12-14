public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        System.out.println(first.getState());
        first.start();
        while (first.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState());
        }
        System.out.println(first.getState());

        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        System.out.println(second.getState());
        second.start();
        while (second.getState() != Thread.State.TERMINATED) {
            System.out.println(second.getState());
        }
        System.out.println(second.getState());

        System.out.println("Work completed");
    }
}
