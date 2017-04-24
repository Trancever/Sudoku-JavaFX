package sudokupack;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

public class SudokuField implements Serializable, Cloneable, Comparable {

    private Integer field;

    public SudokuField() {
        field = new Integer(0);
    }
    public SudokuField(final int value) {
        field = value;
    }

    public int getValue() {
        int value = field.intValue();
        return value;
    }

    public void setValue(final int value) {
        field = new Integer(value);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Integer value = new Integer(this.field.intValue());
        return new SudokuField(value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass()).add("field", field).
                toString();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuField other = (SudokuField) obj;
        return this.getValue() == other.getValue();
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.field);
    }

    public int compareTo(final Object obj) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (this == obj) {
            return EQUAL;
        }
        SudokuField other = (SudokuField) obj;
        if (this.field.intValue() < other.field.intValue()) {
            return BEFORE;
        } else if (this.field.intValue() > other.field.intValue()) {
            return AFTER;
        }

        assert this.equals(other) : "SudokuField compareTo do not match with equals method";
        return EQUAL;
    }
}
