package repository;

import exception.BookNotFound;
import exception.DatabaseConnectionException;
import exception.DatabaseRepositoryException;
import model.Book;
import util.DatabaseConfig;

import java.sql.*;

public class BookRepository {
    private final DatabaseConfig dc = new DatabaseConfig();

    public void insertBook(Book book) throws BookNotFound, DatabaseRepositoryException {
        String sql = "insert into books(title, author, price, stock)  values (?,?,?,?)";
        try(Connection connection = dc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getStock());
            ps.executeUpdate();
        }
        catch (SQLException | DatabaseConnectionException e){
            throw new DatabaseRepositoryException("Book Insertion to Database Failed!");

        }
    }

    public void findBookById (int id) throws BookNotFound, DatabaseRepositoryException {
        String sql = "select * from books where id = ?";
        try(Connection connection = dc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                System.out.println("====BOOK====");
                System.out.println("ID : " + resultSet.getInt("id"));
                System.out.println("Title : " + resultSet.getString("title"));
                System.out.println("Author : " + resultSet.getString("author"));
                System.out.println("Price : " + resultSet.getDouble("price"));
                System.out.println("Stock : " + resultSet.getDouble("stock"));
            } else System.out.println("ID not found!");
        }catch (SQLException | DatabaseConnectionException e){
            throw new DatabaseRepositoryException("Book Find By ID From Database Failed!");
        }

    }
    public void updateBookPrice (int id , double price) throws BookNotFound, DatabaseRepositoryException {
        String sql = "update books set price = ? where id = ?";
        try(Connection connection = dc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, price);
            ps.setInt(2, id);
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("Update successful");
            } else System.out.println("ID not found!");
        }
        catch (SQLException | DatabaseConnectionException e){
            throw new DatabaseRepositoryException("Update Book Price From Database Failed!");
        }
    }
    public void deleteBook(int id) throws BookNotFound, DatabaseRepositoryException {
        String sql = "delete from books where  id =?";
        try(Connection connection = dc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("Remove successful");
            } else System.out.println("Id not found!");
        }
        catch (SQLException | DatabaseConnectionException e){
            throw new DatabaseRepositoryException("Delete Book From Database Failed!");
        }
    }


}
