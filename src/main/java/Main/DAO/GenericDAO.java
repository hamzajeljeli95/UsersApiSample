package Main.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDAO<E, PK extends java.io.Serializable> {

    public static SessionFactory sessionFactory;
    public static Connection conn;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
            conn = sessionFactory.getSessionFactoryOptions().getServiceRegistry().
                    getService(ConnectionProvider.class).getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final Class<E> entityClass;

    public GenericDAO() {
        this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public static Connection getConnection() throws SQLException {
        if ((conn == null) != (conn.isClosed())) {
            conn = sessionFactory.getSessionFactoryOptions().getServiceRegistry().
                    getService(ConnectionProvider.class).getConnection();
        }
        return conn;
    }

    public Class<E> getEntityClass() {
        return entityClass;
    }

    public boolean save(E newInstance) {
        boolean res = false;
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(newInstance);
            session.getTransaction().commit();
            session.close();
            res = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public void saveWithReturn(E newInstance) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            newInstance = (E) session.save(newInstance);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public boolean update(E transientObject) {
        boolean res = false;
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(transientObject);
            session.getTransaction().commit();
            session.close();
            res = true;
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return res;
    }

    public boolean saveOrUpdate(E transientObject) {
        boolean res = false;
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(transientObject);
            session.getTransaction().commit();
            session.close();
            res = true;
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return res;
    }

    public boolean delete(E persistentObject) {
        boolean res = false;
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(persistentObject);
            session.getTransaction().commit();
            session.close();
            res = true;
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return res;
    }

    public List<E> findAll() {
        List<E> results = null;
        try {
            Session session = sessionFactory.openSession();
            Criteria crit = session.createCriteria(getEntityClass());
            results = crit.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return results;
    }

    public List<E> findAllBy1Properties(String propertyName1, Object value1) {
        List<E> results = null;
        try {
            Session session = sessionFactory.openSession();
            results = session.createQuery("from " + getEntityClass().getName() + " u where u." + propertyName1 + " = :one")
                    .setParameter("one", value1)
                    .list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }

    public List<E> findAllBy2Properties(String propertyName1, Object value1, String propertyName2, Object value2) {
        List<E> results = null;
        try {
            Session session = sessionFactory.openSession();
            results = session.createQuery("from " + getEntityClass().getName() + " u where u." + propertyName1 + " = :one and u." + propertyName2 + " = :two ")
                    .setParameter("one", value1)
                    .setParameter("two", value2)
                    .list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }

    public List<E> findAllBy3Properties(String propertyName1, Object value1, String propertyName2, Object value2, String propertyName3, Object value3) {
        List<E> results = null;
        try {
            Session session = sessionFactory.openSession();
            results = session.createQuery("from " + getEntityClass().getName() + " u where u." + propertyName1 + " = :one and u." + propertyName2 + " = :two and u." + propertyName3 + " = :three")
                    .setParameter("one", value1)
                    .setParameter("two", value2)
                    .setParameter("three", value3)
                    .list();

            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return results;

    }

    public List<E> findAllBy4Properties(String propertyName1, Object value1, String propertyName2, Object value2, String propertyName3, Object value3, String propertyName4, Object value4) {
        List<E> results = null;
        try {
            Session session = sessionFactory.openSession();
            results = session.createQuery("from " + getEntityClass().getName() + " u where u." + propertyName1 + " = :one and u." + propertyName2 + " = :two and u." + propertyName3 + " = :three and u." + propertyName4 + " = :four")
                    .setParameter("one", value1)
                    .setParameter("two", value2)
                    .setParameter("three", value3)
                    .setParameter("four", value4)
                    .list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }
}
