package by.epam.xmltask.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BusinessTrip extends AbstractTouristVouchers {
    private String company;

    public BusinessTrip() {
    }

    public BusinessTrip(int touristVoucherId, String name, Country country, int numberOfDays, HotelCharacteristic hotelCharacteristic, BigDecimal coast, LocalDate date, String company) {
        super(touristVoucherId, name, country, numberOfDays, hotelCharacteristic, coast, date);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BusinessTrip that = (BusinessTrip) o;

        return company != null ? company.equals(that.company) : that.company == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BusinessTrip{");
        sb.append("company='").append(company).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
