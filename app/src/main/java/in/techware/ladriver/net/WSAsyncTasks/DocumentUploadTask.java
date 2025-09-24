package in.techware.ladriver.net.WSAsyncTasks;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.List;

import in.techware.ladriver.model.BasicBean;
import in.techware.ladriver.net.invokers.DocumentUploadInvoker;

public class DocumentUploadTask extends AsyncTask<String, Integer, BasicBean> {

    private final List<String> fileList;
    private JSONObject postData;

    private DocumentUploadTaskListener documentUploadTaskListener;

    public DocumentUploadTask(JSONObject postData, List<String> fileList) {
        super();
        this.postData = postData;
        this.fileList = fileList;
    }

    @Override
    protected BasicBean doInBackground(String... params) {

        System.out.println(">>>>>>>>>doInBackground");
        DocumentUploadInvoker documentUploadInvoker = new DocumentUploadInvoker(null, postData);
        return documentUploadInvoker.invokeDocumentUploadWS(fileList);

    }

    @Override
    protected void onPostExecute(BasicBean result) {
        super.onPostExecute(result);

        if (result != null)
            documentUploadTaskListener.dataDownloadedSuccessfully(result);
        else
            documentUploadTaskListener.dataDownloadFailed();
    }

    public interface DocumentUploadTaskListener {

        void dataDownloadedSuccessfully(BasicBean basicBean);

        void dataDownloadFailed();
    }

    public DocumentUploadTaskListener getDocumentUploadTaskListener() {
        return documentUploadTaskListener;
    }

    public void setDocumentUploadTaskListener(DocumentUploadTaskListener documentUploadTaskListener) {
        this.documentUploadTaskListener = documentUploadTaskListener;
    }
}
