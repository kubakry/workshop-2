package pl.coderslab.entity;

public class MainDao {
    public static void main (String[] args) {

//        UserDao.generateUser ("Paro");

        String str = "";
        for (int i = 0; i < UserDao.findAll ().length; i++)
        {
            str = str + "\n" + UserDao.findAll ()[i].toString ();
        }
        System.out.println (str);


//       UserDao user = new UserDao();
//       User read = user.read(13);
//        UserDao.delete (read.getId ());


//        UserDao user = new UserDao();
//        User read = user.read(13);
//        read.setUserName ("Psikuta");
//        read.setEmail ("psikuta@fkfamily.com");
//        read.setPassword ("dgddthdshdhgdh89w87q9ehadhd3dbadtg");
//        UserDao.update (read);

//        UserDao user = new UserDao();
//        User read = user.read(15);
//        System.out.println (read);
    }
}