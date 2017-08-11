package com.winwin.app.UI.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.winwin.app.R;
import com.winwin.app.Util.GlideCircleTransform;
import com.winwin.app.Util.TimeUtil;
import com.winwin.app.im.model.Conversation;
import com.winwin.app.im.ui.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 会话界面adapter
 */
public class ConversationAdapter extends ArrayAdapter<Conversation> {

    private static final String TAG = ConversationAdapter.class.getSimpleName();
    private Context mContext;
    private int resourceId;
    private View view;
    private ViewHolder viewHolder;
    private List<Conversation> mConversations;
    private List<String> mNickNames;
    private List<String> mAvatars;

    private View mHeaderView;


    //待获取用户资料的用户列表
    List<String> users = new ArrayList<String>();

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ConversationAdapter(Context context, int resource, List<Conversation> objects, List<String> nickNames, List<String> avatars) {
        super(context, resource, objects);
        mContext = context;
        resourceId = resource;
        mConversations = objects;
        mNickNames = nickNames;
        mAvatars = avatars;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) view.findViewById(R.id.name);
            viewHolder.avatar = (CircleImageView) view.findViewById(R.id.avatar);
            viewHolder.lastMessage = (TextView) view.findViewById(R.id.last_message);
            viewHolder.time = (TextView) view.findViewById(R.id.message_time);
            viewHolder.unread = (TextView) view.findViewById(R.id.unread_num);
            view.setTag(viewHolder);
        }
        final Conversation data = getItem(position);
        Log.e(TAG, position + " position");

        users.clear();
        users.add(data.getIdentify());
        Log.e(TAG, users.size() + " users.size()");
        for (String i :users) {
            Log.e(TAG, i);
        }
        //获取用户资料
        TIMFriendshipManager.getInstance().getUsersProfile(users, new TIMValueCallBack<List<TIMUserProfile>>(){
            @Override
            public void onError(int code, String desc){
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code列表请参见错误码表
                Log.e(TAG, "getUsersProfile failed: " + code + " desc");
            }

            @Override
            public void onSuccess(List<TIMUserProfile> result){
                Log.e(TAG, "getUsersProfile succ" + result.get(position).getNickName()+" "+result.get(0).getIdentifier());
                for(TIMUserProfile res : result){
                    Log.e(TAG, "identifier: " + res.getIdentifier() + " nickName: " + res.getNickName()
                            + " remark: " + res.getRemark());
                }
                viewHolder.tvName.setText(result.get(position).getNickName()+" "+data.getIdentify() +" "+data.getName());
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .transform(new GlideCircleTransform(mContext))
                        .placeholder(R.mipmap.emoji)
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(mContext)
                        .load(result.get(0).getFaceUrl())
                        .apply(options)
                        .into(viewHolder.avatar);
            }
        });

//        Log.e(TAG, mNickNames.size()+" mNickNames.size()");
//        Log.e(TAG, position + " dataNickName position");
//        final String dataNickName = mNickNames.get(position);

//        viewHolder.tvName.setText(dataNickName);
//        viewHolder.avatar.setImageResource(data.getAvatar());
//        RequestOptions options = new RequestOptions()
//                        .centerCrop()
//                        .transform(new GlideCircleTransform(mContext))
//                        .diskCacheStrategy(DiskCacheStrategy.ALL);
//                Glide.with(mContext)
//                        .load(mAvatars.get(position))
//                        .apply(options)
//                        .into(viewHolder.avatar);
//        viewHolder.lastMessage.setText(data.getLastMessageSummary());
        viewHolder.time.setText(TimeUtil.getTimeStr(data.getLastMessageTime()));
        long unRead = data.getUnreadNum();
        if (unRead <= 0) {
            viewHolder.unread.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.unread.setVisibility(View.VISIBLE);
            String unReadStr = String.valueOf(unRead);
            if (unRead < 10) {
                viewHolder.unread.setBackground(getContext().getResources().getDrawable(R.drawable.point3));
            } else {
                viewHolder.unread.setBackground(getContext().getResources().getDrawable(R.drawable.point4));
                if (unRead > 99) {
                    unReadStr = getContext().getResources().getString(R.string.time_more);
                }
            }
            viewHolder.unread.setText(unReadStr);
        }
        return view;
    }

    public class ViewHolder {
        public TextView tvName;
        public CircleImageView avatar;
        public TextView lastMessage;
        public TextView time;
        public TextView unread;
    }

    public void addHeaderView(View header) {
        this.mHeaderView = header;
        this.notifyDataSetChanged();
    }

}
