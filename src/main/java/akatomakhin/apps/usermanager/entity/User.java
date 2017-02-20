package akatomakhin.apps.usermanager.entity;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */

public class  User {
    private int id;
    private String name;
    private int age;
    private String employment;
    private String hoby;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getEmployment() {
        return employment;
    }

    public void setHoby(String hoby) {
        this.hoby = hoby;
    }

    public String getHoby() {
        return hoby;
    }

    @Override
    public String toString() {
        return "{ \"id\":\"" + id + "\", \"name\":\"" + name + "\"," +
                " \"age\":\"" + age + "\", \"employment\":\"" + employment + "\"," +
                " \"hoby\":\"" + hoby + "\"}";
    }
}
