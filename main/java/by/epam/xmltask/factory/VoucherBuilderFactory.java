package by.epam.xmltask.factory;

import by.epam.xmltask.builder.AbstractVoucherBuilder;
import by.epam.xmltask.builder.DOMVoucherBuilder;
import by.epam.xmltask.builder.SAXVoucherBuilder;
import by.epam.xmltask.builder.STAXVoucherBuilder;

public class VoucherBuilderFactory {
    public static AbstractVoucherBuilder createBuilder(VoucherBuilder builder) {
        switch (builder) {
            case SAX -> {
                return new SAXVoucherBuilder();
            }
            case STAX -> {
                return new STAXVoucherBuilder();
            }
            default -> {
                return new DOMVoucherBuilder();
            }
        }
    }
}
