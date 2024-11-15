package com.mycompany.app;

import java.math.BigDecimal;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConverterUtils {
  /**
   * Contains functions to convert input numbers.
   *
   */
  public static class NewNumber {
    /**
     * numerical value.
     */
    private BigDecimal value;
    /**
     * string representation of the unit of the number.
     */
    private String unit;

    /**
     * Constructor for new number with value and unit.
     * @param nValue The numerical value of the number
     * @param nUnit The string representation of the unit of the number
     */
    public NewNumber(final BigDecimal nValue, final String nUnit) {
      this.value = nValue;
      this.unit = nUnit;
    }

    /**
     * getter method for the value.
     * @return value
     */
    public BigDecimal getValue() {
      return value;
    }
    /**
     * getter method for the unit.
     * @return unit
     */
    public String getUnit() {
      return unit;
    }

    /**
     * setter method for the value.
     * @param nValue
     */
    public void setValue(final BigDecimal nValue) {
      this.value = nValue;
    }
    /**
     * setter method for the unit.
     * @param nUnit
     */
    public void setUnit(final String nUnit) {
      this.unit = nUnit;
    }


    /**
     * custom print method to print the number with value and unit.
     */
    public void printNumber() {
      System.out.println("Input: " + this.value + " " + this.unit);
    }
  }

  public static class Lengths extends NewNumber {
    /**
     * An ArrayList which contains length unit strings.
     */
    public static final List<String> LENGTH_UNITS = Arrays.asList(
      "km", "m", "dm", "cm", "mm", "miles"
    );
    /**
     * Conversionfactor for meters.
     */
    public static final BigDecimal METER_FACTOR =
      BigDecimal.valueOf(1.0);
    /**
     * Conversionfactor for decimeters.
     */
    public static final BigDecimal DECIMETER_FACTOR =
      BigDecimal.valueOf(0.1);
    /**
     * Conversionfactor for centimetres.
     */
    public static final BigDecimal CENTIMETER_FACTOR =
      BigDecimal.valueOf(0.01);
    /**
     * Conversionfactor for millimetres.
     */
    public static final BigDecimal MILLIMETER_FACTOR =
      BigDecimal.valueOf(0.001);
    /**
     * Conversionfactor for kilometers.
     */
    public static final BigDecimal KILOMETER_FACTOR =
      BigDecimal.valueOf(1000.0);
    /**
     * Conversionfactor for miles.
     */
    public static final BigDecimal MILE_FACTOR =
      BigDecimal.valueOf(1609.34);
    /**
     * Map that connects the length units with their
     * corresponding conversionvalues.
     */
    public static final Map<String, BigDecimal>
      CONVERSION_FACTORS = new HashMap<>();
    static {
      CONVERSION_FACTORS.put("m", METER_FACTOR);
      CONVERSION_FACTORS.put("dm", DECIMETER_FACTOR);
      CONVERSION_FACTORS.put("cm", CENTIMETER_FACTOR);
      CONVERSION_FACTORS.put("mm", MILLIMETER_FACTOR);
      CONVERSION_FACTORS.put("km", KILOMETER_FACTOR);
      CONVERSION_FACTORS.put("miles", MILE_FACTOR);
    }

    /**
     * Constructor for length number. Uses parent constructor.
     * @param lengthValue
     * @param lengthUnit
     */
    public Lengths(final BigDecimal lengthValue, final String lengthUnit) {
      super(lengthValue, lengthUnit);
    }

    /**
     * Printfunction. Prints available length units.
     */
    public void printLengthUnits() {
      System.out.println("\nUnits to choose from: ");
      for (int i = 0; i < LENGTH_UNITS.size(); i++) {
        System.out.println(LENGTH_UNITS.get(i));
      }
    }

    /**
     * Convert numerical value according to chosen result unit.
     */
    public void convertLength() {
      System.out.println("\nChoose a result unti:");

      Scanner unitScanner = new Scanner(System.in);
      String resultUnit = unitScanner.nextLine();
      // throw exception on pre-requiered condition
      if (!CONVERSION_FACTORS.containsKey(this.unit)
        || !CONVERSION_FACTORS.containsKey(resultUnit)) {
        throw new IllegalArgumentException(
          "Invalid unit for conversion: " + resultUnit
        );
      }

      BigDecimal siValue = this.value.multiply(
        CONVERSION_FACTORS.get(this.unit)
      );
      BigDecimal resultValue = siValue.divide(
        CONVERSION_FACTORS.get(resultUnit)
      );

      this.value = resultValue;
      this.unit = resultUnit;

      unitScanner.close();
    }

  }

  public static class NumberFactory {
    /**
     * Number factory chooses right constructor according to the unit parameter.
     * @param value
     * @param unit
     * @return number object fitting the given unit.
     */
    public static NewNumber createNewNumber(
      final BigDecimal value, final String unit
    ) {
      if (Lengths.LENGTH_UNITS.contains(unit)) {
        return new Lengths(value, unit);
      } else {
        return null;
      }
    }
  }
}
