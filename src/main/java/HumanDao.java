import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HumanDao {
    private static final String PATH_HUMAN_DB_FILE = "src\\main\\resources\\humanDB.txt";
    public static final String SEPARATOR = "!";

    public static void createHumanFile(List<Human> humanList) {
        try (FileWriter fileWriter = new FileWriter(PATH_HUMAN_DB_FILE, true)) {
            for(Human h:humanList) {
                fileWriter.append(h.getFirstName() + SEPARATOR + h.getLastName() + SEPARATOR +
                        h.getHobby() + SEPARATOR + h.getBirthDate().toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Human> getHumansFromFile() {
        List<Human> humanList = new ArrayList<>();
        List<String> humanLines = new ArrayList<>();
        try {
            humanLines = Files.readAllLines(Paths.get(PATH_HUMAN_DB_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String hl : humanLines) {
            String[] str = hl.split("!");
            Human human = new Human(str[0], str[1], str[2], LocalDate.parse(str[3]));
            humanList.add(human);
        }
        return humanList;
    }

    public static void toJson(List<Human> humanList) {
        CustomAnnotationHandler customAnnotationHandler = new CustomAnnotationHandler();
//        List <String> fieldNamesList = customAnnotationHandler.checkValueAnnotaition();
        try {
            System.out.println("{ [");
            for (Human human : humanList) {
                System.out.println("{");
                System.out.println("\"" + Human.class.getDeclaredField("firstName").getName() + "\" : \"" + human.getFirstName() + "\",");
                System.out.println("\"" + Human.class.getDeclaredField("lastName").getName() + "\" : \"" + human.getLastName() + "\",");
                System.out.println("\"" + customAnnotationHandler.checkValueAnnotaition() + "\" : \"" + human.getHobby() + "\",");
                System.out.println("\"" + Human.class.getDeclaredField("birthDate").getName() + "\" : \"" +
                        customAnnotationHandler.checkDateFormatAnnotaition(human) + "\"");
                System.out.println("}");
            }
            System.out.println("] }");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Human> humanList = Arrays.asList(
                new Human("Ivan", "Ivanov", "Art", LocalDate.of(1990, 9, 20))
                , new Human("Petr", "Petrov", "Sport", LocalDate.of(2000, 2, 17))
        );
        createHumanFile(humanList);
        for (Human h : getHumansFromFile()) {
            System.out.println(h);
        }
    }
}