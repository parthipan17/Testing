package constant;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

public class spliting {
    	
    	final static Logger logger = Logger.getLogger(spliting.class);
    	public static void main(String[] args) {
    		try {
    			File inputfile = new File("C:/Users/791729/eclipse-workspace/PJI/SampleApplication/src/main/java/constant/ISOTRAK_Src.xml");
    			File interfile = new File("C:/Users/791729/eclipse-workspace/PJI/SampleApplication/src/main/java/constant/InBoundFromIsotrak.xsl");
    			File outputfile = new File("C:/Users/791729/eclipse-workspace/PJI/SampleApplication/src/main/java/constant/JDA_testing.xml");    			    		  		    			
    			
    			StreamSource inpSource = new StreamSource(inputfile);
    			StreamSource styleSource = new StreamSource(interfile);
    			Transformer transformer = TransformerFactory.newInstance().newTransformer(styleSource);
    			transformer.transform(inpSource, new StreamResult(outputfile));
    			
    			System.out.println("Testing splititn");
    		}
    		catch (Exception e) {
    			logger.error("Error>>>", e);
    		}

    	}
}
-------------------------------XSL-------------------------------------

<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="3.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xalan="http://xml.apache.org/xslt"
xmlns:xs="http://www.w3.org/2001/XMLSchema"
xmlns:fn="http://www.w3.org/2005/xpath-functions"
xmlns:xdt="http://www.w3.org/2005/xpath-datatypes"
exclude-result-prefixes="xalan fn">

<xsl:mode streamable="yes"/>
<xsl:output method="xml"  version="1.0" encoding="UTF-8" indent="yes" xalan:indent-amount="4" />
<xsl:strip-space elements="*" />

<xsl:template match="/">
  <xsl:apply-templates select="JobExport/Jobs/Job"/>
</xsl:template>

<xsl:template match = "JobExport/Jobs/Job"> 
   <xsl:apply-templates select = "VisitsOnly" /> 
   <xsl:apply-templates select = "Visit" />  
</xsl:template>

<xsl:template match="VisitsOnly">
<xsl:variable name="vo" as="element(VisitsOnly)">
    <xsl:value-of select="."/>
    <xsl:with-param name="pasval" select="$vo" />
</xsl:variable>
</xsl:template>

<xsl:template match="Visit">
<xsl:variable name="v" as="element(Visit)">
    <xsl:copy-of select="."/>
</xsl:variable>
<xsl:variable name="voo" select="$vo" />
  
  <xsl:choose>
  <xsl:when test="$v/Actual">
    <xsl:result-document method="xml" href="{@id}.xml">
      <CISDocument>
			<ApiHeader>
				<OperationName>processLoadUpdateProgress</OperationName>
			</ApiHeader>
		<LoadUpdateProgressData>
			<SystemLoadID><xsl:value-of select="$voo/JobReference/UserReference"/></SystemLoadID>
			<CarrierCode>TXPK</CarrierCode>
			<SECInformation>
				<SECCode>ISOTRAK</SECCode>
				<EventText>Duration:<xsl:value-of select="$v/Duration"/>|ActualDuration:<xsl:value-of select="$v/Actual/Duration"/>|ActualDistance:<xsl:value-of select="$v/Actual/Distance"/>|Start:<xsl:value-of select="$v/Actual/Start"/>|End:<xsl:value-of select="$v/Actual/End"/></EventText>	
			</SECInformation>
      	</LoadUpdateProgressData>
      </CISDocument>
<!--       <xsl:copy-of select="$v"/> -->
    </xsl:result-document>
  </xsl:when>
  <xsl:otherwise>
    <xsl:result-document method="xml" href="{@id}.xml">
      <xsl:copy-of select="$v"/>
    </xsl:result-document>
  </xsl:otherwise>
</xsl:choose>
  

</xsl:template>
</xsl:stylesheet>
<!-- <xsl:template name="actual" match="Actual">
  <CISDocument>
			<ApiHeader>
				<OperationName>processLoadUpdateProgress</OperationName>
			</ApiHeader>
		<LoadUpdateProgressData>
		<xsl:for-each select="JobExport/Jobs/Job">
			<SystemLoadID>
			<xsl:for-each select="JobReference">
				<xsl:value-of select="UserReference"/>
			</xsl:for-each>
			</SystemLoadID>
			<CarrierCode>TXPK</CarrierCode>
			<SECInformation>
				<SECCode>ISOTRAK</SECCode>
				<xsl:for-each select="VisitsOnly/Visit">
				<EventText>
					Duration:<xsl:value-of select="Duration"/>|
					<xsl:for-each select="Actual"> 
					ActualDuration:<xsl:value-of select="Duration"/>|
					ActualDistance:<xsl:value-of select="Distance"/>|
					Start:<xsl:value-of select="Start"/>|
					End:<xsl:value-of select="End"/>
					</xsl:for-each>
				</EventText>				
				<MovementDateTime>
					<xsl:value-of select="Due"/>
				</MovementDateTime>
				<ShippingLocationCode>
					<xsl:for-each select="PlaceReference">
						<xsl:value-of select="UserReference"/>
					</xsl:for-each>
				</ShippingLocationCode>
				<ShippingLocationTypeEnumVal>SPT_DC</ShippingLocationTypeEnumVal>
				</xsl:for-each>
			</SECInformation>
		</xsl:for-each>
		</LoadUpdateProgressData>
		</CISDocument>
</xsl:template>


<xsl:template name="eta" match="ETA">
  		<CISDocument>
			<ApiHeader>
				<OperationName>processSetStopETA</OperationName>
			</ApiHeader>	
		<StopUpdateProgressData>
		<xsl:for-each select="JobExport/Jobs/Job">
			<CarrierCode>TXPK</CarrierCode>
			<SystemLoadID>
			<xsl:for-each select="JobReference">
				<xsl:value-of select="UserReference"/>
			</xsl:for-each>
			</SystemLoadID>
			<ShippingLocationCode>
				<xsl:for-each select="VisitsOnly/Visit/PlaceReference">
					<xsl:value-of select="UserReference"/>
				</xsl:for-each>
			</ShippingLocationCode>
			<StopEvent>
				<EventCode>RETA</EventCode>
				<xsl:for-each select="VisitsOnly/Visit">		
					<MovementDateTime><xsl:value-of select="Due"/></MovementDateTime>
					<EstimatedDateTimeAtStop><xsl:value-of select="ETA"/></EstimatedDateTimeAtStop>
					<EventText>Duration:<xsl:value-of select="Duration"/></EventText>
				</xsl:for-each>
			</StopEvent>
		</xsl:for-each>
		</StopUpdateProgressData>
		</CISDocument>
</xsl:template> -->

-----------------------------INPUT FILE------------------------------
	
<?xml version="1.0" encoding="utf-8"?>
<JobExport>
  <TransactionType>PapaJohnsJobExport-ManHattan-2013</TransactionType>
  <TransactionReference>TestExport</TransactionReference>
  <TransactionTimestamp>2019-07-01T13:39:00</TransactionTimestamp>
  <Jobs>
    <Job>
      <JobReference>
        <UserReference>616092</UserReference>
      </JobReference>
      <ActioningLocationReference>
        <UserReference>QCCNJ</UserReference>
      </ActioningLocationReference>
      <JobStatus>3</JobStatus>
      <VisitCount>14</VisitCount>
      <VisitsOnly>
		<Visit id="1">
          <Place>
            <PlaceReference>
              <UserReference>QCCNJ</UserReference>
            </PlaceReference>
          </Place>
          <Duration>0</Duration>
          <Due>2019-07-01T08:15:00</Due>
		  <Actual>
            <Updated>0</Updated>
            <Start>2019-06-30T05:29:46</Start>
            <End>2019-07-01T06:23:46</End>
            <Duration>1494</Duration>
           <Distance>656068060</Distance>
            <Angular>
              <Latitude>40.32370</Latitude>
              <Longitude>-74.50521</Longitude>
            </Angular>
            <ManualEntry>0</ManualEntry>
          </Actual>
        </Visit>
        <Visit id="2">
          <Place>
            <PlaceReference>
              <UserReference>003225</UserReference>
            </PlaceReference>
          </Place>
          <Window>
            <Earliest>2019-07-01T10:01:00</Earliest>
			<Target>2019-07-01T12:01:00</Target>
            <Latest>2019-07-01T14:01:00</Latest>
          </Window>
          <Duration>30</Duration>
          <Due>2019-07-01T12:01:00</Due>
          <Actual>
            <Updated>0</Updated>
            <Start>2019-07-01T10:13:20</Start>
            <End>2019-07-01T10:35:20</End>
            <Duration>22</Duration>
            <Distance>656402225</Distance>
			<Angular>
              <Latitude>42.12431</Latitude>
              <Longitude>-75.97161</Longitude>
            </Angular>
            <ManualEntry>0</ManualEntry>
          </Actual>
        </Visit>
        <Visit id="3">
          <Place>
            <PlaceReference>
              <UserReference>003403</UserReference>
            </PlaceReference>
          </Place>
          <Window>
            <Earliest>2019-07-01T17:26:00</Earliest>
            <Target>2019-07-01T19:26:00</Target>
            <Latest>2019-07-01T21:26:00</Latest>
          </Window>
          <Duration>35</Duration>
          <Due>2019-07-01T19:26:00</Due>
          <ETA>2019-07-01T16:51:00</ETA>
        </Visit>
        <Visit id="4">
          <Place>
            <PlaceReference>
              <UserReference>QCCNJ</UserReference>
            </PlaceReference>
          </Place>
          <Duration>0</Duration>
          <Due>2019-07-02T13:41:00</Due>
          <ETA>2019-07-02T11:06:00</ETA>
        </Visit>
      </VisitsOnly>
      <Allocations>
        <Driver>
          <ResourceReference>
            <UserReference>575202</UserReference>
          </ResourceReference>
        </Driver>
        <Driver>
          <ResourceReference>
            <UserReference>560383</UserReference>
          </ResourceReference>
        </Driver>
        <Tractor>
          <ResourceReference>
            <UserReference>133472</UserReference>
          </ResourceReference>
        </Tractor>
        <Trailer>
          <ResourceReference>
            <UserReference>683076-TL</UserReference>
          </ResourceReference>
        </Trailer>
      </Allocations>
    </Job>
  </Jobs>
</JobExport>
