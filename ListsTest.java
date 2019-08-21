import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class ListsTest {

    private static final List<Person> personsAfterSort = new ArrayList<>(Arrays.asList(
            new Person(1, "Аркадий"),
            new Person(13, "Рёрик "),
            new Person(14, "Агафий "),
            new Person(15, "Мерилл"),
            new Person(5, "Мухомор "),
            new Person(6, "Доброжир "),
            new Person(7, "Иркаил "),
            new Person(8, "Серафим  "),
            new Person(9, "Агниил"),
            new Person(50, "Святополк"),
            new Person(2, "Баркадий")
    ));
    private static final int[] ids = new int[]{10, 1, 12, 13, 14, 15};

    @Test
    public void equalsOldListPersonsWithNewPersonsSort() {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person(5, "Мухомор"),
                new Person(6, "Доброжир "),
                new Person(7, "Иркаил "),
                new Person(8, "Серафим"),
                new Person(9, "Агниил "),
                new Person(15, "Мерилл "),
                new Person(14, "Агафий "),
                new Person(13, "Рёрик  "),
                new Person(50, "Святополк"),
                new Person(2, "Баркадий"),
                new Person(1, "Аркадий")
        ));

        List oldSortPersons = new ArrayList<>(persons);
        Person.order(persons, ids);

        Assert.assertNotEquals(oldSortPersons, persons);
    }

    @Test
    public void equalsTrueAfterSortPersonsWithNewPersonsSort() {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person(5, "Мухомор"),
                new Person(6, "Доброжир "),
                new Person(7, "Иркаил "),
                new Person(8, "Серафим"),
                new Person(9, "Агниил "),
                new Person(15, "Мерилл "),
                new Person(14, "Агафий "),
                new Person(13, "Рёрик  "),
                new Person(50, "Святополк"),
                new Person(2, "Баркадий"),
                new Person(1, "Аркадий")
        ));

        Person.order(persons, ids);

        Assert.assertEquals(persons, personsAfterSort);
    }
}

class Person {
    int id;
    String name;

    Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;

        return id == person.id &&
                name.replaceAll("\\s+","")
                        .equalsIgnoreCase(person.name.replaceAll("\\s+",""));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    static void order(List<Person> persons, int[] ids) {
        List lastItems = new ArrayList<>(persons);
        List topItems = new ArrayList<Person>();

        for (int id : ids) {
            for (Person person : persons) {
                if (id == person.id) {
                    topItems.add(person);
                    lastItems.remove(person);
                }
            }
        }
        persons.clear();
        persons.addAll(topItems);
        persons.addAll(lastItems);
    }
}
