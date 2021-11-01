package by.epam.xmltask.builder;

import by.epam.xmltask.entity.AbstractTouristVouchers;
import by.epam.xmltask.exception.CustomXMLException;

import java.util.List;

public abstract class AbstractVoucherBuilder {
    protected List<AbstractTouristVouchers> vouchers;

    public List<AbstractTouristVouchers> getVouchers() {
        return vouchers;
    }

    public abstract void buildVouchers(String pathToFile) throws CustomXMLException;
}
