package id.co.kakaroto.iak_sqlitedemo.model;

/**
 * Created by kakaroto on 19/11/17.
 */

public class Person {
    private int id;
    private String name;
    private String hoby;

    public Person() {
    }

    public Person(String name, String hoby) {
        this.name = name;
        this.hoby = hoby;
    }

    public Person(int id, String name, String hoby) {
        this.id = id;
        this.name = name;
        this.hoby = hoby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHoby() {
        return hoby;
    }

    public void setHoby(String hoby) {
        this.hoby = hoby;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hoby='" + hoby + '\'' +
                '}';
    }
}
