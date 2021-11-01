package by.epam.xmltask.builder;

import by.epam.xmltask.entity.*;
import by.epam.xmltask.exception.CustomXMLException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class STAXVoucherBuilder extends AbstractVoucherBuilder {
    private final static Logger logger = LogManager.getLogger();
    private final XMLInputFactory inputFactory;

    public STAXVoucherBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildVouchersList(String pathToFile) throws CustomXMLException {
        try (FileInputStream fileInputStream = new FileInputStream(pathToFile);) {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(fileInputStream);
            vouchers = new ArrayList<>();
            logger.log(Level.INFO, "start parsing");
            String localName;
            while (reader.hasNext()) {
                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                    localName = reader.getLocalName();
                    XMLTags tag = XMLTags.toEnum(localName);
                    if (tag == XMLTags.BUSINESS_TRIP || tag == XMLTags.ENTERTAINMENT_TRIP) {
                        AbstractTouristVoucher voucher = buildVoucher(reader, tag);
                        logger.log(Level.INFO, "voucher -> " + voucher);
                        vouchers.add(voucher);
                    }
                }
            }
            logger.log(Level.INFO, "end parsing");
        } catch (FileNotFoundException e) {
            logger.log(Level.INFO, "cant find file -> " + pathToFile);
            throw new CustomXMLException("cant find file -> " + pathToFile, e);
        } catch (IOException e) {
            logger.log(Level.INFO, "cant open file -> " + pathToFile);
            throw new CustomXMLException("cant open file -> " + pathToFile, e);
        } catch (XMLStreamException e) {
            throw new CustomXMLException(e);
        }
    }

    private AbstractTouristVoucher buildVoucher(XMLStreamReader reader, XMLTags voucherType) throws XMLStreamException, CustomXMLException {
        AbstractTouristVoucher voucher = voucherType == XMLTags.BUSINESS_TRIP ? new BusinessTrip() : new EntertainmentTrip();
        String voucherName = reader.getAttributeValue(null, XMLTags.NAME.toXMLTag());
        String voucherId = reader.getAttributeValue(null, XMLTags.ID.toXMLTag());
        voucher.setTouristVoucherId(Integer.parseInt(voucherId));
        if (voucherName != null) {
            voucher.setName(voucherName);
        }

        String localName;
        while (reader.hasNext()) {
            int elementType = reader.next();
            if (elementType == XMLStreamConstants.START_ELEMENT) {
                localName = reader.getLocalName();
                XMLTags tag = XMLTags.toEnum(localName);
                switch (tag) {
                    case COUNTRY -> {
                        Country country = Country.toCountry(tagContent(reader));
                        voucher.setCountry(country);
                    }
                    case NUMBER_OF_DAYS -> {
                        int numberOfDays = Integer.parseInt(tagContent(reader));
                        voucher.setNumberOfDays(numberOfDays);
                    }
                    case HOTEL_CHARACTERISTIC -> voucher.setHotelCharacteristic(buildHotelCharacteristic(reader));
                    case COAST -> {
                        double c = Double.parseDouble(tagContent(reader));
                        BigDecimal coast = BigDecimal.valueOf(c);
                        voucher.setCoast(coast);
                    }
                    case DATE -> {
                        LocalDate date = LocalDate.parse(tagContent(reader));
                        voucher.setDate(date);
                    }
                    case TYPE -> ((EntertainmentTrip) voucher).setType(tagContent(reader));
                    case COMPANY -> ((BusinessTrip) voucher).setCompany(tagContent(reader));
                }
            } else if (elementType == XMLStreamConstants.END_ELEMENT) {
                localName = reader.getLocalName();
                String tag = voucherType.toXMLTag();
                if (localName.equals(tag)) {
                    return voucher;
                }
            }
        }

        throw new CustomXMLException("unknown tag");
    }

    private HotelCharacteristic buildHotelCharacteristic(XMLStreamReader reader) throws XMLStreamException, CustomXMLException {
        HotelCharacteristic hotelCharacteristic = new HotelCharacteristic();

        String localName;
        while (reader.hasNext()) {
            int elementType = reader.next();
            if (elementType == XMLStreamConstants.START_ELEMENT) {
                localName = reader.getLocalName();
                XMLTags tag = XMLTags.toEnum(localName);
                switch (tag) {
                    case NUMBER_OF_STARS -> {
                        int numberOfStars = Integer.parseInt(tagContent(reader));
                        hotelCharacteristic.setNumberOFStars(numberOfStars);
                    }
                    case HOTEL_NAME -> hotelCharacteristic.setName(tagContent(reader));
                    case TV_PRESENCE -> {
                        boolean tv = Boolean.parseBoolean(tagContent(reader));
                        hotelCharacteristic.setTVPresence(tv);
                    }
                }
            } else if (elementType == XMLStreamConstants.END_ELEMENT) {
                localName = reader.getLocalName();
                String tag = XMLTags.HOTEL_CHARACTERISTIC.toXMLTag();
                if (localName.equals(tag)) {
                    return hotelCharacteristic;
                }
            }
        }

        throw new CustomXMLException("unknown tag");
    }

    private String tagContent(XMLStreamReader reader) throws XMLStreamException {
        String content = null;
        if (reader.hasNext()) {
            reader.next();
            content = reader.getText();
        }
        return content;
    }

}
