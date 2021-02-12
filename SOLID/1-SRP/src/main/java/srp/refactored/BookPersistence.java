package srp.refactored;

import java.io.IOException;

public interface BookPersistence {

    public void save(Book book) throws IOException;
}
