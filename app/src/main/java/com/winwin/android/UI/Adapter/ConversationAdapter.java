package com.winwin.android.UI.Adapter;

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
import com.winwin.android.MVP.IM.model.ConversationUser;
import com.winwin.android.R;
import com.winwin.android.Util.GlideCircleTransform;
import com.winwin.android.Util.TimeUtil;
import com.winwin.android.im.model.Conversation;
import com.winwin.android.im.ui.CircleImageView;

import java.util.ArrayList;
import java.util.HashSet;
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

    private View mHeaderView;


//    //待获取用户资料的用户列表
//    List<String> users = new ArrayList<String>();
//
//    List<String> nickNames = new ArrayList<>();
//    List<String> avatars = new ArrayList<>();
    List<ConversationUser> mConversationUsers = new ArrayList<>();

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ConversationAdapter(Context context, int resource, List<Conversation> objects, List<ConversationUser> conversationUsers) {
        super(context, resource, objects);
        mContext = context;
        resourceId = resource;
        mConversations = objects;
        mConversationUsers = conversationUsers;
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

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .transform(new GlideCircleTransform(mContext))
                .placeholder(R.mipmap.emoji)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        HashSet<ConversationUser> hs = new HashSet<>(mConversationUsers); //此时已经去掉重复的数据保存在hashset中

        for (ConversationUser i: hs) {
            Log.e(TAG, i.getIdentify()+" "+i.getNickName()+" "+i.getAvatar());
                if (i.getIdentify().equals(data.getIdentify())) {
                    viewHolder.tvName.setText(i.getNickName());
                    Glide.with(mContext)
                            .load(i.getAvatar())
                            .apply(options)
                            .into(viewHolder.avatar);
                }
            }
        viewHolder.lastMessage.setText(data.getLastMessageSummary());
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
