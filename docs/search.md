# Working with Search in Postcode Lookup

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 

The postCodeLookUp operation searches postcode or address fragment retrieves details about the postcode or address fragment.

**postCodeLookUp**
```xml
<postcodelookup.postcodelookup configKey="MyPostCodeConfig">
    <searchTerm>{$ctx:searchTerm}</searchTerm>
    <responseType>{$ctx:responseType}</responseType>
    <lines>{$ctx:lines}</lines>
    <include>{$ctx:include}</include>
    <exclude>{$ctx:exclude}</exclude>
    <format>{$ctx:format}</format>
    <addtags>{$ctx:addtags}</addtags>
    <identifier>{$ctx:identifier}</identifier>
    <callback>{$ctx:callback}</callback>
    <page>{$ctx:page}</page>
    <postcodeonly>{$ctx:postcodeonly}</postcodeonly>
</postcodelookup.postcodelookup>
```

**Properties**
* searchTerm: Required - The term being searched for, can be postcode or address fragment being searched for.
* responseType: The number of search results to return per page.
* lines: The number of lines over which to split each address.
* include: Include extra address fields within the address lines returned.
* exclude: Exclude address fields within the address lines returned.
* format: xml | json (default unless header of application/xml is detected).
* addtags: Add extra address fields to the return.
* identifier: Identify your lookups to make debugging and reviewing stats easier.
* callback: Use to specify the name of your JSONP callback function.
* page: For use with searches that return more than 100 results (first page is 0).
* postcodeonly: Use ?postcodeonly=true to limit your search to just the postcode field.


**Sample request**

Following is a sample request that can be handled by the postCodeLookUp operation.

```json
{
    "searchTerm":"NR147PZ",
    "responseType":"json",
    "lines":"3",
    "postcodeonly":"true"
}
```

**Sample response**

Given below is a sample response for the postCodeLookUp operation.

```json
[
    {
        "addressline1":"Allies Computing Ltd",
        "addressline2":"Manor Farm Barns, Fox Road",
        "addressline3":"Framingham Pigot",
        "summaryline":"Allies Computing Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ",
        "organisation":"Allies Computing Ltd",
        "buildingname":"Manor Farm Barns",
        "premise":"Manor Farm Barns",
        "street":"Fox Road",
        "dependentlocality":"Framingham Pigot",
        "posttown":"Norwich",
        "county":"Norfolk",
        "postcode":"NR14 7PZ"
    }
]
```
**Related PostCode LookUp documentation**
https://developers.alliescomputing.com/postcoder-web-api/address-lookup/

### Sample configuration

Following example illustrates how to connect to Postcode Lookup with the init operation and postCodeLookUp operation.

1. Create a sample proxy as below :

```xml
<?xmlversion="1.0"encoding="UTF-8"?>
    <proxy xmlns="http://ws.apache.org/ns/synapse" name="postCodeLookUp"
    transports="https,http"
    statistics="disable"
    trace="disable"
    startOnLoad="true">
        <target>
            <inSequence>
                <propertyexpression="json-eval($.apiKey)"name="apiKey"
                    xmlns:ns="http://org.apache.synapse/xsd"/>
                <propertyexpression="json-eval($.apiUrl)"name="apiUrl"
                    xmlns:ns="http://org.apache.synapse/xsd"/>
                <propertyexpression="json-eval($.searchTerm)"name="searchTerm"
                    xmlns:ns="http://org.apache.synapse/xsd"/>
                <propertyexpression="json-eval($.responseType)"name="responseType"
                    xmlns:ns="http://org.apache.synapse/xsd"/>
                <propertyexpression="json-eval($.lines)"name="lines"
                    xmlns:ns="http://org.apache.synapse/xsd"/>
                <propertyexpression="json-eval($.include)"name="include"
                    xmlns:ns="http://org.apache.synapse/xsd"/>
                <propertyexpression="json-eval($.exclude)"name="exclude"
                    xmlns:ns="http://org.apache.synapse/xsd"/>
                <propertyexpression="json-eval($.format)"name="format"
                    xmlns:ns="http://org.apache.synapse/xsd"/>
                <propertyexpression="json-eval($.addtags)"name="addtags"
                    xmlns:ns="http://org.apache.synapse/xsd"/>
                <propertyexpression="json-eval($.identifier)"name="identifier"
                    xmlns:ns="http://org.apache.synapse/xsd"/>
                <propertyexpression="json-eval($.callback)"name="callback"
                    xmlns:ns="http://org.apache.synapse/xsd"/>
                <propertyexpression="json-eval($.page)"name="page"
                    xmlns:ns="http://org.apache.synapse/xsd"/>
                <propertyexpression="json-eval($.postcodeonly)"name="postcodeonly"
                    xmlns:ns="http://org.apache.synapse/xsd"/>
                <postcodelookup.init>
                    <apiUrl>{$ctx:apiUrl}</apiUrl>
                    <apiKey>{$ctx:apiKey}</apiKey>
                </postcodelookup.init>
                <postcodelookup.postcodelookup>
                    <searchTerm>{$ctx:searchTerm}</searchTerm>
                    <responseType>{$ctx:responseType}</responseType>
                    <lines>{$ctx:lines}</lines>
                    <include>{$ctx:include}</include>
                    <exclude>{$ctx:exclude}</exclude>
                    <format>{$ctx:format}</format>
                    <addtags>{$ctx:addtags}</addtags>
                    <identifier>{$ctx:identifier}</identifier>
                    <callback>{$ctx:callback}</callback>
                    <page>{$ctx:page}</page>
                    <postcodeonly>{$ctx:postcodeonly}</postcodeonly>
                </postcodelookup.postcodelookup>
            <respond/>
            </inSequence>
            <outSequence>
                <send/>
            </outSequence>
        </target>
        <description/>
</proxy>
```

2. Create a json file named postCodeLookUp.json and copy the configurations given below to it:

```json
{
    "searchTerm":"NR147PZ",
    "responseType":"json",
    "lines":"3",
    "postcodeonly":"true"
}
```
3. Replace the credentials with your values.

4. Execute the following curl command:

```bash
curl http://localhost:8280/services/postCodeLookUp -H "Content-Type: application/json" -d @postCodeLookUp.json
```

5. Postcode Lookup returns a json response similar to the one shown below:
 
```json
[
    {
        "addressline1":"Allies Computing Ltd",
        "addressline2":"Manor Farm Barns, Fox Road",
        "addressline3":"Framingham Pigot",
        "summaryline":"Allies Computing Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ",
        "organisation":"Allies Computing Ltd",
        "buildingname":"Manor Farm Barns",
        "premise":"Manor Farm Barns",
        "street":"Fox Road",
        "dependentlocality":"Framingham Pigot",
        "posttown":"Norwich",
        "county":"Norfolk",
        "postcode":"NR14 7PZ"
    }
]
```
