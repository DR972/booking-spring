package by.rozmysl.booking.repository.userRepo;

import org.springframework.session.Session;
/**
 * The interface is used to store data about Session
 */
public interface SessionRepo<S extends Session> {
    /**
     * The method creates a new session
     * @return  user session
     */
    S createSession();

    /**
     * The method saves a new session
     * @param session  user session
     */
    void save(S session);

    /**
     * The method searches for a session in the database of Session by id
     * @param id  session id
     * @return  user session
     */
    S findById(String id);

    /**
     * The method deletes the session by id
     * @param id  session id
     */
    void deleteById(String id);
}
