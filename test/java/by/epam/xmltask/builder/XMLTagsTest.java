package by.epam.xmltask.builder;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class XMLTagsTest {

    @Test
    public void testToXMLTag() {
        XMLTags expected = XMLTags.HOTEL_CHARACTERISTIC;
        String input = " hotel-characteriSTic ";
        XMLTags actual = XMLTags.toXMLTag(input);

        assertEquals(actual, expected);
    }
}