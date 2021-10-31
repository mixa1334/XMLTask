package by.epam.xmltask.validator.impl;

import by.epam.xmltask.exception.CustomXMLException;
import by.epam.xmltask.validator.XMLTouristVoucherValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLTouristVoucherValidatorImpl implements XMLTouristVoucherValidator {
    private final static Logger logger = LogManager.getLogger();
    private String schemaPath;

    public XMLTouristVoucherValidatorImpl(String schemaPath) {
        this.schemaPath = schemaPath;
    }

    public String getSchemaPath() {
        return schemaPath;
    }

    public void setSchemaPath(String schemaPath) {
        this.schemaPath = schemaPath;
    }

    @Override
    public boolean validateXML(String path) throws CustomXMLException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File pathToSchema = new File(schemaPath);
        Schema schema;
        try {
            schema = factory.newSchema(pathToSchema);
        } catch (SAXException e) {
            logger.log(Level.INFO, "invalid xsd - " + schemaPath);
            throw new CustomXMLException("invalid xsd - " + schemaPath, e);
        }

        try {
            Validator validator = schema.newValidator();
            Source pathToXMLFile = new StreamSource(path);
            validator.validate(pathToXMLFile);
            return true;
        } catch (IOException e) {
            logger.log(Level.INFO, "invalid path - " + path);
            throw new CustomXMLException("invalid path - " + path, e);
        } catch (SAXException e) {
            logger.log(Level.INFO, "invalid file - " + path);
            return false;
        }
    }
}
