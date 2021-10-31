package by.epam.xmltask.entity;

import java.util.Locale;

public enum Country {
    RUSSIA,
    POLAND,
    CANADA,
    AUSTRALIA;

    public String getCountry() {
        return name();
    }

    public static Country toCountry(String str) {
        String country = str.strip().toUpperCase(Locale.ROOT);
        return Country.valueOf(country);
    }
}
