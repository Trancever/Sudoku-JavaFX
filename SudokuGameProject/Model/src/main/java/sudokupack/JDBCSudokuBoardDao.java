package sudokupack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * JDBCSudokuBoardDao is an implementation of Dao class. It's dao that read and write Sudoku from/to database.
 */
public class JDBCSudokuBoardDao implements Dao<SudokuBoard> {

    static final Logger logger = LoggerFactory.getLogger(JDBCSudokuBoardDao.class);

    /**
     * manager is an interface that allows access to database context
     */
    private final EntityManager manager;

    /**
     * Construcotr of JDBCSudokuBoardDao
     * @param unit unit is a name of the persistance-unit name.
     */
    public JDBCSudokuBoardDao(final String unit) {
        manager = Persistence.createEntityManagerFactory(unit).createEntityManager();
    }

    /**
     * read SudokuBoard of given name from database.
     * @param name name of the Sudoku
     * @return SudokuBoard
     */
    public SudokuBoard read(final String name) {
        return manager.find(SudokuBoard.class, name);
    }

    /**
     * write SudokuBoard to database
     * @param obj SudokuBoard which will be written
     * @param name name of the Sudoku which will be it's id in database
     */
    public void write(final SudokuBoard obj, final String name) {
        manager.getTransaction().begin();
        manager.persist(obj);
        manager.getTransaction().commit();
    }

    /**
     * getAllBoardNames returns all names of SudokuBoards that database currently contains
     * @return List of strings with SudokuBoard names.
     */
    public List<String> getAllBoardsNames() {
        Query q = manager.createQuery("select b.id from SudokuBoard b");
        return q.getResultList();
    }

    /**
     * finalize close existing connection to database.
     */
    @Override
    public void finalize() {
        try {
            manager.close();
            logger.info("Successfully closed connection to database.");
        } catch (Exception e) {
            logger.error("Exception while closing connection to database.");
        }
    }
}
