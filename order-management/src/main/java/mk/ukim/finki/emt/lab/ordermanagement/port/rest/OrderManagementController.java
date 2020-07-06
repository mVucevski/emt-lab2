package mk.ukim.finki.emt.lab.ordermanagement.port.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.emt.lab.ordermanagement.application.OrderCatalog;
import mk.ukim.finki.emt.lab.ordermanagement.application.form.OrderForm;
import mk.ukim.finki.emt.lab.ordermanagement.application.form.OrderItemForm;
import mk.ukim.finki.emt.lab.ordermanagement.application.form.RecipientAddressForm;
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

    @GetMapping
    //public ResponseEntity<?> createNewOrder(@Valid @RequestBody OrderForm orderForm, BindingResult result) {
    public String createNewOrder() {

        // Testing...

        OrderItemForm orderItemForm = new OrderItemForm();
        VideoGame vg = new VideoGame();
        vg.setId(new VideoGameId("1"));
        vg.setName("Flashlight L");
        vg.setPrice(new Money(Currency.MKD, 5642));
        vg.setQuantity(10);
        orderItemForm.setProduct(vg);
        orderItemForm.setQuantity(1);
        RecipientAddressForm addressForm = new RecipientAddressForm();
        addressForm.setAddress("dasdasdas");
        CityName cityName = new CityName("Skopje");
        addressForm.setCity(cityName);
        addressForm.setCountry(Country.MK);
        addressForm.setName("Miki");

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
        //OrderId newOrderId = orderCatalog.createOrder(orderForm1);
        //return new ResponseEntity<OrderId>(newOrderId, HttpStatus.CREATED);
    }
}
