package repository;

import exception.MemberNotFound;
import model.Member;
import util.DatabaseConfig;

import java.sql.*;

public class MemberRepository {
    private final DatabaseConfig dc =new DatabaseConfig();

    public void registerMember(Member member) throws MemberNotFound,SQLException {
        String sql ="insert into members(full_name, phone)  values (?,?)";
        try(Connection connection = dc.getConnection()) {


            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, member.getFullName());
            ps.setString(2, member.getPhone());
            ps.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Database member ERROR connection... "+ e.getMessage());
        }
    }

    public void findMemberById(int id) throws MemberNotFound,SQLException {
        String sql = "select * from members where id = ?";
        try(Connection connection = dc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                System.out.println("=====Members=====");
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Full name: " + resultSet.getString("full_name"));
                System.out.println("Phone: " + resultSet.getString("phone"));
            } else System.out.println("ID not found!");
        }
        catch (SQLException e){
            System.out.println("Database members ERROR connection... "+e.getMessage());
        }

    }

    public void removeMember (int id) throws MemberNotFound,SQLException {
        String sql = "delete from members where id = ?";

        try(Connection connection = dc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int row = ps.executeUpdate();
            if (row>0){
                System.out.println("Remove successful");
            }
            else System.out.println("ID not found");
        }catch (SQLException e) {

            System.out.println("Database members ERROR connection... "+ e.getMessage());
        }


    }
}
