package pro.sky.coursework1.data;

/**
 * Генератор последовательностей
 * // TODO: 28.12.2022 сделать пул именованных последовательностей
 *
 */

final class EmployeesSequence {
    private int idCounter = 1000;

    private EmployeesSequence() {
    }

    public static EmployeesSequence getInstance() {
        return EmployeesSequence.SingletonHelper.INSTANCE;
    }

    public int getNumber() {
        return idCounter++;
    }

    private static final class SingletonHelper {
        private static final EmployeesSequence INSTANCE = new EmployeesSequence();
    }
}
