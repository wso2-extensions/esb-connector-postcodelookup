/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.connector.integration.test.postcodelookup;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostcodelookupConnectorIntegrationTest extends ConnectorIntegrationTestBase {

    private Map<String, String> esbRequestHeadersMap = new HashMap<String, String>();

    private Map<String, String> apiRequestHeadersMap = new HashMap<String, String>();

    /**
     * Set up the environment.
     */
    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {

        init("postcodelookup-connector-1.0.3-SNAPSHOT");

        esbRequestHeadersMap.put("Accept-Charset", "UTF-8");
        esbRequestHeadersMap.put("Content-Type", "application/json");
        esbRequestHeadersMap.put("Accept", "application/json");
        apiRequestHeadersMap.putAll(esbRequestHeadersMap);
    }

    /**
     * Positive test case for postcodelookup method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description =
            "postcodelookup integration test with mandatory" + " parameters.")
    public void testPostcodelookupWithMandatoryParameters() throws IOException, JSONException {

        esbRequestHeadersMap.put("Action", "urn:postcodelookup");

        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_postcodelookup_mandatory.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/pcw/"
                + connectorProperties.getProperty("apiKey")
                + "/address/uk/" + connectorProperties.getProperty("searchTerm");

        RestResponse<JSONObject> apiRestResponse =
                sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(apiRestResponse.getBody().getString("output"), esbRestResponse.getBody().getString("output"));
    }

    /**
     * Positive test case for postcodelookup method with optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "postcodelookup integration test with optional parameters.")
    public void testPostcodelookupWithOptionalParameters() throws IOException, JSONException {

        esbRequestHeadersMap.put("Action", "urn:postcodelookup");

        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_postcodelookup_optional.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/pcw/"
                + connectorProperties.getProperty("apiKey") + "/address/uk/"
                + connectorProperties.getProperty("searchTerm") + "?responseType="
                + connectorProperties.getProperty("responseType") + "&lines="
                + connectorProperties.getProperty("lines") + "&postcodeonly="
                + connectorProperties.getProperty("postcodeonly");

        RestResponse<JSONObject> apiRestResponse =
                sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(apiRestResponse.getBody().getString("output"), esbRestResponse.getBody().getString("output"));
    }

    /**
     * Negative test case for postcodelookup method.
     */
    @Test(groups = {"wso2.esb"}, description = "postcodelookup integration test with negative case.")
    public void testPostcodelookupWithNegativeCase() throws IOException, JSONException {

        esbRequestHeadersMap.put("Action", "urn:postcodelookup");

        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_postcodelookup_negative.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/pcw/"
                + connectorProperties.getProperty("apiKey") + "/address/uk/" + "INVALID";

        RestResponse<JSONObject> apiRestResponse =
                sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);

        Assert.assertEquals(apiRestResponse.getBody().getString("output"), esbRestResponse.getBody().getString("output"));
    }
}