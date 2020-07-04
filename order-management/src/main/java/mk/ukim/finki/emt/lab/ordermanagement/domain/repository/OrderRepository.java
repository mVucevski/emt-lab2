package mk.ukim.finki.emt.lab.ordermanagement.domain.repository;

import mk.ukim.finki.emt.lab.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}
