package bookstore.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "batteries")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BatteryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private String productId;

    private String manufacturer;
    private double price;
    private int capacityAh;
    private int voltage;

    protected BatteryEntity() {
    }

    public BatteryEntity(String productId, String manufacturer, double price, int capacityAh, int voltage) {
        this.productId = productId;
        this.manufacturer = manufacturer;
        this.price = price;
        this.capacityAh = capacityAh;
        this.voltage = voltage;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProductId() { return productId; }
    public String getManufacturer() { return manufacturer; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getCapacityAh() { return capacityAh; }
    public int getVoltage() { return voltage; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BatteryEntity)) return false;
        BatteryEntity that = (BatteryEntity) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "Battery [id=" + id + ", product_id=" + productId + ", manufacturer=" + manufacturer + ", price=$" + price + "]";
    }
}