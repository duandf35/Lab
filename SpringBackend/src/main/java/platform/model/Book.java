package platform.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Wenyu
 * @since 4/30/17
 */
@Entity
@Table(name="test_book")
@Cacheable
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="test_book_sequence")
    @SequenceGenerator(name="test_book_sequence", sequenceName="test_book_sequence", allocationSize=1)
    private Long id;

    @NotNull
    @Column(name="book_name")
    private String bookName;

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
