package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private static final String SELECT_ALL_BOOKS = "SELECT * FROM books";
    private static final String SELECT_BOOK_BY_TITLE = "SELECT * FROM books WHERE title = ?";
    private static final String SELECT_BOOK_BY_NAME = "SELECT * FROM books WHERE name = ?";
    private static final String INSERT_BOOK = "INSERT INTO books VALUES (?,?,?)";
    private static final String UPDATE_BOOK = "UPDATE books SET about=?, quantity=? WHERE name = ?";
    private static final String DELETE_BOOK = "DELETE FROM books WHERE name=?";

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = (Connection) DBConnection.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving books from the database", e);
        }
        return bookList;
    }
    public void getBook(String title) {
        try (Connection connection = (Connection) DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM book WHERE title = ?");
            ResultSet rs = ps.executeQuery("SELECT * FROM books");

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving books from the database", e);
        }
    }
    public void addbook(Book book) {
        try (Connection connection = (Connection) DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO books (title, name, price, quantity) VALUES (?, ?, ?)");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getName());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBook(int id, Book book) {
        try (Connection connection = (Connection) DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE book SET title = ?, name = ?, price = ?, quantity = ? WHERE id = ?");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getName());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4,book.getQuantity());
            ps.setInt(5, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        try (Connection connection = (Connection) DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM books WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
