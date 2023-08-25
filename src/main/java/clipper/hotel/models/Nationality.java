package clipper.hotel.models;

public enum Nationality {
    BRASIL("Brasil"),
    PORTUGAL("Portugal"),
    ESTADOS_UNIDOS("Estados Unidos"),
    CANADA("Canadá"),
    FRANCA("França"),
    ALEMANHA("Alemanha"),
    JAPAO("Japão"),
    CHINA("China"),
    INDIA("Índia"),
    AUSTRALIA("Austrália"),
    ARGENTINA("Argentina"),
    ITALIA("Itália"),
    ESPANHA("Espanha"),
    MEXICO("México"),
    RUSSIA("Rússia"),
    REINO_UNIDO("Reino Unido"),
    COREIA_DO_SUL("Coreia do Sul"),
    SUICA("Suíça"),
    SUECIA("Suécia"),
    HOLANDA("Holanda");

    private final String nationalityName;

    Nationality(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    public String getNationalityName() {
        return nationalityName;
    }
}
