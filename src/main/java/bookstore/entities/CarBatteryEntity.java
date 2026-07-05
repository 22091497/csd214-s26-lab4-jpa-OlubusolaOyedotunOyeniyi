package bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("CAR")
public class CarBatteryEntity extends BatteryEntity {

    private int coldCrankingAmps;

    public CarBatteryEntity() {
        super();
    }

    public CarBatteryEntity(String productId, String manufacturer, double price, int capacityAh, int voltage, int coldCrankingAmps) {
        super(productId, manufacturer, price, capacityAh, voltage);
        this.coldCrankingAmps = coldCrankingAmps;
    }

    public int getColdCrankingAmps() { return coldCrankingAmps; }
    public void setColdCrankingAmps(int coldCrankingAmps) { this.coldCrankingAmps = coldCrankingAmps; }

    @Override
    public String toString() {
        return super.toString() + " [Car Battery | CCA: " + coldCrankingAmps + "]";
    }
}