package by.epam.xmltask.factory;

import by.epam.xmltask.builder.AbstractVoucherBuilder;
import by.epam.xmltask.exception.CustomXMLException;

public interface VoucherBuilderFactory {
    AbstractVoucherBuilder createBuilder(VoucherBuilder builder) throws CustomXMLException;
}
