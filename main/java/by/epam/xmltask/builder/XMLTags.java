package by.epam.xmltask.builder;

public enum XMLTags {
    TOURIST_VOUCHERS,
    ID,
    NAME,
    BUSINESS_TRIP,
    ENTERTAINMENT_TRIP,
    HOTEL_CHARACTERISTIC,
    COUNTRY,
    NUMBER_OF_DAYS,
    NUMBER_OF_STARS,
    HOTEL_NAME,
    TV_PRESENCE,
    COAST,
    DATE,
    TYPE,
    COMPANY;
    private final static String HYPHEN = "-";
    private final static String UNDERSCORE = "_";

    public static XMLTags toEnum(String xmltag) {
        String tag = xmltag.strip().replaceAll(HYPHEN, UNDERSCORE).toUpperCase();
        return valueOf(tag);
    }

    public String toXMLTag() {
        return name().replaceAll(UNDERSCORE, HYPHEN).toLowerCase();
    }
}
