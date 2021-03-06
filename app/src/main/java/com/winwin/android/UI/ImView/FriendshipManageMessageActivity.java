package com.winwin.android.UI.ImView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tencent.imsdk.ext.sns.TIMFriendFutureItem;
import com.tencent.imsdk.ext.sns.TIMFutureFriendType;
import com.winwin.android.MVP.IM.model.FriendFuture;
import com.winwin.android.R;
import com.winwin.android.UI.Adapter.FriendManageMessageAdapter;
import com.winwin.android.im.presenter.FriendshipManagerPresenter;
import com.winwin.android.im.viewfeatures.FriendshipMessageView;

import java.util.ArrayList;
import java.util.List;

public class FriendshipManageMessageActivity extends Activity implements FriendshipMessageView {


    private final String TAG = FriendshipManageMessageActivity.class.getSimpleName();
    private FriendshipManagerPresenter presenter;
    private ListView listView;
    private List<FriendFuture> list= new ArrayList<>();
    private FriendManageMessageAdapter adapter;
    private final int FRIENDSHIP_REQ = 100;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendship_manage_message);
        listView = (ListView) findViewById(R.id.list);
        adapter = new FriendManageMessageAdapter(this, R.layout.item_two_line, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.get(position).getType() == TIMFutureFriendType.TIM_FUTURE_FRIEND_PENDENCY_IN_TYPE){    //收到的未决请求
                    index = position;
                    Intent intent = new Intent(FriendshipManageMessageActivity.this, FriendshipHandleActivity.class);
                    intent.putExtra("id", list.get(position).getIdentify());
                    intent.putExtra("word", list.get(position).getMessage());
                    startActivityForResult(intent, FRIENDSHIP_REQ);
                } else if (list.get(position).getType() == TIMFutureFriendType.TIM_FUTURE_FRIEND_DECIDE_TYPE) {     //已决好友
//                    Intent intent = new Intent(FriendshipManageMessageActivity.this, ChatActivity.class);
//                    intent.putExtra("identify", identify);
//                    intent.putExtra("type", TIMConversationType.C2C);
//                    startActivity(intent);
//                    finish();
                }

            }
        });
        presenter = new FriendshipManagerPresenter(this);
        presenter.getFriendshipMessage();
    }

    /**
     * 获取好友关系链管理最后一条系统消息的回调
     *
     * @param message     最后一条消息
     * @param unreadCount 未读数
     */
    @Override
    public void onGetFriendshipLastMessage(TIMFriendFutureItem message, long unreadCount) {

    }

    /**
     * 获取好友关系链管理最后一条系统消息的回调
     *
     * @param message 消息列表
     */
    @Override
    public void onGetFriendshipMessage(List<TIMFriendFutureItem> message) {
        if (message != null && message.size() != 0){
            for (TIMFriendFutureItem item : message){
                list.add(new FriendFuture(item));
            }
            presenter.readFriendshipMessage(message.get(0).getAddTime());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FRIENDSHIP_REQ){
            if (resultCode == RESULT_OK){
                if (index >= 0 && index < list.size()){
                    boolean isAccept = data.getBooleanExtra("operate", true);
                    if (isAccept){
                        list.get(index).setType(TIMFutureFriendType.TIM_FUTURE_FRIEND_DECIDE_TYPE);
                    }else{
                        list.remove(index);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }

    }
}
