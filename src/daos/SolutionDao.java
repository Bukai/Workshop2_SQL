package src.daos;

import src.DBUtil;
import src.models.Solution;

import java.sql.*;
import java.util.Arrays;

public class SolutionDao {

    private static final String CREATE_SOLUTION_QUERY = "INSERT INTO solutions(created, description, exercise_id, user_id) VALUES (?, ?, ?, ?)";
    private static final String READ_SOLUTION_QUERY = "SELECT * FROM solutions WHERE id = ?";
    private static final String UPDATE_SOLUTION_QUERY = "UPDATE solutions SET updated = ?, description = ? WHERE id = ?";
    private static final String DELETE_SOLUTION_QUERY = "DELETE FROM solutions WHERE id = ?";
    private static final String FIND_ALL_SOLUTIONS_QUERY = "SELECT * FROM solutions";
    private static final String FIND_ALL_SOLUTIONS_OF_USER = "SELECT * FROM solutions WHERE user_id = ?";
    private static final String FIND_ALL_EMPTY_SOLUTION_FOR_USER = "SELECT * from solutions WHERE description = ?  and user_id = ?";


    public Solution create(Solution solution) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getDescription());
            statement.setInt(3, solution.getExercise_id());
            statement.setInt(4, solution.getUsers_id());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution read(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("user_id"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setString(1, solution.getUpdated());
            statement.setString(2, solution.getDescription());
            statement.setInt(3, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Solution[] findAll() {
        try (Connection conn = DBUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setCreated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("user_id"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Solution[] addToArray(Solution s, Solution[] solutions) {
        Solution[] tmpUsers = Arrays.copyOf(solutions, solutions.length + 1);
        tmpUsers[solutions.length] = s;
        return tmpUsers;
    }

    public Solution[] findAllUserSolution(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_OF_USER);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("user_id"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Solution[] findAllEmptySolutionsForUser(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EMPTY_SOLUTION_FOR_USER);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUsers_id(resultSet.getInt("user_id"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
