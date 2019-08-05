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
  <xsl:apply-templates select="JobExport/Jobs/Job/VisitsOnly/Visit"/>
</xsl:template>

<xsl:template match="Visit">
  <xsl:variable name="v" as="element(Visit)">
    <xsl:copy-of select="."/>
  </xsl:variable>
  
  
  <xsl:choose>
  <xsl:when test="$v/Actual">
    <xsl:result-document href="{@id}.xml">
      <xsl:copy-of select="$v"/>
    </xsl:result-document>
  </xsl:when>
  <xsl:otherwise>
    <xsl:result-document href="{@id}.xml">
      <xsl:copy-of select="$v"/>
    </xsl:result-document>
  </xsl:otherwise>
</xsl:choose>
  

</xsl:template>
</xsl:stylesheet>


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
