package by.epam.xmltask.factory.impl;

import by.epam.xmltask.builder.AbstractVoucherBuilder;
import by.epam.xmltask.builder.DOMVoucherBuilder;
import by.epam.xmltask.builder.SAXVoucherBuilder;
import by.epam.xmltask.builder.STAXVoucherBuilder;
import by.epam.xmltask.exception.CustomXMLException;
import by.epam.xmltask.factory.VoucherBuilderFactory;
import by.epam.xmltask.factory.VoucherBuilder;

public class VoucherBuilderFactoryImpl implements VoucherBuilderFactory {
    @Override
    public AbstractVoucherBuilder createBuilder(VoucherBuilder builder) throws CustomXMLException {
        switch (builder) {
            case SAX -> {
                return SAXVoucherBuilder.newSAXVoucherBuilder();
            }
            case STAX -> {
                return new STAXVoucherBuilder();
            }
            case DOM -> {
                return DOMVoucherBuilder.newDOMVoucherBuilder();
            }
            default -> throw new CustomXMLException("cant create builder");
        }
    }
}
