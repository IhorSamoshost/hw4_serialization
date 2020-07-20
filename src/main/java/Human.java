import java.time.LocalDate;

public class Human {
    private String firstName;
    private String lastName;

    @JsonValue(name = "fun")
    private String hobby;
    @CustomDateFormat(format = "dd-MM-yyyy")
    private LocalDate birthDate;

    public Human(String firstName, String lastName, String hobby, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobby = hobby;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHobby() {
        return hobby;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Human{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hobby='" + hobby + '\'' +
                ", birthDate=" + birthDate.toString() +
                '}';
    }
}