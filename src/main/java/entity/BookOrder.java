package entity;


import enums.BookOption;

import java.util.Objects;

public class BookOrder {
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookOrder bookOrder = (BookOrder) o;
        return id == bookOrder.id && Objects.equals(bookInstance, bookOrder.bookInstance) && Objects.equals(userOrder, bookOrder.userOrder) && bookOption == bookOrder.bookOption;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookInstance, userOrder, bookOption);
    }

    private BookInstance bookInstance;

    @Override
    public String toString() {
        return "BookOrder{" +
                "id=" + id +
                ", bookInstance=" + bookInstance +
                ", userOrder=" + userOrder +
                ", bookOption=" + bookOption +
                '}';
    }

    private UserOrder userOrder;

    public BookOrder(int id, BookInstance bookInstance, UserOrder userOrder, BookOption bookOption) {
        this.id = id;
        this.bookInstance = bookInstance;
        this.userOrder = userOrder;
        this.bookOption = bookOption;
    }

    private BookOption bookOption;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookInstance getBookInstance() {
        return bookInstance;
    }

    public void setBookInstance(BookInstance bookInstance) {
        this.bookInstance = bookInstance;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public BookOption getBookOption() {
        return bookOption;
    }

    public void setBookOption(BookOption bookOption) {
        this.bookOption = bookOption;
    }
}