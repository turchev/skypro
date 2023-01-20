package pro.sky.listqueue.rally.person;

/*
 * Дополнительно создан абстрактный класс Person и в него вынесены общие с водителем поля и методы
 */
public abstract class Person {
    protected String nick;

    public Person(String nick) {
        setNick(nick);
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        if (nick == null) {
            throw new IllegalArgumentException();
        }
        this.nick = nick;
    }
}
