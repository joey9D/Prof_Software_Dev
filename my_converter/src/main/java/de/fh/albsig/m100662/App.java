package de.fh.albsig.m100662;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import de.fh.albsig.m100662.ConverterUtils.NumberFactory;
import de.fh.albsig.m100662.ConverterUtils.NewNumber;

public class App {
  /**
  * Die Hauptmethode, die als Einstiegspunkt für die Anwendung dient.
  * Sie gibt "Hello World!" auf der Konsole aus und führt eine einfache
  * Addition von zwei Zahlen durch.
  *
  * @param args Die Befehlszeilenargumente.
  */
  public static void main(final String[] args) {
    System.out.println("Enter a value and a unit.");
    Scanner scannerValue = new Scanner(System.in, StandardCharsets.UTF_8);
    System.out.println("Value: ");
    BigDecimal value = scannerValue.nextBigDecimal();
    Scanner scannerUnit = new Scanner(System.in, StandardCharsets.UTF_8);
    System.out.println("Unit: ");
    String unit = scannerUnit.nextLine();

    NewNumber inputNumber = NumberFactory.createNewNumber(value, unit);

    inputNumber.printNumber();

    if (inputNumber instanceof ConverterUtils.Lengths) {
      ConverterUtils.Lengths lengthInput = (ConverterUtils.Lengths) inputNumber;
      lengthInput.printLengthUnits();
      lengthInput.convertLength();
      lengthInput.printNumber();
    }

    scannerValue.close();
    scannerUnit.close();
  }
}
