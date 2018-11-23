### Integration tests for WSO2 EI PostCodeLookup connector

### Pre-requisites:

 - Maven 3.x
 - Java 1.8
 - The org.wso2.esb.integration.integration-base project is required. The test suite has been configured to download this project automatically. If the automatic download fails, download the following project and compile it using the mvn clean install command to update your local repository:
   https://github.com/wso2-extensions/esb-integration-base

### Tested Platform: 

 - UBUNTU 16.04
 - WSO2 EI 6.4.0

Steps to follow in setting integration test.

 1. Download EI 6.4.0.

 2. Place the EI 6.4.0.zip file in to location "<POSTCODELOOKUP_HOME>/repository/".
    If you need to add the certificate to keystores, extract the certificate from browser(Mozilla Firefox) by navigating to postcodelookup account url and place it to {EI_Connector_Home}/repositiry/.

 3. Create postcodelookup account with https://www.alliescomputing.com/postcoder/sign-up and obtain apiKey by navigating API -> Credentials

 4. Update the PostCodeLookup properties file at location "<POSTCODELOOKUP_HOME>/src/test/resources/artifacts/ESB/connector/config" as below.
	
	    i)  apiUrl            - Endpoint URL of the API. Use http://ws.postcoder.com
	    ii) apiKey            - API Key obtained via 3.

 5. Navigate to "<POSTCODELOOKUP_HOME>/" and run the following command.<br/>
      ```$ mvn clean install -Dskip-tests=false```

		
