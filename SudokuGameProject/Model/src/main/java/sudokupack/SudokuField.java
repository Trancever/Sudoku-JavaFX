package sudokupack;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

public class SudokuField implements Serializable, Cloneable, Comparable {

    private Integer value;

    public SudokuField() {
        value = new Integer(0);
    }
    public SudokuField(final int value) {
        this.value = value;
    }

    public int getValue() {
        int value = this.value.intValue();
        return value;
    }

    public void setValue(final int value) {
        this.value = new Integer(value);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Integer value = new Integer(this.value.intValue());
        return new SudokuField(value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass()).add("value", value).
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
        return Objects.hashCode(this.value);
    }

    public int compareTo(final Object obj) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (this == obj) {
            return EQUAL;
        }
        SudokuField other = (SudokuField) obj;
        if (this.value.intValue() < other.value.intValue()) {
            return BEFORE;
        } else if (this.value.intValue() > other.value.intValue()) {
            return AFTER;
        }

        assert this.equals(other) : "Sudokuvalue compareTo do not match with equals method";
        return EQUAL;
    }
}
