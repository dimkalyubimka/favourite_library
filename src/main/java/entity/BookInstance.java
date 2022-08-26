package entity;


import java.util.Objects;

public class BookInstance {
    public BookInstance(int id, Book book) {
        this.id = id;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    private int id;

    @Override
    public String toString() {
        return "BookInstance{" +
                "id=" + id +
                ", book=" + book +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookInstance that = (BookInstance) o;
        return id == that.id && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book);
    }

    private Book book;
}