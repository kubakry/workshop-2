package pl.coderslab.entity;

public class Main01 {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User read = userDao.read(1);
        System.out.println(read);


        User user = new User();
        user.setUserName ("Śmipa");
        user.setEmail ("smipa@fkfamily.com");
        user.setPassword ("dgdtbdtg");
        userDao.create(user);
    }
}