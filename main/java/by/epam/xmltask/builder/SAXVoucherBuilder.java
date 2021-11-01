package by.epam.xmltask.builder;

import by.epam.xmltask.exception.CustomXMLException;
import by.epam.xmltask.handler.VoucherErrorHandler;
import by.epam.xmltask.handler.VoucherHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXVoucherBuilder extends AbstractVoucherBuilder {
    private final static Logger logger = LogManager.getLogger();
    private XMLReader reader;
    private VoucherHandler handler;

    private SAXVoucherBuilder() {
    }

    public static SAXVoucherBuilder newSAXVoucherBuilder() throws CustomXMLException {
        SAXVoucherBuilder builder = new SAXVoucherBuilder();
        try {
            XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            reader.setErrorHandler(new VoucherErrorHandler());
            VoucherHandler handler = new VoucherHandler();
            reader.setContentHandler(handler);
            builder.handler = handler;
            builder.reader = reader;
        } catch (SAXException | ParserConfigurationException e) {
            logger.log(Level.ERROR, "cant configure reader");
            throw new CustomXMLException("cant configure reader", e);
        }
        return builder;
    }

    @Override
    public void buildVouchers(String pathToFile) throws CustomXMLException {
        try {
            reader.parse(pathToFile);
        } catch (IOException e) {
            logger.log(Level.INFO, "invalid path - " + pathToFile);
            throw new CustomXMLException("invalid path - " + pathToFile, e);
        } catch (SAXException e) {
            throw new CustomXMLException(e);
        }
        vouchers = handler.getVouchers();
    }
}
