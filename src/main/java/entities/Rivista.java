package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "riviste")
@Getter
@Setter
@NoArgsConstructor
public class Rivista extends Readablex {
    public enum Periodicita {
        SETTIMANALE,
        MENSILE,
        SEMESTRALE
    }

    private Periodicita periodicita;

    public Rivista(String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicita=" + periodicita +
                '}';
    }
}