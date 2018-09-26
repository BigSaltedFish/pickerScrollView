package io.ztc.pickerscrollview;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    /**
     * 与Activity创建关联时
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * 创建Fragment时
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    /**
     * 第一次绘制用户界面时
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         View view = initView(inflater,container,savedInstanceState);
         initListener();
         initData();
         return view;
    }

    /**
     * 初始化视图
     * @return
     */
    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState);
    /**
     * 初始化监听关系
     */
    protected abstract void initListener();
    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 当前Fragment所属Activity创建成功时
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Fragment能够被用户看到时调用
     */
    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * 获取用户焦点是时
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 失去用户焦点
     */
    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Fragment被用户完全遮挡时
     */
    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * Fragment视图被移除时
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * Fragment被销毁时调用
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * fragment与activity失去关联时
     */
    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void addBundle(Fragment fragment,OnAddBundleListener listener){
        Bundle bundle = new Bundle();
        listener.onAddBundle(bundle);
        fragment.setArguments(bundle);
    }
    public interface OnAddBundleListener{
        void onAddBundle(Bundle bundle);
    }
}
