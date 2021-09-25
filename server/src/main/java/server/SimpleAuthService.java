package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {
    private static Connection connection;
    private static Statement statement;
    private class UserData {
        String login;
        String password;
        String nickname;

        public UserData(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;

//            this.login = String.format("SELECT FROM users WHERE ('%s')", login);
//            this.password = String.format("SELECT FROM users WHERE ('%s')", password);
//            this.nickname = String.format("SELECT FROM users WHERE ('%s')", nickname);

        }
    }

    private List<UserData> users;

    public SimpleAuthService() {
        this.users = new ArrayList<>();

        String sql = String.format("SELECT * FROM users");
//        users.add(new UserData("qwe", "qwe", "qwe"));
//        users.add(new UserData("asd", "asd", "asd"));
//        users.add(new UserData("zxc", "zxc", "zxc"));
//        for (int i = 1; i < 10; i++) {
//            users.add(new UserData("login" + i, "pass" + i, "nick" + i));
//        }
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
//        for (UserData u : users) {
//            if (u.login.equals(login) && u.password.equals(password)) {
//                return u.nickname;
//            }
//        }
//
//
//        return null;
//    }
        String sql = String.format("SELECT nickname FROM users where login = '%s' and password = '%s'", login, password);
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:user.db");
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                String str = rs.getString(1);
                return rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        for (UserData u : users) {
            if (u.login.equals(login) || u.nickname.equals(nickname)) {
                return false;
            }
        }
        users.add(new UserData(login, password, nickname));
        return true;
    }
}
