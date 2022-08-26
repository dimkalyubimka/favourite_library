package entity;

import enums.UserOrderStatus;

import java.util.Objects;

public class UserOrder {
    private int id;
    private User user;
    private Book book;

    public UserOrder(int id, User user, Book book, UserOrderStatus status) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrder userOrder = (UserOrder) o;
        return id == userOrder.id && Objects.equals(user, userOrder.user) && Objects.equals(book, userOrder.book) && status == userOrder.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, book, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public UserOrderStatus getStatus() {
        return status;
    }

    public void setStatus(UserOrderStatus status) {
        this.status = status;
    }

    private UserOrderStatus status;
}
