package com.winwin.android.UI.ImView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupCacheInfo;
import com.winwin.android.MVP.IM.model.ConversationUser;
import com.winwin.android.MVP.IM.model.CustomMessage;
import com.winwin.android.MVP.IM.model.MessageFactory;
import com.winwin.android.MVP.IM.model.NomalConversation;
import com.winwin.android.R;
import com.winwin.android.UI.Adapter.ConversationAdapter;
import com.winwin.android.UI.RecommendView.RecommendFragment;
import com.winwin.android.Util.PushUtil;
import com.winwin.android.im.model.Conversation;
import com.winwin.android.im.presenter.ConversationPresenter;
import com.winwin.android.im.viewfeatures.ConversationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 会话界面
 */
public class ConversationActivity extends AppCompatActivity implements ConversationView {

    private static final String TAG = ConversationActivity.class.getSimpleName();
    private Toolbar toolbar;
    private List<Conversation> conversationList = new LinkedList<>();
    private ConversationAdapter adapter;
    private ListView listView;
    private ConversationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        toolBar();

        listView = (ListView) findViewById(R.id.list);
        adapter = new ConversationAdapter(this, R.layout.item_conversation, conversationList, conversationUsers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                conversationList.get(position).navToDetail(ConversationActivity.this);
            }
        });
        presenter = new ConversationPresenter(this);
        presenter.getConversation();
        registerForContextMenu(listView);
        adapter.notifyDataSetChanged();

        RelativeLayout mChatRecyclerView = (RelativeLayout) findViewById(R.id.chat_rl);
        mChatRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConversationActivity.this, SystemMessageActivity.class));
            }
        });
    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back_image));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
        PushUtil.getInstance().reset();
    }


    //待获取用户资料的用户列表
    List<String> users = new ArrayList<String>();
    List<String> nickNames = new ArrayList<>();
    List<String> avatars = new ArrayList<>();
    List<ConversationUser> conversationUsers = new ArrayList<>();
    /**
     * 初始化界面或刷新界面
     *
     * @param conversationList
     */
    @Override
    public void initView(List<TIMConversation> conversationList) {
        this.conversationList.clear();
        for (TIMConversation item : conversationList) {
            switch (item.getType()) {
                case C2C:
                    if (!users.contains(item.getPeer())) users.add(item.getPeer());
//                    this.conversationList.add(new NomalConversation(item));
                case Group:
                    this.conversationList.add(new NomalConversation(item));
                    break;
            }
        }
        Log.e(TAG, "conversationList:");
        for (TIMConversation i : conversationList) {
            Log.e(TAG, i.getPeer());
        }

        Log.e(TAG, "users:");
        for (String i : users) {
            Log.e(TAG, i);
        }

        //获取用户资料
        TIMFriendshipManager.getInstance().getUsersProfile(users, new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int code, String desc) {
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code列表请参见错误码表
                Log.e(TAG, "getUsersProfile failed: " + code + " desc");
            }

            @Override
            public void onSuccess(List<TIMUserProfile> result) {
                Log.e(TAG, "getUsersProfile succ");
                for (TIMUserProfile res : result) {
                    Log.e(TAG, "identifier: " + res.getIdentifier()
                            + " nickName: " + res.getNickName()
                            + " avatar: " + res.getFaceUrl());
                    if (nickNames.contains(res.getNickName()))
                        nickNames.add(res.getNickName());
                    if (avatars.contains(res.getFaceUrl()))
                        avatars.add(res.getFaceUrl());

                    conversationUsers.add(new ConversationUser(res.getIdentifier(), res.getNickName(), res.getFaceUrl()));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 更新最新消息显示
     *
     * @param message 最后一条消息
     */
    @Override
    public void updateMessage(TIMMessage message) {
        if (message == null) {
            adapter.notifyDataSetChanged();
            return;
        }
        if (MessageFactory.getMessage(message) instanceof CustomMessage) return;
        NomalConversation conversation = new NomalConversation(message.getConversation());
        Iterator<Conversation> iterator = conversationList.iterator();
        while (iterator.hasNext()) {
            Conversation c = iterator.next();
            if (conversation.equals(c)) {
                conversation = (NomalConversation) c;
                iterator.remove();
                break;
            }
        }
        conversation.setLastMessage(MessageFactory.getMessage(message));
        conversationList.add(conversation);
        Collections.sort(conversationList);
        refresh();
    }

    @Override
    public void updateFriendshipMessage() {

    }

    /**
     * 删除会话
     *
     * @param identify
     */
    @Override
    public void removeConversation(String identify) {
        Iterator<Conversation> iterator = conversationList.iterator();
        while (iterator.hasNext()) {
            Conversation conversation = iterator.next();
            if (conversation.getIdentify() != null && conversation.getIdentify().equals(identify)) {
                iterator.remove();
                adapter.notifyDataSetChanged();
                return;
            }
        }
    }

    /**
     * 更新群信息
     *
     * @param info
     */
    @Override
    public void updateGroupInfo(TIMGroupCacheInfo info) {
        for (Conversation conversation : conversationList) {
            if (conversation.getIdentify() != null && conversation.getIdentify().equals(info.getGroupInfo().getGroupId())) {
                adapter.notifyDataSetChanged();
                return;
            }
        }
    }

    /**
     * 刷新
     */
    @Override
    public void refresh() {
        Collections.sort(conversationList);
        adapter.notifyDataSetChanged();
        RecommendFragment.setMsgUnread(getTotalUnreadNum() == 0);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Conversation conversation = conversationList.get(info.position);
        if (conversation instanceof NomalConversation) {
            menu.add(0, 1, Menu.NONE, getString(R.string.conversation_del));
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        NomalConversation conversation = (NomalConversation) conversationList.get(info.position);
        switch (item.getItemId()) {
            case 1:
                if (conversation != null) {
                    if (presenter.delConversation(conversation.getType(), conversation.getIdentify())) {
                        conversationList.remove(conversation);
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    private long getTotalUnreadNum() {
        long num = 0;
        for (Conversation conversation : conversationList) {
            num += conversation.getUnreadNum();
        }
        return num;
    }
}
