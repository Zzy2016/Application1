package com.example.application1.fragments;

import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.application1.R;
import com.example.application1.base.BaseFragment;
import com.example.application1.model.HomeTimeLineModel;
import com.example.application1.model.UserInfoModel;
import com.example.application1.utils.PostAndGet;
import com.example.application1.utils.SharedPreferenceUtils;
import com.google.gson.Gson;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class Fragment3 extends Fragment {


    private String token;
    private String uid;

    private Request request;
    private Call call;
    private OkHttpClient okHttpClient;

    private String path;
    private Gson gson;
    private UserInfoModel userInfoModel;

    private TextView tvUserName;
    private TextView tvUserAdd;
    private TextView tv1, tv2, tv3;
    ImageView imageUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        userInfoModel = new UserInfoModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        token = (String) SharedPreferenceUtils.getData("assess_token", "token");
        uid = (String) SharedPreferenceUtils.getData("uid", "uid");
        path = "https://api.weibo.com/2/users/show.json?access_token=" + token + "&uid=" + uid;
        initView(view);
        return view;
    }

    public void initData() {


        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        String result = PostAndGet.doGetHttp(path);
                        userInfoModel = gson.fromJson(result, UserInfoModel.class);
                        handler.sendEmptyMessage(0);
                        Log.e("-----", result);
                    }
                }
        ).start();


//        okHttpClient = new OkHttpClient();
//        request = new Request.Builder().url(path).get().build();
//        call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("----1", e.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    String result = response.body().string();
//                    homeTimeLineModel = gson.fromJson(result, HomeTimeLineModel.class);
//
//
//                } else {
//                    Toast.makeText(getContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
//                }
//                Log.e("----0", response.body().string());
//            }
//        });
    }


    public void initView(View view) {

        tvUserName = view.findViewById(R.id.tv_user_name);
        tvUserAdd = view.findViewById(R.id.tv_user_address);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);

        imageUser=view.findViewById(R.id.image_user);
        initData();
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    tvUserName.setText(userInfoModel.getScreen_name());
                    tvUserAdd.setText(userInfoModel.getDescription());
                    tv1.setText(userInfoModel.getStatuses_count() + "");
                    tv2.setText(userInfoModel.getFriends_count() + "");
                    tv3.setText(userInfoModel.getFollowers_count() + "");
                    Glide.with(getContext()).load(userInfoModel.getProfile_image_url()).into(imageUser);
                    break;
            }
        }
    };

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initData();
        }
    }
}
