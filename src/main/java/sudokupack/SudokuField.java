package sudokupack;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

public class SudokuField implements Serializable {

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
}
