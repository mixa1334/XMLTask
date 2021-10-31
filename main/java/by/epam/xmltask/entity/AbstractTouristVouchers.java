package by.epam.xmltask.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class AbstractTouristVouchers {
    private int touristVoucherId;
    private String name;
    private Country country;
    private int numberOfDays;
    private HotelCharacteristic hotelCharacteristic;
    private BigDecimal coast;
    private LocalDate date;

    public AbstractTouristVouchers() {
    }

    public AbstractTouristVouchers(int touristVoucherId, String name, Country country
            , int numberOfDays, HotelCharacteristic hotelCharacteristic, BigDecimal coast, LocalDate date) {
        this.touristVoucherId = touristVoucherId;
        this.name = name;
        this.country = country;
        this.numberOfDays = numberOfDays;
        this.hotelCharacteristic = hotelCharacteristic;
        this.coast = coast;
        this.date = date;
    }

    public int getTouristVoucherId() {
        return touristVoucherId;
    }

    public void setTouristVoucherId(int touristVoucherId) {
        this.touristVoucherId = touristVoucherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public HotelCharacteristic getHotelCharacteristic() {
        return hotelCharacteristic;
    }

    public void setHotelCharacteristic(HotelCharacteristic hotelCharacteristic) {
        this.hotelCharacteristic = hotelCharacteristic;
    }

    public BigDecimal getCoast() {
        return coast;
    }

    public void setCoast(BigDecimal coast) {
        this.coast = coast;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractTouristVouchers that = (AbstractTouristVouchers) o;

        if (touristVoucherId != that.touristVoucherId) return false;
        if (numberOfDays != that.numberOfDays) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (country != that.country) return false;
        if (hotelCharacteristic != null ? !hotelCharacteristic.equals(that.hotelCharacteristic) : that.hotelCharacteristic != null)
            return false;
        if (coast != null ? !coast.equals(that.coast) : that.coast != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = touristVoucherId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + numberOfDays;
        result = 31 * result + (hotelCharacteristic != null ? hotelCharacteristic.hashCode() : 0);
        result = 31 * result + (coast != null ? coast.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AbstractTouristVouchers{");
        sb.append("touristVoucherId=").append(touristVoucherId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", country=").append(country);
        sb.append(", numberOfDays=").append(numberOfDays);
        sb.append(", hotelCharacteristic=").append(hotelCharacteristic);
        sb.append(", coast=").append(coast);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
