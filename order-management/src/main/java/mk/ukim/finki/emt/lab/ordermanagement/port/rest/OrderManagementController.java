package mk.ukim.finki.emt.lab.ordermanagement.port.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.emt.lab.ordermanagement.application.OrderCatalog;
import mk.ukim.finki.emt.lab.ordermanagement.application.form.OrderForm;
import mk.ukim.finki.emt.lab.ordermanagement.application.form.OrderItemForm;
import mk.ukim.finki.emt.lab.ordermanagement.application.form.RecipientAddressForm;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.VideoGame;
import mk.ukim.finki.emt.lab.ordermanagement.domain.model.VideoGameId;
import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.lab.sharedkernel.domain.geo.CityName;
import mk.ukim.finki.emt.lab.sharedkernel.domain.geo.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderManagementController {

    private final OrderCatalog orderCatalog;

    OrderManagementController(OrderCatalog orderCatalog) {
        this.orderCatalog = orderCatalog;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") String orderId) {
        return orderCatalog.findById(new OrderId(orderId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Example form:
    // {"currency":"EUR","billingAddress":{"name":"Miki","address":"dasdasdas","city":{"name":"Skopje"},"country":"MK","email":"miki@gmail.com"},"items":[{"product":{"name":"Far Cry 4","id":{"id":"1"},"price":{"currency":"EUR","amount":30},"quantity":1,"description":null,"imageURL":null},"quantity":1}]}
    @PostMapping
    public ResponseEntity<?> createNewOrder(@Valid @RequestBody OrderForm orderForm, BindingResult result) {
        OrderId newOrderId = orderCatalog.createOrder(orderForm);
        return new ResponseEntity<OrderId>(newOrderId, HttpStatus.CREATED);
    }

    @GetMapping("/create/test")
    public String createNewOrderForTesting() {

        // Testing...

        OrderItemForm orderItemForm = new OrderItemForm();
        VideoGame vg = new VideoGame();
        vg.setId(new VideoGameId("1"));
        vg.setName("Far Cry 4");
        vg.setPrice(new Money(Currency.EUR, 30));
        vg.setQuantity(1);
        orderItemForm.setProduct(vg);
        orderItemForm.setQuantity(1);
        RecipientAddressForm addressForm = new RecipientAddressForm();
        addressForm.setAddress("Nova ulica 2");
        CityName cityName = new CityName("Skopje");
        addressForm.setCity(cityName);
        addressForm.setCountry(Country.MK);
        addressForm.setName("Miki");
        addressForm.setEMail("miki@gmail.com");

        OrderForm orderForm1 = new OrderForm();
        orderForm1.setOrderItems(List.of(orderItemForm));
        orderForm1.setCurrency(Currency.EUR);
        orderForm1.setBillingAddress(addressForm);

        try {
            ObjectMapper mapper = new ObjectMapper();
            OrderId newOrderId = orderCatalog.createOrder(orderForm1);
            return mapper.writeValueAsString(orderForm1);
        }catch (JsonProcessingException exception){
            exception.printStackTrace();
        }


        return "error";
    }
}
