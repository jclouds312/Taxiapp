package in.techware.ladriver.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;

import in.techware.ladriver.model.BasicBean;
import in.techware.ladriver.net.ServiceNames;
import in.techware.ladriver.net.WebConnector;
import in.techware.ladriver.net.parsers.BasicParser;
import in.techware.ladriver.net.utils.WSConstants;

public class DriverTypeRegistrationInvoker extends BaseInvoker {

    public DriverTypeRegistrationInvoker() {
        super();
    }

    public DriverTypeRegistrationInvoker(HashMap<String, String> urlParams,
                                         JSONObject postData) {
        super(urlParams, postData);
    }

    public BasicBean invokeDriverTypeRegistrationWS() {

        System.out.println("POSTDATA>>>>>>>" + postData);

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.DRIVER_TYPE_REGISTRATION), WSConstants.PROTOCOL_HTTP, null, postData);

        String wsResponseString = webConnector.connectToPOST_service();

        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        BasicBean basicBean = null;
        if (wsResponseString.equals("")) {

            return basicBean = null;
        } else {
            basicBean = new BasicBean();
            BasicParser basicParser = new BasicParser();
            basicBean = basicParser.parseBasicResponse(wsResponseString);
            return basicBean;
        }
    }
}
