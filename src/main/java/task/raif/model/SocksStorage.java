package task.raif.model;

import javax.persistence.*;

@Entity
@Table (name = "socks_storage")
public class SocksStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String color;
    private int cottonPart;
    private long quantity;

    public SocksStorage() {
    }

    public SocksStorage(String color, int cottonPart, long quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
