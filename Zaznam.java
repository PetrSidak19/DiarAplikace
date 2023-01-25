package Diar;

import java.time.LocalDateTime;

public class Zaznam {

    private LocalDateTime datumCas;
    private String text;

    public Zaznam(LocalDateTime datumCas, String text) {
        this.datumCas = datumCas;
        this.text = text;
    }

    /**
     * return the datumCas
     */
    public LocalDateTime getDatumCas() {
        return datumCas;
    }

    /**
     * @param datumCas the datumCas to set
     */
    public void setDatumCas(LocalDateTime datumCas) {
        this.datumCas = datumCas;
    }

    /**
     * return text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return Diar.FORMAT_DATA.format(datumCas) + " " + text;
    }
}
