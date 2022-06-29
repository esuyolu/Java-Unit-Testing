import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {

    ContactManager contactManager;

    @BeforeAll
    void setupAll() {
        System.out.println("Should print before all tests.");
    }

    @BeforeEach
    void setup() {
        contactManager = new ContactManager();
    }

    @Test
    @Disabled
    void shouldCreateContact() {
        contactManager.addContact("Esma", "Suyolu", "5344034741");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts()
                .stream()
                .anyMatch(contact ->
                        contact.getFirstName().equals("Esma") &&
                        contact.getLastName().equals("Suyolu") &&
                        contact.getPhoneNumber().equals("5344034741")));
    }

    @Test
    @DisplayName("Should not create contact when first name is null!")
    void shouldThrowRunTimeExceptionWhenFirstNameIsNull() {
        assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Suyolu", "5344034741");
        });
    }

    @Test
    @DisplayName("Should not create contact when last name is null!")
    void shouldThrowRunTimeExceptionWhenLastNameIsNull() {
        assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Esma", null, "5344034741");
        });
    }

    @Test
    @DisplayName("Should not create contact when phone number is null!")
    void shouldThrowRunTimeExceptionWhenPhoneNumberIsNull() {
        assertThrows(RuntimeException.class, () -> {
           contactManager.addContact("Esma", "Suyolu", null);
        });
    }

    @AfterEach
    void tearDown() {
        System.out.println("Should execute after each test.");
    }

    @AfterAll
    void tearDownAll() {
        System.out.println("Should be executed at the end of the test.");
    }

    @RepeatedTest(value = 5, name = "Repeating contact creation test {currentRepetition} of {totalRepetitions}")
    @DisplayName("Repeat contact creation test 5 times")
    void shouldCreateContactRepeatedly() {
        contactManager.addContact("Esma", "Suyolu", "5344034741");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"5344034741", "02125337716", "1234567890"})
    @DisplayName("Phone number validation")
    void shouldCreateContactUsingValueSource(String phoneNumber) {
        contactManager.addContact("Esma", "Suyolu", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @ParameterizedTest
    @MethodSource("phoneNumberList")
    @DisplayName("Phone number validation using Method Source")
    void shouldCreateContactUsingMethodSource(String phoneNumber) {
        contactManager.addContact("Esma", "Suyolu", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    private List<String> phoneNumberList() {
        return Arrays.asList("5344034741", "02125337716", "1234567890");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    @DisplayName("Phone number validation using Csv File Source")
    void shouldCreateContactUsingCsvFileSource(String phoneNumber) {
        contactManager.addContact("Esma", "Suyolu", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }
}
