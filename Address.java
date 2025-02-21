public class Address {
    private String provinceMunicipality;
    private String district;
    private String commune;
    private String quartier;
    private String streetNumber;
    private String homeNumber;
    private static int totalAddress = 0;

    // Constructor
    public Address(String province, String district, String commune, String quartier, String streetNumber, String homeNumber) {
        this.provinceMunicipality = province;
        this.district = district;
        this.commune = commune;
        this.quartier = quartier;
        this.streetNumber = streetNumber;
        this.homeNumber = homeNumber;
        this.totalAddress++;
    }

    // Getters (Allow controlled access to private fields)
    public String getProvinceMunicipality() { return provinceMunicipality; }
    public String getDistrict() { return district; }
    public String getCommune() { return commune; }
    public String getQuartier() { return quartier; }
    public String getStreetNumber() { return streetNumber; }
    public String getHomeNumber() { return homeNumber; }

    // Setters (Allow modification in a controlled way)
    public void setProvinceMunicipality(String provinceMunicipality) { this.provinceMunicipality = provinceMunicipality; }
    public void setDistrict(String district) { this.district = district; }
    public void setCommune(String commune) { this.commune = commune; }
    public void setQuartier(String quartier) { this.quartier = quartier; }
    public void setStreetNumber(String streetNumber) { this.streetNumber = streetNumber; }
    public void setHomeNumber(String homeNumber) { this.homeNumber = homeNumber; }
}


