package by.epam.xmltask.factory;

import by.epam.xmltask.builder.AbstractVoucherBuilder;
import by.epam.xmltask.builder.DOMVoucherBuilder;
import by.epam.xmltask.builder.SAXVoucherBuilder;
import by.epam.xmltask.builder.STAXVoucherBuilder;
import by.epam.xmltask.exception.CustomXMLException;

public class VoucherBuilderFactory {
    public static AbstractVoucherBuilder createBuilder(VoucherBuilder builder) throws CustomXMLException {
        switch (builder) {
            case SAX -> {
                return SAXVoucherBuilder.newSAXVoucherBuilder();
            }
            case STAX -> {
                return new STAXVoucherBuilder();
            }
            case DOM -> {
                return new DOMVoucherBuilder();
            }
            default -> throw new CustomXMLException("cant create builder");
        }
    }
}
