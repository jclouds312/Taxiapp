package in.techware.ladriver.listeners;

import in.techware.ladriver.model.CommentListBean;

/**
 * Created by Jemsheer K D on 19 May, 2017.
 * Package in.techware.ladriver.listeners
 * Project LaTaxiDriver
 */

public interface CommentListListener {

    void onLoadCompleted(CommentListBean commentListBean);

    void onLoadFailed(String error);
}
