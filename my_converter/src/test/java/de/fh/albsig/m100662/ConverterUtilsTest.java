package de.fh.albsig.m100662;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.math.BigDecimal;


/**
 * Unit test for simple App.
 */
public class ConverterUtilsTest {

  private ByteArrayOutputStream outputStream;
  private PrintStream originalOut;

  @BeforeAll
  void setUpClass() {
      // Aktionen, die vor allen Tests ausgeführt werden müssen
      System.out.println("Initialisiere Tests...");
  }

  @AfterAll
  void tearDownClass() {
      // Aktionen, die nach allen Tests ausgeführt werden müssen
      System.out.println("Beende Tests...");
  }

  @BeforeEach
  void setUp() {
  // Vorbereitung, die vor jedem Test erfolgen muss
  outputStream = new ByteArrayOutputStream();
  originalOut = System.out;
  System.setOut(new PrintStream(outputStream));
}
  
  @AfterEach
  void tearDown() {
      // Aufräumarbeiten, die nach jedem Test erfolgen müssen
      System.setOut(originalOut);
  }

  @Test
  void testNewNumberConstructor() {
    // Arrange
    BigDecimal value = BigDecimal.valueOf(10.5);
    String unit = "m";

    // Act
    ConverterUtils.NewNumber number = new ConverterUtils.NewNumber(value, unit);

    // Assert
    assertEquals(value, number.getValue());
    assertEquals(unit, number.getUnit());
  }

  @Test
  void testNewNumberSettersAndGetters() {
    // Arrange
    ConverterUtils.NewNumber number = new ConverterUtils.NewNumber(BigDecimal.valueOf(5), "cm");

    // Act
    number.setValue(BigDecimal.valueOf(12.3));
    number.setUnit("m");

    // Assert
    assertEquals(BigDecimal.valueOf(12.3), number.getValue());
    assertEquals("m", number.getUnit());
  }

  @Test
  void testPrintNumber() {
    // Arrange
    ConverterUtils.NewNumber number = new ConverterUtils.NewNumber(BigDecimal.valueOf(15), "km");

    // Act
    number.printNumber();

    // Assert
    assertEquals("Input: 15 km\n", outputStream.toString());
  }

  @Test
  void testNumberFactory() {
    // Arrange
    BigDecimal value = BigDecimal.valueOf(500);
    String lengthUnit = "m";
    String invalidUnit = "kg";

    // Act
    ConverterUtils.NewNumber lengthNumber = ConverterUtils.NumberFactory.createNewNumber(value, lengthUnit);
    ConverterUtils.NewNumber invalidNumber = ConverterUtils.NumberFactory.createNewNumber(value, invalidUnit);

    // Assert
    assertNotNull(lengthNumber);
    assertTrue(lengthNumber instanceof ConverterUtils.Lengths);
    assertEquals(value, lengthNumber.getValue());
    assertEquals(lengthUnit, lengthNumber.getUnit());

    assertNull(invalidNumber);
  }

  @Test
  void testConvertLength() {
    // Arrange
    ConverterUtils.Lengths length = new ConverterUtils.Lengths(BigDecimal.valueOf(10), "m");

    // Simuliere Benutzereingabe für die Zieleinheit (z. B. "km")
    String simulatedInput = "km\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    // Act
    length.convertLength();

    // Assert
    assertEquals(BigDecimal.valueOf(0.01), length.getValue()); // 10 m = 0.01 km
    assertEquals("km", length.getUnit());

    // Wiederherstellen des Standard-Eingabestreams
    System.setIn(System.in);
  }

}
