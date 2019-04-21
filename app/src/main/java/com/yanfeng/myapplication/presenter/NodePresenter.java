package com.yanfeng.myapplication.presenter;


import com.yanfeng.myapplication.base.BasePresenter;
import com.yanfeng.myapplication.bean.V2exBean;
import com.yanfeng.myapplication.callback.ResultCallBack;
import com.yanfeng.myapplication.model.NodeModel;
import com.yanfeng.myapplication.utils.ToastUtil;
import com.yanfeng.myapplication.view.NodeView;

public class NodePresenter extends BasePresenter<NodeView> {

    private NodeModel model;

    @Override
    protected void initModel() {
        model = new NodeModel();
        mModels.add(model);
    }

    public void getNode() {
        model.getNode(new ResultCallBack<V2exBean>() {
            @Override
            public void onSuccess(V2exBean bean) {
                if (bean!=null&&bean.getData().size()>0){
                    mView.updateNode(bean.getData());
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showLong(msg);
            }

        });
    }
}
