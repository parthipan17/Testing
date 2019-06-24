<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xalan="http://xml.apache.org/xslt"
xmlns:xs="http://www.w3.org/2001/XMLSchema"
xmlns:func="http://exslt.org/functions"
xmlns:date="http://exslt.org/dates-and-times"
extension-element-prefixes="date"
date:doc="http://www.exslt.org/date"
xmlns:mf="http://example.com/mf"
exclude-result-prefixes="xs mf">

<xsl:output method="xml"  version="1.0" encoding="ASCII" indent="yes" xalan:indent-amount="4" />
<xsl:strip-space elements="*" />
<xsl:template match="/">
	
		<CISDocument>
		<xsl:for-each select="tXML">
		<ApiHeader>
			<OperationName>processShipmentOrderMaintain</OperationName>
		</ApiHeader>		
		</xsl:for-each>
			<ProcessShipmentOrderCreate>
				<xsl:for-each select="tXML/Message/Order">
					<Shipment>
						<ShipmentNumber>
							<xsl:value-of select="OrderId"></xsl:value-of>
						</ShipmentNumber>
						<CustomerCode>
							<xsl:value-of select="BusinessUnit"></xsl:value-of>
						</CustomerCode>
						<ShipmentDescription>
							<xsl:value-of select="PurchaseOrderType"></xsl:value-of>
						</ShipmentDescription>
						<ShipFromLocationTypeEnumVal>STT_DC</ShipFromLocationTypeEnumVal>
						<ShipFromLocationCode>
							<xsl:value-of select="OriginFacilityAliasId"></xsl:value-of>
						</ShipFromLocationCode>											
						<ShipToLocationTypeEnumVal>STT_DC</ShipToLocationTypeEnumVal>
						<ShipToLocationCode>
							<xsl:value-of select="DestinationFacilityAliasId"></xsl:value-of>
						</ShipToLocationCode>																	
						<PickupFromDateTime>
							tESTING
							<!--<xsl:variable name="dt" select="PickupStart"/>
							<xsl:value-of select="format-dateTime($dt, '[Y0001]-[M01]-[D01]T[H01]:[m01]')" />
						
							 <xsl:value-of select="format-dateTime(xs:dateTime(PickupStart),'[D01]/[M01]/[Y0001] [H01]:[m01]:[s01]','en',(),())" />						
							<xsl:variable name="dt" select="PickupStart"/>  
							<xsl:value-of select="format-dateTime($dt, '[Y0001]-[M01]-[D01] HH:MM:SS')"/> -->  
							
						</PickupFromDateTime>						
						<PickupToDateTime>
							<xsl:value-of select="PickupEnd"></xsl:value-of>
						</PickupToDateTime>						
						<DeliveryFromDateTime>
							<xsl:value-of select="DeliveryStart"></xsl:value-of>
						</DeliveryFromDateTime>						
						<DeliveryToDateTime>
							<xsl:value-of select="DeliveryEnd"></xsl:value-of>
						</DeliveryToDateTime>						
						<CommodityCode>
							<xsl:value-of select="IsPerishable"></xsl:value-of>
						</CommodityCode>	
						<FreightTermsEnumVal>
							<xsl:value-of select="BillingMethodCode"></xsl:value-of>
						</FreightTermsEnumVal>
						<xsl:if test= "PurchaseOrderType='PO'">						
						<SystemPlanID>9900</SystemPlanID>					
						</xsl:if>						
						<xsl:if test= "PurchaseOrderType='DO'">						
						<SystemPlanID>10003</SystemPlanID>					
						</xsl:if>						
						<ExecuteAPRatingFlag>false</ExecuteAPRatingFlag>
						<ShipmentInputSourceEnumVal>IS_EXTERNAL_API</ShipmentInputSourceEnumVal>
						<HoldFlag>false</HoldFlag>
						<IgnoreContainersFlag>false</IgnoreContainersFlag>
						<ShipmentEntryModeEnumVal>TOM_ITEM_LEVEL_DETAIL</ShipmentEntryModeEnumVal>
						<Container>
							<ContainerTypeCode>ZZ</ContainerTypeCode>
							<Quantity>1</Quantity>
							<IgnoreShipmentItemsFlag>false</IgnoreShipmentItemsFlag>
							<ContainerShippingInformation>
							<xsl:for-each select="LineItem/Volume">
								<Volume>
									<xsl:value-of select="PlannedVolume"></xsl:value-of>
								</Volume>
							</xsl:for-each>
							</ContainerShippingInformation>
							<ShipmentItem>
							<xsl:for-each select="LineItem">
								<ShipmentItemNumber>
									<xsl:value-of select="LineItemId"></xsl:value-of>
								</ShipmentItemNumber>
								<ItemNumber>
									<xsl:value-of select="ItemName"></xsl:value-of>
								</ItemNumber>
								<ItemDescription>
									<xsl:value-of select="Description"></xsl:value-of>
								</ItemDescription>
								<xsl:for-each select="Quantity">
								<ItemQuantity>
									<xsl:value-of select="OrderQty"></xsl:value-of>
								</ItemQuantity>								
								</xsl:for-each>
								<xsl:for-each select="Weight">
								<NominalWeight>
									<xsl:value-of select="PlannedWeight"></xsl:value-of>
								</NominalWeight>
								</xsl:for-each>
								<ItemOrderValue>0</ItemOrderValue>
								<ItemDeclaredValue>0</ItemDeclaredValue>
								<OriginCountryCode>USA</OriginCountryCode>
								<ReferenceNumberStructure>
										<ReferenceNumberTypeCode>PRO_CLS_CODE</ReferenceNumberTypeCode>
										<ReferenceNumber>
											<xsl:value-of select="ProductClassCode"></xsl:value-of>
										</ReferenceNumber>
								</ReferenceNumberStructure>
								<ReferenceNumberStructure>
										<ReferenceNumberTypeCode>PRO_LEV_CODE</ReferenceNumberTypeCode>
										<ReferenceNumber>
											<xsl:value-of select="ProtectionLevelCode"></xsl:value-of>
										</ReferenceNumber>
								</ReferenceNumberStructure>							
								<ReferenceNumberStructure>
										<ReferenceNumberTypeCode>COM_CLS_CODE</ReferenceNumberTypeCode>
										<ReferenceNumber>
											<xsl:value-of select="CommodityClassCode"></xsl:value-of>
										</ReferenceNumber>
								</ReferenceNumberStructure>								
								</xsl:for-each>
							</ShipmentItem>							
						</Container>
					</Shipment>
					<IgnoreAllShipmentReferenceNumbersFlag>false</IgnoreAllShipmentReferenceNumbersFlag>
					<ExecuteAPRatingFlag>false</ExecuteAPRatingFlag>					
				</xsl:for-each>
			</ProcessShipmentOrderCreate>
		</CISDocument>
	</xsl:template>
</xsl:stylesheet>