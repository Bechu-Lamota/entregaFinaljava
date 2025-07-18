package com.example.demo.model;
import com.example.demo.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date = LocalDate.now();
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private String user;

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
        list.setOrder(this); // Importante para mantener la relacion bidireccional
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
