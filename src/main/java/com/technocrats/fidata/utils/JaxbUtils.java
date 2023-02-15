package com.technocrats.fidata.utils;


import com.technocrats.fidata.types.Account;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;

public class JaxbUtils {

//    public static void marshal(Account,String xmlStr)
//            throws IOException, JAXBException {
//        JAXBContext context;
//        BufferedWriter writer = null;
//        writer = new BufferedWriter(new FileWriter(selectedFile));
//        context = JAXBContext.newInstance(Employee.class);
//        Marshaller m = context.createMarshaller();
//        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        m.marshal(employee, writer);
//        writer.close();
//    }

    // Import: Unmarshalling
    public static Account unmarshal(String xmlStr) throws JAXBException {
        Account account = null;
        JAXBContext context;
        context = JAXBContext.newInstance(Account.class);
        Unmarshaller um = context.createUnmarshaller();
        account = (Account) um.unmarshal(new StringReader(xmlStr));
        return account;
    }
}
