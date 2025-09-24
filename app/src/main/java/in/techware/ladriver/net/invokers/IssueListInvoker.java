package in.techware.lataxidriver.net.invokers;

import org.json.JSONObject;

import java.util.HashMap;

import in.techware.lataxidriver.model.IssueListBean;
import in.techware.lataxidriver.net.ServiceNames;
import in.techware.lataxidriver.net.WebConnector;
import in.techware.lataxidriver.net.parsers.IssueListParser;
import in.techware.lataxidriver.net.utils.WSConstants;

/**
 * Created by Jemsheer K D on 19 May, 2017.
 * Package in.techware.lataxidriver.net.invokers
 * Project LaTaxiDriver
 */

public class IssueListInvoker extends BaseInvoker {

    public IssueListInvoker() {
        super();
    }

    public IssueListInvoker(HashMap<String, String> urlParams,
                            JSONObject postData) {
        super(urlParams, postData);
    }

    public IssueListBean invokeIssueListWS() {

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.ISSUE_LIST),
                WSConstants.PROTOCOL_HTTP, urlParams, null);

        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
//    String wsResponseString=webConnector.connectToPOST_service();
        String wsResponseString = webConnector.connectToGET_service();
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        IssueListBean issueListBean = null;
        if (wsResponseString.equals("")) {
            /*registerBean=new RegisterBean();
            registerBean.setWebError(true);*/
            return issueListBean = null;
        } else {
            issueListBean = new IssueListBean();
            IssueListParser issueListParser = new IssueListParser();
            issueListBean = issueListParser.parseIssueListResponse(wsResponseString);
            return issueListBean;
        }
    }
}
