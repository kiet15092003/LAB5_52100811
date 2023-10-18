package models;

import lombok.Data;
import lombok.Getter;
import javax.persistence.*;
@Getter
@Data
@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private int price;

    public Product() {

    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String toString(){
        return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
    }
}
