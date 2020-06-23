package edu.eci.arsw.covid19.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Casos de Covid 19 por pais
 */
public class Covid19ByCountry {

    private LocalDateTime time;
    private String country;
    private List<Stat> stats;


    /**
     * Constructor de la clase coivd19ByCountry
     * @param stats Estadisticas de las provincias o del pais
     * @param country Nombre del pais
     */
    public Covid19ByCountry(List<Stat> stats, String country) {
        this.stats = stats;
        this.country = country;
        this.time = LocalDateTime.now();
    }

    /**
     * Constructor por defecto de la clase coivd19ByCountry
     */
    public Covid19ByCountry() {
        this.time = LocalDateTime.now();
    }

    /**
     * Obtiene el momento en el cual se obtuvieron los casos de covid19
     * @return Un LocalDateTime con el momento en el cual se obtuvieron los casos de covid19
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Obtiene el pais de los casos de covid19
     * @return Una cadena con el pais de los casos de covid19
     */
    public String getCountry() {
        return country;
    }

    /**
     * Establece el pais de casos de covid19
     * @param country El nombre del pais
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Obtiene las estadisticas de los casos de covid19
     * @return Una lista con las estadisticas de los casos de covid19
     */
    public List<Stat> getStats() {
        return stats;
    }

    /**
     * Establece los casos de covid19
     * @param stats Una lista con las estadisticas de los casos de covid19
     */
    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    /**
     * Representa en forma de string del objeto Covid19ByCountry
     * @return String del objeto Covid19ByCountry
     */
    @Override
    public String toString() {
        return "country: "+country+", stats: "+stats+", time: ";
    }
}
