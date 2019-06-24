package mavendump;

import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

public class XMLConverter {

	final static Logger logger = Logger.getLogger(XMLConverter.class);
	public static void main(String[] args) throws TransformerFactoryConfigurationError, TransformerException{

			try{
				logger.debug("PARTHIPAN LOGGER TESTING");
								
				StreamSource inpSource = new StreamSource(XMLConverter.class.getClassLoader().getResourceAsStream("Manhattan_PurchaseOrder.xml"));
				logger.debug("AFTER INPUT FILE");
				StreamSource styleSource = new StreamSource(XMLConverter.class.getClassLoader().getResourceAsStream("PurchaseOrder.xsl"));
				logger.debug("AFTER XSL FILE");
				Transformer transformer = TransformerFactory.newInstance().newTransformer(styleSource);
				logger.debug("AFTER TRANSFORM");
				transformer.transform(inpSource, new StreamResult(new File("Output\\Jda_PurchaseOrder.xml")));
				logger.debug("AFTER FIRST FILE");
				System.out.println("Testing Purchase order");
				
				}
				catch (Exception e) {
					logger.error("Error>>>", e);
				}
				
			}

}
