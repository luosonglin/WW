package com.winwin.app.UI.ImView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.ext.group.TIMGroupCacheInfo;
import com.tencent.imsdk.ext.sns.TIMFriendFutureItem;
import com.winwin.app.MVP.IM.model.CustomMessage;
import com.winwin.app.MVP.IM.model.FriendshipConversation;
import com.winwin.app.MVP.IM.model.MessageFactory;
import com.winwin.app.MVP.IM.model.NomalConversation;
import com.winwin.app.R;
import com.winwin.app.UI.Adapter.ConversationAdapter;
import com.winwin.app.Util.PushUtil;
import com.winwin.app.im.model.Conversation;
import com.winwin.app.im.presenter.ConversationPresenter;
import com.winwin.app.im.presenter.FriendshipManagerPresenter;
import com.winwin.app.im.viewfeatures.ConversationView;
import com.winwin.app.im.viewfeatures.FriendshipMessageView;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ConversationActivity extends AppCompatActivity implements ConversationView, FriendshipMessageView {

    private static final String TAG = ConversationActivity.class.getSimpleName();
    private Toolbar toolbar;
    private List<Conversation> conversationList = new LinkedList<>();
    private ConversationAdapter adapter;
    private ListView listView;
    private ConversationPresenter presenter;
    private FriendshipManagerPresenter friendshipManagerPresenter;
    private FriendshipConversation friendshipConversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        toolBar();

        listView = (ListView) findViewById(R.id.list);
        adapter = new ConversationAdapter(this, R.layout.item_conversation, conversationList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                conversationList.get(position).navToDetail(ConversationActivity.this);
//                if (conversationList.get(position) instanceof GroupManageConversation) {
//                    groupManagerPresenter.getGroupManageLastMessage();
//                }
            }
        });
        friendshipManagerPresenter = new FriendshipManagerPresenter(this);
//        groupManagerPresenter = new GroupManagerPresenter(this);
        presenter = new ConversationPresenter(this);
        presenter.getConversation();
        registerForContextMenu(listView);
        adapter.notifyDataSetChanged();
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
    public void onResume(){
        super.onResume();
        refresh();
        PushUtil.getInstance().reset();
    }



    /**
     * 初始化界面或刷新界面
     *
     * @param conversationList
     */
    @Override
    public void initView(List<TIMConversation> conversationList) {
        this.conversationList.clear();
//        groupList = new ArrayList<>();
        for (TIMConversation item:conversationList){
            switch (item.getType()){
                case C2C:
//                case Group:
//                    this.conversationList.add(new NomalConversation(item));
//                    groupList.add(item.getPeer());
//                    break;
            }
        }
        friendshipManagerPresenter.getFriendshipLastMessage();
//        groupManagerPresenter.getGroupManageLastMessage();
    }

    /**
     * 更新最新消息显示
     *
     * @param message 最后一条消息
     */
    @Override
    public void updateMessage(TIMMessage message) {
        if (message == null){
            adapter.notifyDataSetChanged();
            return;
        }
//        if (message.getConversation().getType() == TIMConversationType.System){
//            groupManagerPresenter.getGroupManageLastMessage();
//            return;
//        }
        if (MessageFactory.getMessage(message) instanceof CustomMessage) return;
        NomalConversation conversation = new NomalConversation(message.getConversation());
        Iterator<Conversation> iterator =conversationList.iterator();
        while (iterator.hasNext()){
            Conversation c = iterator.next();
            if (conversation.equals(c)){
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

    /**
     * 更新好友关系链消息
     */
    @Override
    public void updateFriendshipMessage() {
        friendshipManagerPresenter.getFriendshipLastMessage();
    }

    /**
     * 删除会话
     *
     * @param identify
     */
    @Override
    public void removeConversation(String identify) {
        Iterator<Conversation> iterator = conversationList.iterator();
        while(iterator.hasNext()){
            Conversation conversation = iterator.next();
            if (conversation.getIdentify()!=null&&conversation.getIdentify().equals(identify)){
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
        for (Conversation conversation : conversationList){
            if (conversation.getIdentify()!=null && conversation.getIdentify().equals(info.getGroupInfo().getGroupId())){
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
//        if (getActivity() instanceof  HomeActivity)
//            ((HomeActivity) getActivity()).setMsgUnread(getTotalUnreadNum() == 0);
    }

    /**
     * 获取好友关系链管理系统最后一条消息的回调
     *
     * @param message 最后一条消息
     * @param unreadCount 未读数
     */
    @Override
    public void onGetFriendshipLastMessage(TIMFriendFutureItem message, long unreadCount) {
        if (friendshipConversation == null){
            friendshipConversation = new FriendshipConversation(message);
            conversationList.add(friendshipConversation);
        }else{
            friendshipConversation.setLastMessage(message);
        }
        friendshipConversation.setUnreadCount(unreadCount);
        Collections.sort(conversationList);
        refresh();
    }

    /**
     * 获取好友关系链管理最后一条系统消息的回调
     *
     * @param message 消息列表
     */
    @Override
    public void onGetFriendshipMessage(List<TIMFriendFutureItem> message) {
        friendshipManagerPresenter.getFriendshipLastMessage();
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Conversation conversation = conversationList.get(info.position);
        if (conversation instanceof NomalConversation){
            menu.add(0, 1, Menu.NONE, getString(R.string.conversation_del));
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        NomalConversation conversation = (NomalConversation) conversationList.get(info.position);
        switch (item.getItemId()) {
            case 1:
                if (conversation != null){
                    if (presenter.delConversation(conversation.getType(), conversation.getIdentify())){
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

    private long getTotalUnreadNum(){
        long num = 0;
        for (Conversation conversation : conversationList){
            num += conversation.getUnreadNum();
        }
        return num;
    }





}