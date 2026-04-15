package lab.bookings.repositories;

import lab.bookings.models.Apartment;
import org.springframework.data.repository.CrudRepository;

public interface ApartmentRepository
        extends CrudRepository<Apartment, Long> {
}
