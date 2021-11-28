package ru.novik.spring.dao;

import org.springframework.stereotype.Component;
import ru.novik.spring.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNT = 0;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(PEOPLE_COUNT++, "Tom", 24, "tom@mail.ru"));
        people.add(new Person(PEOPLE_COUNT++, "Bob", 52, "bob@mail.ru"));
        people.add(new Person(PEOPLE_COUNT++, "Carl", 18, "carl@mail.ru"));
        people.add(new Person(PEOPLE_COUNT++, "Katy", 21, "katy@mail.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        /*Person tempPerson = null;
        for (Person person : people) {
            if (id == person.getId()) {
                tempPerson = people.get(id);
            }
        }
        return tempPerson;*/
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(PEOPLE_COUNT++);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        /*for (int i = 0; i < people.size(); i++) {
            if (id == people.indexOf(people.get(i))) {
                people.remove(i);
            }
        }*/
        people.removeIf(p -> p.getId() == id);
    }
}
