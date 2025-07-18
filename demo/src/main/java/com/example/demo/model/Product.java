package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data // Para ahorrar codigo @Getter @Setter
@Table(name = "productos") // Definimos el nombre de la tabla en la BD
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String category;
    private String imgUrl;
    private int stock;
}
