package com.winwin.android.im.presenter;

import android.util.Log;

import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.message.TIMConversationExt;
import com.tencent.imsdk.ext.message.TIMManagerExt;
import com.winwin.android.im.event.MessageEvent;
import com.winwin.android.im.event.RefreshEvent;
import com.winwin.android.im.viewfeatures.ConversationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 会话界面逻辑
 */
public class ConversationPresenter implements Observer {

    private static final String TAG = "ConversationPresenter";
    private ConversationView view;

    public ConversationPresenter(ConversationView view) {
        //注册消息监听
        MessageEvent.getInstance().addObserver(this);
        //注册刷新监听
        RefreshEvent.getInstance().addObserver(this);
        //注册好友关系链监听
//        FriendshipEvent.getInstance().addObserver(this);
        //注册群关系监听
//        GroupEvent.getInstance().addObserver(this);
        this.view = view;
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof MessageEvent) {
            if (data instanceof TIMMessage) {
                TIMMessage msg = (TIMMessage) data;
                view.updateMessage(msg);
            }
        }
//        else if (observable instanceof FriendshipEvent) {
//            FriendshipEvent.NotifyCmd cmd = (FriendshipEvent.NotifyCmd) data;
//            switch (cmd.type) {
//                case ADD_REQ:
//                case READ_MSG:
//                case ADD:
//                    view.updateFriendshipMessage();
//                    break;
//            }
//        }

//        else if (observable instanceof GroupEvent){
//            GroupEvent.NotifyCmd cmd = (GroupEvent.NotifyCmd) data;
//            switch (cmd.type){
//                case UPDATE:
//                case ADD:
//                    view.updateGroupInfo((TIMGroupCacheInfo) cmd.data);
//                    break;
//                case DEL:
//                    view.removeConversation((String) cmd.data);
//                    break;
//
//            }
//        }
        else if (observable instanceof RefreshEvent) {
            view.refresh();
        }
    }

    public void getConversation() {
        List<TIMConversation> list = TIMManagerExt.getInstance().getConversationList();

        /**
         *
         08-11 16:14:39.526 2051-2051/com.winwin.app E/ConversationActivity: onCreate
         08-11 16:14:39.627 2051-2051/com.winwin.app E/ConversationPresenter: 4 List<TIMConversation> haha
         08-11 16:14:39.627 2051-2051/com.winwin.app E/ConversationPresenter:  getConversationList()19 C2C
         08-11 16:14:39.627 2051-2051/com.winwin.app E/ConversationPresenter:  getConversationList()14 C2C
         08-11 16:14:39.627 2051-2051/com.winwin.app E/ConversationPresenter:  getConversationList() System
         08-11 16:14:39.628 2051-2051/com.winwin.app E/ConversationPresenter:  getConversationList()25 C2C
         */
        Log.e(TAG, list.size()+" List<TIMConversation> list");
        for (TIMConversation i :list) {
            Log.e(TAG, " getConversationList()" +i.getPeer()+" "+i.getType());
        }

        final List<TIMConversation> result = new ArrayList<>();
//        final List<String> identityList = new ArrayList<>();
//        final List<String> nickNameList = new ArrayList<>();
//        final List<String> avatarList = new ArrayList<>();
//
//        for (TIMConversation conversation : list) {
//            if (conversation.getType() == TIMConversationType.C2C) {
//                identityList.add(conversation.getPeer());
//            }
//        }
//        Log.e(TAG, "identityList.size() result.size() "+identityList.size()+" "+result.size());
//        if (identityList.size() != 0) {
//            //获取用户资料
//            TIMFriendshipManager.getInstance().getUsersProfile(identityList, new TIMValueCallBack<List<TIMUserProfile>>() {
//                @Override
//                public void onError(int code, String desc) {
//                    //错误码code和错误描述desc，可用于定位请求失败原因
//                    //错误码code列表请参见错误码表
//                    Log.e(TAG, "getUsersProfile failed: " + code + " desc");
//                }
//
//                @Override
//                public void onSuccess(List<TIMUserProfile> userResult) {
//                    Log.e(TAG, "getUsersProfile succ");
//                    nickNameList.clear();
//                    avatarList.clear();
//                    for (TIMUserProfile res : userResult) {
//                        Log.e(TAG, "identifier: " + res.getIdentifier() + " nickName: " + res.getNickName() + " remark: " + res.getRemark());
//                        nickNameList.add(res.getNickName());
//                        avatarList.add(res.getFaceUrl());
//                    }
//                    view.initView(result, nickNameList, avatarList);
//                }
//            });
//        }

        for (TIMConversation conversation : list) {
            if (conversation.getType() == TIMConversationType.C2C) {
                result.add(conversation);

                TIMConversationExt conversationExt = new TIMConversationExt(conversation);
                conversationExt.getMessage(1, null, new TIMValueCallBack<List<TIMMessage>>() {
                    @Override
                    public void onError(int i, String s) {
                        Log.e(TAG, "get message error" + s);
                    }

                    @Override
                    public void onSuccess(List<TIMMessage> timMessages) {
                        if (timMessages.size() > 0) {
                            view.updateMessage(timMessages.get(0));
                        }
                        for (TIMMessage i : timMessages) {
                            Log.e(TAG, "timMessages "
                                    +i.getConversation().getPeer() + " " + i.getSenderProfile()
//                                    + " \n" + i.getSenderProfile().getNickName()
//                                    + " \n" + i.getSenderProfile().getIdentifier()
//                                    + " \n" + i.getSenderProfile().getRemark()
//                                    + " \n" + i.getSenderProfile().getFaceUrl()
                                    + "  " + i.getConversation().getType());
                        }
                    }
                });
            }
        }


//        Log.e(TAG, result.size() + " List<TIMConversation> result");
//        for (TIMConversation i : result) {
//            Log.e(TAG, " result: " + i.getPeer() + " " + i.getType());
//        }
//        Log.e(TAG, nickNameList.size() + " List<String> nickNameList");
//        for (String i : nickNameList) {
//            Log.e(TAG, " nickNameList: " + i);
//        }
//        Log.e(TAG, avatarList.size() + " List<String> avatarList");
//        for (String i : avatarList) {
//            Log.e(TAG, " avatarList: " + i);
//        }
//
//        if (nickNameList.size() !=0) {
//            view.initView(result, nickNameList, avatarList);
//        }
//        else {
//            view.initView(result, identityList, identityList);
//        }
        view.initView(result);

    }

    /**
     * 删除会话
     *
     * @param type 会话类型
     * @param id   会话对象id
     */
    public boolean delConversation(TIMConversationType type, String id) {
        return TIMManagerExt.getInstance().deleteConversationAndLocalMsgs(type, id);
    }


}
