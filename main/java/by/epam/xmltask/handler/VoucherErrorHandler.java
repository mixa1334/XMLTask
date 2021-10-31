package by.epam.xmltask.handler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class VoucherErrorHandler implements ErrorHandler {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        logger.log(Level.WARN, getLineInfo(exception) + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        logger.log(Level.ERROR, getLineInfo(exception) + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        logger.log(Level.FATAL, getLineInfo(exception) + exception.getMessage());
    }

    private String getLineInfo(SAXParseException exception) {
        return exception.getLineNumber() + " line, " + exception.getColumnNumber() + " column -> ";
    }
}
