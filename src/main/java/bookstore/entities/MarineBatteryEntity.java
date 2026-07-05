package bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("MARINE")
public class MarineBatteryEntity extends BatteryEntity {

    private boolean deepCycle;

    public MarineBatteryEntity() {
        super();
    }

    public MarineBatteryEntity(String productId, String manufacturer, double price, int capacityAh, int voltage, boolean deepCycle) {
        super(productId, manufacturer, price, capacityAh, voltage);
        this.deepCycle = deepCycle;
    }

    public boolean isDeepCycle() { return deepCycle; }
    public void setDeepCycle(boolean deepCycle) { this.deepCycle = deepCycle; }

    @Override
    public String toString() {
        return super.toString() + " [Marine Battery | Deep Cycle: " + deepCycle + "]";
    }
}