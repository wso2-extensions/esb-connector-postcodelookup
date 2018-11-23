# Configuring Postcode Lookup, Operations

[[Prerequisites]](#Prerequisites) [[Initializing the Connector]](#initializing-the-connector)

## Prerequisites

To use the Postcode lookup connector, add the <postcodelookup.init> element in your configuration before carrying out any other postcode lookup operations. This will keep the API key and API URL to be used in the subsequent Postcode lookup operations.

Postcode lookup authenticates the application and authorizes access using basic authentication where the apiKey will be used to generate the authorization header. apiUrl is a optional parameter the default value is “ws.postcoder.com”.

### Using the Companies House API

* **Follow the steps below to using the Postcode Lookup API:**

    1. Create Postcode Lookup account with https://www.alliescomputing.com/postcoder/sign-up.
       Obtain API key by navigating API -> Credentials.

## Initializing the Connector

Specify the init method as follows:

**init**
```xml
<postcodelookup.init>
    <apiUrl>{$ctx:apiUrl}</apiUrl>
    <apiKey>{$ctx:apiKey}</apiKey>
</postcodelookup.init>
```
**Properties** 
* apiUrl: Refers to the application URL.
* apiKey: The  postcode lookup API key.

Now that you have connected to Postcode lookup,, use the information in the following topics to perform various operations with the connector:

[Working with Search in Postcode lookup](search.md)
