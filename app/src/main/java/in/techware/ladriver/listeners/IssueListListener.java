package in.techware.ladriver.listeners;

import in.techware.ladriver.model.IssueListBean;

/**
 * Created by Jemsheer K D on 19 May, 2017.
 * Package in.techware.ladriver.listeners
 * Project LaTaxiDriver
 */

public interface IssueListListener {

    void onLoadCompleted(IssueListBean issueListBean);

    void onLoadFailed(String error);

}
