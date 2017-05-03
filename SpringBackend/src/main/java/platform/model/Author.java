package platform.model;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Set;

/**
 * @author Wenyu
 * @since 4/30/17
 */
@Entity
@Table(name="test_author")
@Cacheable
public class Author {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="test_author_sequence")
    @SequenceGenerator(name="test_author_sequence", sequenceName="test_author_sequence", allocationSize=1)
    private Long id;

    @NotNull
    @Column(name="author_name")
    private String authorName;

    @OneToMany(orphanRemoval=true, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Set<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
