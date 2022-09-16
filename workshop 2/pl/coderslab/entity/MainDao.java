package pl.coderslab.entity;

public class MainDao {
    public static void main(String[] args) {

        UserDao user = new UserDao();

        System.out.println (UserDao.findAll ());

//       UserDao user = new UserDao();
//       User read = user.read(13);
//        UserDao.delete (read.getId ());

//        UserDao user = new UserDao();
//        User read = user.read(13);
//        read.setUserName ("Psikuta");
//        read.setEmail ("psikuta@fkfamily.com");
//        read.setPassword ("dgddthdshdhgdh89w87q9ehadhd3dbadtg");
//        UserDao.update (read);
    }
}