package dto;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Sale {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Customer customer;
    @OneToMany
    private List<Product> product;

}
