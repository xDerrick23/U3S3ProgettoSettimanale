package entities;

import javax.persistence.*;

import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public class Readablex {
    @Id
    @GeneratedValue
    private Long isbn;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;

    @OneToOne(mappedBy = "elementoPrestato")
    private Prestito prestito;

    public Readablex(String titolo, int annoPubblicazione, int numeroPagine) {
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }
}