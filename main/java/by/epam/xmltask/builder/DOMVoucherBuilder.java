package by.epam.xmltask.builder;

import by.epam.xmltask.entity.*;
import by.epam.xmltask.exception.CustomXMLException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class DOMVoucherBuilder extends AbstractVoucherBuilder {
    private final static Logger logger = LogManager.getLogger();
    private DocumentBuilder documentBuilder;

    private DOMVoucherBuilder() {
    }

    public static DOMVoucherBuilder newDOMVoucherBuilder() throws CustomXMLException {
        DOMVoucherBuilder builder = new DOMVoucherBuilder();
        try {
            builder.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "cant configure doc builder");
            throw new CustomXMLException("cant configure doc builder", e);
        }
        return builder;
    }

    @Override
    public void buildVouchersList(String pathToFile) throws CustomXMLException {
        try {
            Document document = documentBuilder.parse(pathToFile);
            Element root = document.getDocumentElement();

            vouchers = new ArrayList<>();
            NodeList vouchersList;
            String businessVouchers = XMLTags.BUSINESS_TRIP.toXMLTag();
            String entertainmentVouchers = XMLTags.ENTERTAINMENT_TRIP.toXMLTag();

            vouchersList = root.getElementsByTagName(businessVouchers);
            buildList(vouchersList);
            vouchersList = root.getElementsByTagName(entertainmentVouchers);
            buildList(vouchersList);
        } catch (SAXException e) {
            throw new CustomXMLException(e);
        } catch (IOException e) {
            logger.log(Level.INFO, "invalid path -> " + pathToFile);
            throw new CustomXMLException("invalid path -> " + pathToFile, e);
        }
    }

    private void buildList(NodeList vouchersList) throws CustomXMLException {
        for (int i = 0; i < vouchers.size(); i++) {
            Element voucherElement = (Element) vouchersList.item(i);
            AbstractTouristVoucher voucher = buildVoucher(voucherElement);
            vouchers.add(voucher);
        }
    }

    private AbstractTouristVoucher buildVoucher(Element element) throws CustomXMLException {
        AbstractTouristVoucher voucher = createVoucher(element);

        String country = getTagContent(element, XMLTags.COUNTRY);
        voucher.setCountry(Country.toCountry(country));
        String numberOfDays = getTagContent(element, XMLTags.NUMBER_OF_DAYS);
        voucher.setNumberOfDays(Integer.parseInt(numberOfDays));
        String coast = getTagContent(element, XMLTags.COAST);
        BigDecimal decimalCoast = BigDecimal.valueOf(Double.parseDouble(coast));
        voucher.setCoast(decimalCoast);
        String date = getTagContent(element, XMLTags.DATE);
        voucher.setDate(LocalDate.parse(date));

        HotelCharacteristic hotelCharacteristic = buildHotelCharacteristic(element);
        voucher.setHotelCharacteristic(hotelCharacteristic);

        return voucher;
    }

    private AbstractTouristVoucher createVoucher(Element element) throws CustomXMLException {
        XMLTags currentTag = XMLTags.toEnum(element.getTagName());
        AbstractTouristVoucher voucher;

        switch (currentTag) {
            case BUSINESS_TRIP -> {
                BusinessTrip trip = new BusinessTrip();
                String company = getTagContent(element, XMLTags.COMPANY);
                trip.setCompany(company);
                voucher = trip;
            }
            case ENTERTAINMENT_TRIP -> {
                EntertainmentTrip trip = new EntertainmentTrip();
                String type = getTagContent(element, XMLTags.TYPE);
                trip.setType(type);
                voucher = trip;
            }
            default -> throw new CustomXMLException("cant create voucher");
        }

        return voucher;
    }

    private HotelCharacteristic buildHotelCharacteristic(Element element) {
        HotelCharacteristic hotelCharacteristic = new HotelCharacteristic();

        String numberOfStars = getTagContent(element, XMLTags.NUMBER_OF_STARS);
        hotelCharacteristic.setNumberOFStars(Integer.parseInt(numberOfStars));
        String hotelName = getTagContent(element, XMLTags.HOTEL_NAME);
        hotelCharacteristic.setName(hotelName);
        String tvPresence = getTagContent(element, XMLTags.TV_PRESENCE);
        hotelCharacteristic.setTVPresence(Boolean.parseBoolean(tvPresence));

        return hotelCharacteristic;
    }

    private String getTagContent(Element element, XMLTags tag) {
        NodeList list = element.getElementsByTagName(tag.toXMLTag());
        Node node = list.item(0);
        return node.getTextContent();
    }
}
