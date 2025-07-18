package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date = LocalDate.now();
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    // OneToMany -> Crea una tabla intermedia que no controla la cascada ni la orfandad.
    // cascade -> Dice que al persistir, actualizar o eliminar el pedido lo hace entodas sus lineas asociadas.
    // orphan -> Nos dice que al elimina una linea de la lista se elimina en la BD.
    private List<OrderList> list = new ArrayList<>();

    // Calculamos el total
    public Double getTotal() {
        return list.stream().mapToDouble(OrderList::getSubtotal).sum();
    }

    // Agregamos la linea
    public void addLine(OrderList line) {
        list.add(line);
        line.setOrder(this); // bidireccionalidad
    }
}

