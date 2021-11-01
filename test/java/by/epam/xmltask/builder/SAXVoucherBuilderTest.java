package by.epam.xmltask.builder;

import by.epam.xmltask.entity.*;
import by.epam.xmltask.exception.CustomXMLException;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.*;

public class SAXVoucherBuilderTest {

    @Test
    public void testBuildVouchers() throws CustomXMLException {
        SAXVoucherBuilder builder = SAXVoucherBuilder.newSAXVoucherBuilder();
        builder.buildVouchersList("src/test/resources/data/positive.xml");
        List<AbstractTouristVoucher> actual = builder.getVouchers();

        EntertainmentTrip entertainmentTrip = new EntertainmentTrip();
        HotelCharacteristic hotelCharacteristic = new HotelCharacteristic();
        hotelCharacteristic.setName("Trinity");
        hotelCharacteristic.setTVPresence(true);
        hotelCharacteristic.setNumberOFStars(2);
        entertainmentTrip.setHotelCharacteristic(hotelCharacteristic);
        entertainmentTrip.setType("excursion");
        entertainmentTrip.setCoast(BigDecimal.valueOf(50.0));
        entertainmentTrip.setDate(LocalDate.parse("2021-10-31"));
        entertainmentTrip.setCountry(Country.RUSSIA);
        entertainmentTrip.setNumberOfDays(5);
        entertainmentTrip.setTouristVoucherId(1);

        BusinessTrip businessTrip = new BusinessTrip();
        HotelCharacteristic hotelCharacteristicBusiness = new HotelCharacteristic();
        hotelCharacteristicBusiness.setName("Renaissance");
        hotelCharacteristicBusiness.setTVPresence(false);
        hotelCharacteristicBusiness.setNumberOFStars(4);
        businessTrip.setHotelCharacteristic(hotelCharacteristicBusiness);
        businessTrip.setCompany("google");
        businessTrip.setCoast(BigDecimal.valueOf(240.0));
        businessTrip.setCountry(Country.CANADA);
        businessTrip.setDate(LocalDate.parse("2021-12-12"));
        businessTrip.setTouristVoucherId(2);
        businessTrip.setName("vip trip");
        businessTrip.setNumberOfDays(4);

        List<AbstractTouristVoucher> expected = List.of(entertainmentTrip, businessTrip);

        assertEquals(actual, expected);
    }
}