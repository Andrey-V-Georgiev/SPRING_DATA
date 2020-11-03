package spring_data.game_store.domain.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private User buyer;
    private List<Game> products = new ArrayList<>();

    public Order() {
    }

    public Order(User buyer) {
        this.buyer = buyer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    public List<Game> getProducts() {
        return products;
    }

    public void setProducts(List<Game> products) {
        this.products = products;
    }
}
