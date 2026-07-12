import exception.DatabaseRepositoryException;
import model.Book;
import model.Member;
import repository.BookRepository;
import repository.MemberRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, DatabaseRepositoryException {
        Member member =new Member("ali","09123456789");
        Member member2 =new Member("Mahdi","09123456789");
        Book book = new Book("java","Ali Najafi",2230.01,4);
        Book book2 = new Book("python","Javad",237.00,22);

        MemberRepository mr = new MemberRepository();
//        mr.registerMember(member);
//        mr.registerMember(member2);

        BookRepository br = new BookRepository();
/*        System.out.println("Book1");
        br.insertBook(book);
        System.out.println("___________________");
        System.out.println("Book2");
        br.insertBook(book2);
        System.out.println("___________________");*/
        br.deleteBook(11);
        br.findBookById(12);
        br.updateBookPrice(12,73.00);

//        mr.findMemberById(8);
//        mr.removeMember(10);

        Scanner scanner = new Scanner(System.in);
        int menu;
        do {
            System.out.println("""
                    1. Insert books.
                    2. Insert members.
                    3. Update the price of a book.
                    4. Delete a book.
                    5. Delete a member
                    6. Exit
                    """);
            System.out.print("Enter number: ");
            menu =scanner.nextInt();

            switch (menu){

                case 1:
                    System.out.print("Title: ");
                    String title = scanner.next();
                    System.out.print("Author: ");
                    String author = scanner.next();
                    System.out.print("Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("stock: ");
                    int stock = scanner.nextInt();

                    Book book1 = new Book(title,author,price,stock);
                    br.insertBook(book1);
                break;
                case 2:
                    System.out.print("Full name: ");
                    String fullName = scanner.next();
                    System.out.print("Phone: ");
                    String phone = scanner.next();
                    Member member1 = new Member(fullName,phone);

                    mr.registerMember(member1);
                break;
                case 3:
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Price: ");
                    double price1 = scanner.nextDouble();
                    br.updateBookPrice(id,price1);
                break;
                case 4:
                    System.out.print("ID: ");
                    int id1 = scanner.nextInt();
                    br.deleteBook(id1);
                break;
                case 5:
                    System.out.println("ID: ");
                    int id2 = scanner.nextInt();
                    mr.removeMember(id2);
                break;
                case 6 :
                    System.out.println("Bye");
                break;
                default:
                    System.err.println("Invalid!");
            }

        }while (menu != 6);

    }
}