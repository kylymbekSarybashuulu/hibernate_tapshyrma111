package org.example;

import org.example.dao.UserDao;
import org.example.dao.UserDaoHibernateImpl;
import org.example.dao.UserDaoJdbcImpl;
import org.example.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {

        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.createUsersTable();
        userDaoJdbc.saveUser("Kylymbek", "Sarybash", (byte) 22);
        userDaoJdbc.saveUser("Aliya", "Kadirbekova", (byte) 22);
        userDaoJdbc.saveUser("Uson", "Taalaybek", (byte) 21);
        userDaoJdbc.saveUser("Jasmin", "Turdubaeva", (byte) 20);
        userDaoJdbc.dropUsersTable();
        userDaoJdbc.removeUserById(2);
        userDaoJdbc.cleanUsersTable();
        List<User> userList = userDaoJdbc.getAllUsers();
        System.out.println(userList);


        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Kylymbek","Sarybash",(byte) 22);
        userDao.saveUser("Aliya","Kadirbekova",(byte) 22);
        userDao.saveUser("Uson","Taalaybek",(byte) 22);
        userDao.saveUser("Jasmin","Turdubaeva",(byte) 21);

        userDao.removeUserById(2);
        //userDao.dropUsersTable();
        userDao.cleanUsersTable();


    }
}
