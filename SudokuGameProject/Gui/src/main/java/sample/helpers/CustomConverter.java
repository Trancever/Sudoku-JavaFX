package sample.helpers;

import javafx.util.StringConverter;

/**
 * Created by Trensik on 5/21/2017.
 */
public class CustomConverter extends StringConverter<Number> {

    @Override
    public String toString(final Number object) {
        if (object.intValue() == 0) {
            return "";
        } else {
            return Integer.toString(object.intValue());
        }
    }

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
