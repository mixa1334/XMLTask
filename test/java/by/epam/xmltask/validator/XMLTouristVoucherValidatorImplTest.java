package by.epam.xmltask.validator;

import by.epam.xmltask.exception.CustomXMLException;
import by.epam.xmltask.validator.impl.XMLTouristVoucherValidatorImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class XMLTouristVoucherValidatorImplTest {
    XMLTouristVoucherValidator validator = new XMLTouristVoucherValidatorImpl("src/test/resources/data/vouchers.xsd");

    @Test
    public void testValidateXMLPositive() throws CustomXMLException {
        String pathXml = "src/test/resources/data/positive.xml";
        boolean actual = validator.validateXML(pathXml);

        assertTrue(actual);
    }

    @Test
    public void testValidateXMLNegative() throws CustomXMLException {
        String pathXml = "src/test/resources/data/negative.xml";
        boolean actual = validator.validateXML(pathXml);

        assertFalse(actual);
    }

    @Test(expectedExceptions = CustomXMLException.class)
    public void testValidateXMLInvalidPath() throws CustomXMLException {
        XMLTouristVoucherValidator valid = new XMLTouristVoucherValidatorImpl("src/test/resources/data/vouchers.xsd");
        String pathXml = "src/test/resources/data/44.xml";
        valid.validateXML(pathXml);
    }
}