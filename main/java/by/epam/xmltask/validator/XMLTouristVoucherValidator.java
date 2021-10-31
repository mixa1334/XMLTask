package by.epam.xmltask.validator;

import by.epam.xmltask.exception.CustomXMLException;

public interface XMLTouristVoucherValidator {
    boolean validateXML(String path) throws CustomXMLException;
}
