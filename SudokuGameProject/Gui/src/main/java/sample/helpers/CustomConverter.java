package sample.helpers;

import javafx.util.StringConverter;

/**
 * CustomConverter converts String to Number and viceVersa
 */
public class CustomConverter extends StringConverter<Number> {

    /**
     * converts Number to String
     * @param object Number object that has to be converted
     * @return String converted from Number
     */
    @Override
    public String toString(final Number object) {
        if (object.intValue() == 0) {
            return "";
        } else {
            return Integer.toString(object.intValue());
        }
    }

    /**
     * converts String to Number
     * @param string String object that has to be converted
     * @return Number converted from String
     */
    @Override
    public Number fromString(final String string) {
        if (string.equals("")) {
            return new Number() {
                @Override
                public int intValue() {
                    return 0;
                }

                @Override
                public long longValue() {
                    return 0;
                }

                @Override
                public float floatValue() {
                    return 0;
                }

                @Override
                public double doubleValue() {
                    return 0;
                }
            };
        } else {
            return new Number() {
                @Override
                public int intValue() {
                    return Integer.parseInt(string);
                }

                @Override
                public long longValue() {
                    return Long.parseLong(string);
                }

                @Override
                public float floatValue() {
                    return Float.parseFloat(string);
                }

                @Override
                public double doubleValue() {
                    return Double.parseDouble(string);
                }
            };
        }
    }
}
