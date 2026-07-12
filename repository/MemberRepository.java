package repository;

import exception.DatabaseConnectionException;
import exception.DatabaseRepositoryException;
import exception.MemberNotFound;
import model.Member;
import util.DatabaseConfig;

import java.sql.*;

public class MemberRepository {
    private final DatabaseConfig dc =new DatabaseConfig();

    public void registerMember(Member member) throws MemberNotFound, DatabaseRepositoryException {
        String sql ="insert into members(full_name, phone)  values (?,?)";
        try(Connection connection = dc.getConnection()) {


            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, member.getFullName());
            ps.setString(2, member.getPhone());
            ps.executeUpdate();
        }
        catch (SQLException | DatabaseConnectionException e){
            throw new DatabaseRepositoryException("Member Insertion to Database Failed!");
        }
    }

    public void findMemberById(int id) throws MemberNotFound, DatabaseRepositoryException {
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
        catch (SQLException | DatabaseConnectionException e){
            throw new DatabaseRepositoryException("Member Find By ID From Database Failed!");
        }

    }

    public void removeMember (int id) throws MemberNotFound, DatabaseRepositoryException {
        String sql = "delete from members where id = ?";

        try(Connection connection = dc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int row = ps.executeUpdate();
            if (row>0){
                System.out.println("Remove successful");
            }
            else System.out.println("ID not found");
        }catch (SQLException | DatabaseConnectionException e) {

            throw new DatabaseRepositoryException("Find All Members From Database Failed!");
        }


    }
}
