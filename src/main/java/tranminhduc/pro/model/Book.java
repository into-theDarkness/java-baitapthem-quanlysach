package tranminhduc.pro.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Size(min=2, max=50)
    private String name;
    private Date dateOfPurchase;
    @NotEmpty
    @Size(min=2, max=50)
    private String author;
    @Min(0)
    private Float price;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    public Book(){
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Book(Long id, String name, Date dateOfPurchase, String author, Float price,Category category){
        this.id=id;
        this.name=name;
        this.dateOfPurchase = dateOfPurchase;
        this.author = author;
        this.price=price;
        this.category = category;
    }
}
