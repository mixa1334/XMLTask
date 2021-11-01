package by.epam.xmltask.handler;

import by.epam.xmltask.builder.XMLTags;
import by.epam.xmltask.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class VoucherHandler extends DefaultHandler {
    private final static Logger logger = LogManager.getLogger();
    private ArrayList<AbstractTouristVouchers> vouchersList;
    private AbstractTouristVouchers voucher;
    private HotelCharacteristic hotelCharacteristic;
    private XMLTags currentTag;

    public ArrayList<AbstractTouristVouchers> getVouchers() {
        return vouchersList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTag = XMLTags.toXMLTag(qName);
        logger.log(Level.INFO, "current tag -> " + currentTag);

        if (currentTag.equals(XMLTags.TOURIST_VOUCHERS)) {
            logger.log(Level.INFO, "new list of vouchers");
            vouchersList = new ArrayList<>();
            currentTag = null;
        } else if (currentTag.equals(XMLTags.BUSINESS_TRIP) || currentTag.equals(XMLTags.ENTERTAINMENT_TRIP)) {
            voucher = currentTag.equals(XMLTags.BUSINESS_TRIP) ? new BusinessTrip() : new EntertainmentTrip();
            for (int i = 0; i < attributes.getLength(); i++) {
                String value = attributes.getValue(i);
                String name = attributes.getQName(i);
                XMLTags voucherAttribute = XMLTags.toXMLTag(name);
                switch (voucherAttribute) {
                    case ID -> voucher.setTouristVoucherId(Integer.parseInt(value));
                    case NAME -> voucher.setName(value);
                }
            }
            currentTag = null;
        } else if (currentTag.equals(XMLTags.HOTEL_CHARACTERISTIC)) {
            hotelCharacteristic = new HotelCharacteristic();
            currentTag = null;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        XMLTags tag = XMLTags.toXMLTag(qName);
        if (tag.equals(XMLTags.BUSINESS_TRIP) || tag.equals(XMLTags.ENTERTAINMENT_TRIP)) {
            logger.log(Level.INFO, "voucher added to list -> " + voucher);
            vouchersList.add(voucher);
        } else if (tag.equals(XMLTags.HOTEL_CHARACTERISTIC)) {
            logger.log(Level.INFO, "hotel characteristic added  -> " + hotelCharacteristic);
            voucher.setHotelCharacteristic(hotelCharacteristic);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentTag != null) {
            String content = new String(ch, start, length);
            logger.log(Level.INFO, "content of tag -> " + content);
            switch (currentTag) {
                case COUNTRY -> voucher.setCountry(Country.toCountry(content));
                case NUMBER_OF_DAYS -> voucher.setNumberOfDays(Integer.parseInt(content));
                case NUMBER_OF_STARS -> hotelCharacteristic.setNumberOFStars(Integer.parseInt(content));
                case HOTEL_NAME -> hotelCharacteristic.setName(content);
                case TV_PRESENCE -> hotelCharacteristic.setTVPresence(Boolean.parseBoolean(content));
                case COAST -> voucher.setCoast(BigDecimal.valueOf(Double.parseDouble(content)));
                case DATE -> voucher.setDate(LocalDate.parse(content));
                case TYPE -> ((EntertainmentTrip) voucher).setType(content);
                case COMPANY -> ((BusinessTrip) voucher).setCompany(content);
            }
            currentTag = null;
        }
    }
}
