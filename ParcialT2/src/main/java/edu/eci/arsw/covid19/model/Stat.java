package edu.eci.arsw.covid19.model;

public class Stat {
    private String province;
    private int recovered;
    private int confirmed;
    private int deaths;
    private long latitude;
    private long longitude;

    /**
     * Constructor de estadisticas de covid19, en caso de que sea de un pais general su providencia es ''
     * @param province Providencia de los casos de covid19
     * @param recovered Numero de personas recuperadas del covid19
     * @param confirmed Numero de personas confirmadas con covid19
     * @param deaths Numero de personas muertas por covid19
     */
    public Stat(String province, int recovered, int confirmed, int deaths) {
        this.province = province;
        this.recovered = recovered;
        this.confirmed = confirmed;
        this.deaths = deaths;
    }

    /**
     * Obtiene la providencia de las estadisticas, si es sobre un apis el valor es ''
     * @return la providencia de las estadisticas
     */
    public String getProvince() {
        return province;
    }

    /**
     * Establece la providencia de las estadisticas del covid19, si es sobre un pais es ''
     * @param province La providencia de los casos
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Obtiene la cantidad de personas recuperadas por el covid19
     * @return la cantidad de personas recuperadas por el covid19
     */
    public int getRecovered() {
        return recovered;
    }

    /**
     * Establece la cantidad de personas recuperadas por el covid19
     * @param recovered la cantidad de personas recuperadas por el covid19
     */
    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    /**
     * Obtiene la cantidad de personas confirmadas con covid19
     * @return la cantidad de personas confirmadas con el covid19
     */
    public int getConfirmed() {
        return confirmed;
    }

    /**
     * Establece la cantidad de personas confirmadas con covid19
     * @param confirmed la cantidad de personas confirmadas con covid19
     */
    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    /**
     * Obtiene la cantidad de personas muertas por covid19
     * @return la cantidad de personas muertas por covid19
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * Establece la cantidad de personas muertas por covid19
     * @param deaths la cantidad de personas muertas por covid19
     */
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    /**
     * Obtiene la latitud de la zona analizada
     * @return la latitud de la zona analizada
     */
    public long getLatitude() {
        return latitude;
    }

    /**
     * Establece la latitud de la zona analizada
     * @param latitude la latitud de la zona analizada
     */
    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    /**
     * Obtiene la longitud de la zona analizada
     * @return la longitud de la zona analizada
     */
    public long getLongitude() {
        return longitude;
    }

    /**
     * Establece la longitud de la zona analizada
     * @param longitude la longitud de la zona analizada
     */
    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    /**
     * Representa en forma de cadena del objeto Stat
     * @return Representacion en forma de cadena del objeto Stat
     */
    @Override
    public String toString() {
        return "province=" + province +", recovered=" + recovered +", confirmed=" + confirmed + ", deaths=" + deaths +", latitude=" + latitude + ", longitude=" + longitude;
    }
}
