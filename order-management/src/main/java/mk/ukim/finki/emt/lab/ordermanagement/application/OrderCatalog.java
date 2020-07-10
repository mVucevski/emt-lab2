package mk.ukim.finki.emt.lab.ordermanagement.application;

import mk.ukim.finki.emt.lab.ordermanagement.application.form.OrderForm;
import mk.ukim.finki.emt.lab.ordermanagement.application.form.RecipientAddressForm;
import mk.ukim.finki.emt.lab.ordermanagement.domain.event.OrderItemAdded;
import mk.ukim.finki.emt.lab.ordermanagement.domain.event.OrderCreated;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.*;
import mk.ukim.finki.emt.lab.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.lab.ordermanagement.integration.GameKeyAddedToOrderEvent;
import mk.ukim.finki.emt.lab.sharedkernel.domain.geo.RecipientAddress;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OrderCatalog {

    private final OrderRepository orderRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final Validator validator;

    private VideoGameCatalog productCatalog;

    public OrderCatalog(OrderRepository orderRepository,
                        VideoGameCatalog productCatalog,
                        Validator validator,
                        ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.validator = validator;
        this.productCatalog = productCatalog;
    }

    public OrderId createOrder(@NonNull OrderForm order) {
        Objects.requireNonNull(order,"order must not be null");
        var constraintViolations = validator.validate(order);

        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The OrderForm is not valid", constraintViolations);
        }

        var newOrder = orderRepository.saveAndFlush(toDomainModel(order));
        applicationEventPublisher.publishEvent(new OrderCreated(newOrder.id(),newOrder.getOrderedOn()));
        newOrder.getItems().forEach(orderItem -> applicationEventPublisher.publishEvent(new OrderItemAdded(newOrder.id(),orderItem.id(),orderItem.getVideoGameId(),orderItem.getQuantity(), Instant.now())));
        return newOrder.id();
    }

    @NonNull
    public Optional<Order> findById(@NonNull OrderId orderId) {
        Objects.requireNonNull(orderId, "orderId must not be null");
        return orderRepository.findById(orderId);
    }

    @NonNull
    private Order toDomainModel(@NonNull OrderForm orderForm) {
        var order = new Order(Instant.now(), orderForm.getCurrency(),
                toDomainModel(orderForm.getBillingAddress()));
        orderForm.getItems().forEach(item -> order.addItem(item.getProduct(), item.getQuantity()));
        return order;
    }

    @NonNull
    private RecipientAddress toDomainModel(@NonNull RecipientAddressForm form) {
        return new RecipientAddress(form.getName(), form.getAddress(),form.getCity(), form.getCountry());
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onGameKeyAddedToOrderEvent(GameKeyAddedToOrderEvent event) {

        System.out.println("onGameKeyAddedToOrderEvent - RECEIVED");

        Order order = orderRepository.findById(event.getOrderId()).orElseThrow(RuntimeException::new);
        OrderItem orderItem = order.getItems().filter(e->e.getId()==event.getOrderItemId()).findFirst().orElseThrow(RuntimeException::new);

        orderItem.setGameKeyId(event.getGameKeyId());
        order.setState(OrderState.RECEIVED);
        orderRepository.saveAndFlush(order);
    }

}
