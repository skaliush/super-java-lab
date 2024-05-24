package ru.skaliush.superlab.server.user;

import ru.skaliush.superlab.common.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
    private final Connection connection;

    public UserManager(Connection connection) {
        this.connection = connection;
    }

    public boolean checkUserLogin(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from users where login = ?");
            ps.setString(1, user.getLogin());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String dbPassword = resultSet.getString("password");
                String salt = resultSet.getString("salt");
                return PasswordUtil.verifyPassword(user.getPassword(), salt, dbPassword);
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean register(User user) {
        try {
            PreparedStatement ps1 = connection.prepareStatement("select * from users where login = ?");
            ps1.setString(1, user.getLogin());
            ResultSet resultSet = ps1.executeQuery();
            if (resultSet.next()) {
                return false;
            }
            PreparedStatement ps2 = connection.prepareStatement("insert into users (login, password, salt) values (?, ?, ?)");
            ps2.setString(1, user.getLogin());
            String salt = PasswordUtil.generateSalt();
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword(), salt);
            ps2.setString(2, hashedPassword);
            ps2.setString(3, salt);
            ps2.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
