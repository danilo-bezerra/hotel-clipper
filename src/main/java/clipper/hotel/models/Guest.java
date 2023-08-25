package clipper.hotel.models;



import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "guests")
public class Guest{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fName;
    private String lName;
    private LocalDate birthDate;
    private Nationality nationality;
    private String phone;

    @OneToMany(mappedBy = "guest")
    private List<Accommodation> accommodations = new ArrayList<>();

    public Guest() {

    }

    public Guest(String fName, String lName, LocalDate birthDate, Nationality nationality, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", nome='" + fName + '\'' +
                ", sobrenome='" + lName + '\'' +
                ", nascimento=" + birthDate +
                ", nacionalidade='" + nationality + '\'' +
                ", telefone='" + phone + '\'';
    }
}

