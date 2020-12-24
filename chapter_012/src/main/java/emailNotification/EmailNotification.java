package emailNotification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    // Getter
    public ExecutorService getPool() {
        return pool;
    }

    /**
     * Имитирует отправку почты пользователю user
     * @param user - пользователь, котору отправляется поста
     */
    public void emailTo(User user) {
        String userName = user.userName;
        String email = user.email;
        String subject = "Notification " + userName + " to email " + email;
        String body = "Add a new event to " + userName;

        pool.submit(() -> EmailNotification.this.send(subject, body, email));
    }

    public void send(String subject, String body, String email) {
        System.out.println("EmailNotification.send: " + email + " executed in " + Thread.currentThread().getName());
    }

    /**
     * Закрывает пул потоков
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
