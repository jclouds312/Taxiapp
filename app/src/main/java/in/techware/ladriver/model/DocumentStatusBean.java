package in.techware.ladriver.model;

import java.util.ArrayList;
import java.util.List;

import in.techware.ladriver.util.AppConstants;

/**
 * Created by Jemsheer K D on 28 April, 2017.
 * Package in.techware.ladriver.model
 * Project LaTaxiDriver
 */

public class DocumentStatusBean extends BaseBean {

    private List<DocumentBean> documents = new ArrayList<>();

    public List<DocumentBean> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentBean> documents) {
        this.documents = documents;
    }

    public boolean isAllDocumentsUploaded() {


        if (documents != null && !documents.isEmpty()) {
            for (DocumentBean bean : documents) {
                if (!bean.isUploaded() || (bean.getDocumentStatus() != AppConstants.DOCUMENT_STATUS_PENDING_APPROVAL
                        && bean.getDocumentStatus() != AppConstants.DOCUMENT_STATUS_APPROVED)) {
                    return false;
                }
            }
        }

        return true;
    }
}
