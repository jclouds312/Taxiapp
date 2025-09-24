package in.techware.lataxidriver.listeners;

import in.techware.lataxidriver.model.CommentListBean;

/**
 * Created by Jemsheer K D on 19 May, 2017.
 * Package in.techware.lataxidriver.listeners
 * Project LaTaxiDriver
 */

public interface CommentListListener {

    void onLoadCompleted(CommentListBean commentListBean);

    void onLoadFailed(String error);
}
