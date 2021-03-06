package sudokupack;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * SudokuItem is an abstract class that represents items which sudoku contains
 */
public abstract class SudokuItem {

    /**
     * values which represents row in sudoku
     */
    protected ArrayList<SudokuField> values;

    /**
     * Constructor of SudokuItem
     * @param values values is List of SudokuFields which represents row in Sudoku
     */
    public SudokuItem(final ArrayList<SudokuField> values) {
        this.values = values;
    }

    /**
     * verify is a method that checks if values contains only unique values
     * @return true if contains unique values, false if contains duplicated values (excluding zeroes)
     */
    public boolean verify() {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getValue() != 0) {
                if (!set.add(values.get(i).getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * isSolved is a method that checks if values contains only unique values
     * @return true if contains unique values, false if contains duplicated values (including zeroes)
     */
    public boolean isSolved() {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getValue() == 0) {
                return false;
            }
            if (!set.add(values.get(i).getValue())) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<SudokuField> getAll() {
        return this.values;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass()).add("values", values)
                .toString();
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
        final SudokuItem other = (SudokuItem) obj;
        final ArrayList<SudokuField> list = other.getAll();
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getValue() != list.get(i).getValue()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.values);
    }
}
