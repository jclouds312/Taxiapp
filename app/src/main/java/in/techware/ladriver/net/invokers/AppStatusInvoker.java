package in.techware.ladriver.net.invokers;

import org.json.JSONObject;

import java.util.HashMap;

import in.techware.ladriver.model.AppStatusBean;
import in.techware.ladriver.net.ServiceNames;
import in.techware.ladriver.net.WebConnector;
import in.techware.ladriver.net.parsers.AppStatusParser;
import in.techware.ladriver.net.utils.WSConstants;

/**
 * Created by Jemsheer K D on 14 June, 2017.
 * Package in.techware.ladriver.net.invokers
 * Project LaTaxiDriver
 */

public class AppStatusInvoker extends BaseInvoker {

    public AppStatusInvoker() {
        super();
    }

    public AppStatusInvoker(HashMap<String, String> urlParams,
                            JSONObject postData) {
        super(urlParams, postData);
    }

    public AppStatusBean invokeAppStatusWS() {

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.APP_STATUS), WSConstants.PROTOCOL_HTTP, urlParams, null);

        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
//    String wsResponseString=webConnector.connectToPOST_service();
        String wsResponseString = webConnector.connectToGET_service();
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        AppStatusBean appStatusBean = null;
        if (wsResponseString.equals("")) {
            /*registerBean=new RegisterBean();
            registerBean.setWebError(true);*/
            return appStatusBean = null;
        } else {
            appStatusBean = new AppStatusBean();
            AppStatusParser appStatusParser = new AppStatusParser();
            appStatusBean = appStatusParser.parseAppStatusResponse(wsResponseString);
            return appStatusBean;
        }
    }
}
