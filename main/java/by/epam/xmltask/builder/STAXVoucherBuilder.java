package by.epam.xmltask.builder;

import by.epam.xmltask.exception.CustomXMLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;

public class STAXVoucherBuilder extends AbstractVoucherBuilder {
    private final static Logger logger = LogManager.getLogger();
    private final XMLInputFactory inputFactory;

    public STAXVoucherBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildVouchersList(String pathToFile) throws CustomXMLException {
        
    }
}
