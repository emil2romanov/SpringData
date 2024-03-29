package cardealer.domain.custumer;

import cardealer.domain.BaseEntity;
import cardealer.domain.sale.Sale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    //customers get additional 5% off for the sale
    @Column(name = "is_young_driver")
    private boolean youngDriver;

    @OneToMany(targetEntity = Sale.class, mappedBy = "customer")
    @Fetch(FetchMode.JOIN)
    private Set<Sale> sales;

}