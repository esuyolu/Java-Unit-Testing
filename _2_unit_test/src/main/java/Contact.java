public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void validateFirstName() {
        if (this.firstName.trim().isEmpty()) {
            throw new RuntimeException("First name cannot be null or empty!");
        }
    }

    public void validateLastName() {
        if (this.lastName.trim().isEmpty()) {
            throw new RuntimeException("Last name cannot be null or empty!");
        }
    }

    public void validatePhoneNumber() {
        if (this.phoneNumber.trim().isEmpty()) {
            throw new RuntimeException("Phone Number cannot be null or empty!");
        }

        if (this.phoneNumber.length() != 10) {
            throw new RuntimeException("Phone Number should be 10 digits long!");
        }

        if (this.phoneNumber.matches("^\\\\d{10}$")) {
            throw new RuntimeException("Phone Number contains only digits");
        }
    }
}