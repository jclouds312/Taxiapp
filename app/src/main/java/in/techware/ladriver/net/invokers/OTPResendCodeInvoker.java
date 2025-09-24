package in.techware.lataxidriver.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;

import in.techware.lataxidriver.model.BasicBean;
import in.techware.lataxidriver.net.ServiceNames;
import in.techware.lataxidriver.net.WebConnector;
import in.techware.lataxidriver.net.parsers.BasicParser;
import in.techware.lataxidriver.net.utils.WSConstants;

public class OTPResendCodeInvoker extends BaseInvoker {

    public OTPResendCodeInvoker() {
        super();
    }

    public OTPResendCodeInvoker(HashMap<String, String> urlParams,
                                JSONObject postData) {
        super(urlParams, postData);
    }

    public BasicBean invokeRegistrationWS() {

        System.out.println("POSTDATA>>>>>>>" + postData);

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.OTP_RESEND_CODE), WSConstants.PROTOCOL_HTTP, null, postData);

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
