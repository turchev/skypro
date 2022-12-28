package pro.sky.coursework1.data;

import pro.sky.coursework1.entity.Employee;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface EmployeesData {

    static EmployeesData getInstance() {
        return new EmployeesAdapter();
    }

    Employee[] getEmployees();

    boolean addEmployee(@Nonnull Employee employee);

    void delEmployee(int id);

    @Nullable
    Employee getEmployeeByNick(@Nonnull String nick);

    int getNextNumber();

    final class EmployeesAdapter implements EmployeesData {

        /**
         * @return Массив Employee из класса-заглушки, имитирующего хранилище.
         */
        @Override
        public Employee[] getEmployees() {
            return EmployeesDataStub.getInstance().getEmployees();
        }

        /**
         * @param employee – элемент, присутствие которого в этом массиве должно быть обеспечено
         * @return true, если размер массива изменился в результате вызова.
         * false - если элемент был вставлен в пустую ячейку и массив не был изменен
         */
        @Override
        public boolean addEmployee(@Nonnull Employee employee) {
            return EmployeesDataStub.getInstance().addEmployee(employee);
        }

        /**
         * @param id - идентификатор удаляемого сотрудника
         */
        @Override
        public void delEmployee(int id) {
            EmployeesDataStub.getInstance().delEmployee(id);
        }

        /**
         * @param nick - строка вида "Фамилия Имя Отчество"
         * @return Employee - если найден объект и null если не найден
         */
        @Override
        public Employee getEmployeeByNick(@Nonnull String nick) {
            return EmployeesDataStub.getInstance().getEmployeeByNick(nick);
        }

        /**
         * @return Текущее значение счетчика последовательности и увеличивает его на 1
         */
        @Override
        public int getNextNumber() {
            return EmployeesSequence.getInstance().getNumber();
        }
    }

}
