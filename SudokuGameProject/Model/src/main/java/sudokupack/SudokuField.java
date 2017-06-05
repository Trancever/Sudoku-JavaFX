package sudokupack;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class SudokuField represents one field of the sudoku grid.
 */
@Entity
public class SudokuField implements Serializable, Cloneable, Comparable {

    /**
     *  Id of SudokuField needed for database mapping
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Current value of SudokuField
     */
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "Value")
    private Integer value;

    /**
     * Flag which represents if field can be changeable.
     */
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "IsChangeable")
    private Boolean isChangeable;

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public void setSudokuBoard(final SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    /**
     * Reference to Sudoku which contains this SudokuField
     */
    @ManyToOne
    @JoinColumn(name = "board_fk", referencedColumnName = "id")
    private SudokuBoard sudokuBoard;

    /**
     * Parameterless constructor.
     * Sets value to 0 and isChangeable to false.
     */
    public SudokuField() {
        value = new Integer(0);
        isChangeable = new Boolean(false);
    }

    /**
     * Constructor with 1 int parameter
     * @param value value of SudokuField
     */
    public SudokuField(final int value) {
        this.value = value;
        this.isChangeable = new Boolean(false);
    }

    public int getValue() {
        int value = this.value.intValue();
        return value;
    }

    public void setValue(final int value) {
        this.value = new Integer(value);
    }

    public Boolean getChangeable() {
        return isChangeable;
    }

    public void setChangeable(final Boolean changeable) {
        isChangeable = changeable;
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
