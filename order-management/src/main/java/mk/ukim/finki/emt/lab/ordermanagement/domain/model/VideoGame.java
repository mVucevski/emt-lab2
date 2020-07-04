package mk.ukim.finki.emt.lab.ordermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Money;

@Getter
public class VideoGame {

    private String name;

    private VideoGameId id;

    private Money price;

    private int quantity;
}
