package ru.unn.agile.vector3d.view;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ViewTest {
    @Test
    public void canAccessLayoutFXML() throws java.io.IOException {
        InputStream stream = getClass().getResourceAsStream("Layout.fxml");
        assertNotNull(stream);
    }

    @Test
    public void isLayoutFXMLWellFormedXML() {
        Document document = null;
        try {
            InputStream stream = getClass().getResourceAsStream("Layout.fxml");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance()
                                                                    .newDocumentBuilder();
            document = documentBuilder.parse(stream);
        } catch (Throwable e) {
            fail(e.toString());
        }
        assertNotNull(document);
    }
}
