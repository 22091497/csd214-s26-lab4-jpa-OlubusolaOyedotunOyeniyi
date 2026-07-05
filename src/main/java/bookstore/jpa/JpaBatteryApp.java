package bookstore.jpa;

import bookstore.entities.CarBatteryEntity;
import bookstore.entities.MarineBatteryEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class JpaBatteryApp {

    public static void main(String[] args) {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore-pu");
EntityManager em = emf.createEntityManager();
        try {
            System.out.println("--- Starting Standalone JPA Battery Lifecycle Tests ---");

    
            em.getTransaction().begin();
            CarBatteryEntity car = new CarBatteryEntity("UUID-CAR-456", "Bosch", 189.99, 75, 12, 700);
            MarineBatteryEntity marine = new MarineBatteryEntity("UUID-MAR-789", "Optima", 249.99, 95, 12, true);
            em.persist(car);
            em.persist(marine);
            em.getTransaction().commit();
            System.out.println("✅ Entities persisted successfully.");

            
            List<CarBatteryEntity> list = em.createQuery("SELECT c FROM CarBatteryEntity c", CarBatteryEntity.class).getResultList();
            for (CarBatteryEntity b : list) {
                System.out.println("Fetched from DB: " + b);
            }

            em.getTransaction().begin();
            Long targetId = car.getId();
            CarBatteryEntity managedCar = em.find(CarBatteryEntity.class, targetId);
            if (managedCar != null) {
                managedCar.setPrice(199.99); 
            }
            em.getTransaction().commit();
            System.out.println("✅ Price updated cleanly via Hibernate dirty checking.");

            em.getTransaction().begin();
            MarineBatteryEntity managedMarine = em.find(MarineBatteryEntity.class, marine.getId());
            if (managedMarine != null) {
                em.remove(managedMarine);
            }
            em.getTransaction().commit();
            System.out.println("✅ Marine battery record dropped safely from DB context.");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}