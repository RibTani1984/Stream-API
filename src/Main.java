import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Петр", "Мария", "Татьяна", "Александр", "Андрей", "Владимир", "Евгений");
        List<String> families = Arrays.asList("Серганин", "Резникова", "Иванова", "Макеев", "Чушкин", "Ермаков", "Пантелев");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long minors = persons.stream()
                .filter(people -> (people.getAge() < 18))
                .count();
        System.out.println("1) Количество несовершеннолетних: " + minors);
        System.out.println();
        List<String> conscripts = persons.stream()
                .filter(people -> (people.getAge() >= 18 && people.getAge() < 27 && people.getSex() == Sex.MAN))
                .map(Person::getFamily)
                // .limit(5)
                .collect(Collectors.toList());
        //System.out.println("2) Список фамилий призывников: ");
        //conscripts.forEach(System.out::println);
        System.out.println("2) Всего призывников: " + conscripts.size() + " человек");
        List<Person> ableBodied = persons.stream()
                .filter(people -> people.getEducation() == Education.HIGHER)
                .filter(people -> people.getAge() >= 18)
                .filter(person -> (person.getSex() == Sex.WOMAN && person.getAge() < 60) ||
                        (person.getSex() == Sex.MAN && person.getAge() < 65))
                //.limit(2)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println();
        //ableBodied.forEach(System.out::println);
        System.out.println("3) Список трудоспособных людей с высшим образованием: " + ableBodied.size() + " человек");
    }
}

