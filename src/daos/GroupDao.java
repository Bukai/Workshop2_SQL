package src.daos;

import src.DBUtil;
import src.models.Group;

import java.sql.*;
import java.util.Arrays;

public class GroupDao {

    private static final String CREATE_GROUP_QUERY = "INSERT INTO users_groups(name) VALUES (?)";
    private static final String READ_GROUP_QUERY = "SELECT * FROM users_groups WHERE id = ?";
    private static final String UPDATE_GROUP_QUERY = "UPDATE users_groups SET name = ? WHERE id = ?";
    private static final String DELETE_GROUP_QUERY = "DELETE FROM users_groups WHERE id = ?";
    private static final String FIND_ALL_GROUP_QUERY = "SELECT * FROM users_groups";


    public Group create(Group group) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, group.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                group.setId(resultSet.getInt(1));
            }
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Group read(int groupId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Group group) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_GROUP_QUERY);
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int groupId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_GROUP_QUERY);
            statement.setInt(1, groupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Group[] findAll() {
        try (Connection conn = DBUtil.getConnection()) {
            Group[] groups = new Group[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                groups = addToArray(group, groups);
            }
            return groups;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Group[] addToArray(Group g, Group[] users) {
        Group[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = g;
        return tmpUsers;
    }
}
