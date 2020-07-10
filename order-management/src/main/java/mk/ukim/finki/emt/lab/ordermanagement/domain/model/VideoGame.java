package mk.ukim.finki.emt.lab.ordermanagement.domain.model;

import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.emt.lab.sharedkernel.domain.financial.Money;

@Getter
//Setter to be removed
@Setter
public class VideoGame {

    private String name;

    private VideoGameId id;

    private Money price;

    private int quantity;

    private String description;

    private String imageURL;
}
