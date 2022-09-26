package ru.job4j.solid.srp;

import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;

public class XMLOutput implements OutputFormat{

    private StringBuilder text = new StringBuilder();
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public void giveFormat(List<Employee> employees) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(employees), writer);
            text.append(writer.getBuffer().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StringBuilder getOutputText() {
        return text;
    }
}
