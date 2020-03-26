package encobib.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "lendingPeriod")
public class LendingPeriod {

    //    Aktuelle Baustelle, bitte ignorieren! Und wehe du streichst das an^^

    //    @JsonIgnore
    //    @Transient
    //    private int bookId;
    //    @JsonIgnore
    //    @Transient
    //    private String isbn;
    //    @JsonIgnore
    //    @Transient
    //    private String title;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_book")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Book book;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "startDate", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "endDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    LendingPeriod() {
    }

    LendingPeriod(Book book, LocalDate startDate, LocalDate endDate) {
        this.book = book;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    LocalDate getStartDate() {
        return startDate;
    }

    LocalDate getEndDate() {
        return endDate;
    }

    void setBook(Book book) {
        this.book = book;
    }
}

