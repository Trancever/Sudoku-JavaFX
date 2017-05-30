package sudokupack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Trensik on 5/30/2017.
 */
public class JDBCSudokuBoardDao implements Dao<SudokuBoard> {

    static final Logger logger = LoggerFactory.getLogger(JDBCSudokuBoardDao.class);
    private final EntityManager manager;

    public JDBCSudokuBoardDao(final String unit) {
        manager = Persistence.createEntityManagerFactory(unit).createEntityManager();
    }

    public SudokuBoard read(final String name) {
        return manager.find(SudokuBoard.class, name);
    }

    public void write(final SudokuBoard obj, final String name) {
        manager.getTransaction().begin();
        manager.persist(obj);
        manager.getTransaction().commit();
    }

    public List<String> getAllBoardsNames() {
        Query q = manager.createQuery("select b.id from SudokuBoard b");
        return q.getResultList();
    }

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
