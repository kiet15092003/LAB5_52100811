package dao;

import Utils.HibernateUtils;
import respository.RespositoryUser;
import models.User;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class UserDao implements RespositoryUser<User,String> {
    private static final Session session;
    static {
        session = HibernateUtils.getFactory().openSession();
    }
    @Override
    public boolean add(User user) {
        try {
            //session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        try{
            session.beginTransaction();
            Query query = session.createQuery("From User ");
            List<User> users = query.getResultList();
            session.getTransaction().commit();
            return users;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByUserName(String userName) {
        try{
            session.beginTransaction();
            Query query = session.createQuery("From User where username = :username");
            query.setParameter("username",userName);
            User user = (User) query.getSingleResult();
            session.getTransaction().commit();
            return user;
        } catch(Exception e) {
            return null;
        }
    }
}
