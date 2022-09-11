package dto;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Sale {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Customer customer;
    @OneToMany
    private Product product;

}
