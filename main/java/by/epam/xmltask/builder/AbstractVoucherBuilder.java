package by.epam.xmltask.builder;

import by.epam.xmltask.entity.AbstractTouristVoucher;
import by.epam.xmltask.exception.CustomXMLException;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractVoucherBuilder {
    protected List<AbstractTouristVoucher> vouchers;

    public AbstractVoucherBuilder() {
        vouchers = new ArrayList<>();
    }

    public List<AbstractTouristVoucher> getVouchers() {
        return vouchers;
    }

    public abstract void buildVouchersList(String pathToFile) throws CustomXMLException;
}
