package by.epam.xmltask.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EntertainmentTrip extends AbstractTouristVouchers {
    private String type;

    public EntertainmentTrip(String type) {
        this.type = type;
    }

    public EntertainmentTrip(int touristVoucherId, String name, Country country, int numberOfDays, HotelCharacteristic hotelCharacteristic, BigDecimal coast, LocalDate date, String type) {
        super(touristVoucherId, name, country, numberOfDays, hotelCharacteristic, coast, date);
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        EntertainmentTrip that = (EntertainmentTrip) o;

        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EntertainmentTrip{");
        sb.append("type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}