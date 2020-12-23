package trackerSingle;

/**
 * Поле экземпляра обозначено volatile. Это обеспечит решение проблемы видимости, после инициализации поля.
 *
 * Первая проверка экземпляра идет до блока синхронизации, что позволяет улучить скорость работы по сравнению с single checked locking реализацией.
 *
 * В критической секции происходит инициализация экземпляра и запись его в переменную.
 *
 * Этот шаблон использовать не рекомендуется. Он уменьшает производительность системы при многопроцессорном окружении.
 */
public class TrackerSingle21 {
    private static volatile TrackerSingle21 INSTANCE;

    private TrackerSingle21() {
    }

    public static TrackerSingle21 getInstance() {
        if (INSTANCE == null) {
            synchronized (TrackerSingle.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TrackerSingle21();
                }
            }
        }
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingle21 tracker = TrackerSingle21.getInstance();
    }
}
