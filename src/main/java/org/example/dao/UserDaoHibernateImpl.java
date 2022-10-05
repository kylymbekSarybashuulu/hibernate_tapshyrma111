package org.example.dao;

import org.example.model.User;
import org.example.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
   SessionFactory session = new Util().getSessionFactory();


    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
           session.createQuery("create table User(" +
                    " id serial primary key," +
                    " name varchar(30)," +
                    " lastname varchar(40)," +
                    " age smallint not null)").executeUpdate();
            session.beginTransaction();
            session.getTransaction().commit();
            session.close();
            System.out.println("hibernate таблицасы тузулду");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {Session session = Util.getSessionFactory().openSession();
           // session.createQuery("drop table User");
            session.beginTransaction();
            session.getTransaction().commit();
            session.close();
            System.out.println("hibernate уделение болду");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
            System.out.println("hibernate маалымат кошулду");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try {Session session = Util.getSessionFactory().openSession();
            session.createQuery("delete User where id = :id")
                    .setParameter("id", id).executeUpdate();
            session.beginTransaction();
            session.getTransaction().commit();
            session.close();
            System.out.println("hibernate айди мн очуруу");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {Session session = Util.getSessionFactory().openSession();
            userList = session.createQuery("from User order by name").list();
            session.beginTransaction();
            session.getTransaction().commit();
            session.close();
            System.out.println("hibernate >>>");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try {Session session = Util.getSessionFactory().openSession();
            session.createQuery("delete User").executeUpdate();
            session.beginTransaction();
            session.getTransaction().commit();
            session.close();
            System.out.println("hibernate чистка болду");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
