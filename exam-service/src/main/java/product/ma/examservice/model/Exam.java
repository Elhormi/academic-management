package product.ma.examservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "exams")
public class Exam {
    @Id
    private String id;

    private String name; // Nom de l'examen
    private String date; // Date de l'examen
    private String room; // Salle de l'examen
    private List<String> supervisors; // Liste des surveillants
    private List<String> examPapers; // Liste des copies d'examen (par exemple, des IDs de fichiers)
}