package by.epam.xmltask.builder;

import by.epam.xmltask.entity.AbstractTouristVouchers;
import by.epam.xmltask.exception.CustomXMLException;

import java.util.HashSet;

public abstract class AbstractVoucherBuilder {
    protected HashSet<AbstractTouristVouchers> set;

    public HashSet<AbstractTouristVouchers> getVouchers() {
        return set;
    }

    public abstract void buildVouchers(String pathToFile) throws CustomXMLException;
}
