package in.techware.ladriver.model;

import java.util.List;

/**
 * Created by Jemsheer K D on 19 May, 2017.
 * Package in.techware.ladriver.model
 * Project LaTaxiDriver
 */

public class HelpListBean extends BaseBean {

    private List<HelpBean> helpList;

    public List<HelpBean> getHelpList() {
        return helpList;
    }

    public void setHelpList(List<HelpBean> helpList) {
        this.helpList = helpList;
    }
}
