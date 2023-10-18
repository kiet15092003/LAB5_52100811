package dao;

import Utils.HibernateUtils;
import models.Product;
import org.hibernate.Session;
import respository.RespositoryUser;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements RespositoryUser<Product,String> {
    private static final Session session;
    static {
        session = HibernateUtils.getFactory().openSession();
    }

    @Override
    public boolean add(Product product) {
        try {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<Product>();
        try{
            session.beginTransaction();
            Query query = session.createQuery("From Product ");
            products = query.getResultList();
            session.getTransaction().commit();
            return products;
        } catch(Exception e){
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getByUserName(String name) {
        try{
            session.beginTransaction();
            Query query = session.createQuery("From Product where name = :name");
            query.setParameter("name",name);
            Product product = (Product) query.getSingleResult();
            session.getTransaction().commit();
            return product;
        } catch(Exception e) {
            return null;
        }
    }

    public boolean removeById(Integer id){
        try {
            session.beginTransaction();
            Query query = session.createQuery("From Product where id = :id");
            query.setParameter("id",id);
            Product product = (Product) query.getSingleResult();
            session.remove(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
