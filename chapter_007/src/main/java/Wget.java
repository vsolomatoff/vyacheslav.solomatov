public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    for (int index=0; index<=100; index++) {
                        try {
                            System.out.print("\rLoading : " + index  + "%");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                }
        );
        thread.start();
        System.out.println("Main");
    }
}
