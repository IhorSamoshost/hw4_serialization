import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Object> humanList = HumanDao.getHumansFromFile();
        HumanDao.toJson(humanList);
    }
}
