package product.ma.resultservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentId; // ID de l'étudiant

    @Column(nullable = false)
    private String courseId; // ID du cours

    @Column(nullable = false)
    private double grade; // Note de l'étudiant

    @Column(nullable = false)
    private String remarks; // Remarques (par exemple, "Excellent", "Passable")
}