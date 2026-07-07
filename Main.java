import model.Book;
import model.Member;
import repository.BookRepository;
import repository.MemberRepository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
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




    }
}