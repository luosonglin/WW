package com.winwin.android.UI.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.winwin.android.Constant.Data;
import com.winwin.android.MVP.IM.model.Message;
import com.winwin.android.R;
import com.winwin.android.Util.GlideCircleTransform;
import com.winwin.android.im.ui.CircleImageView;

import java.util.List;

/**
 * 聊天界面adapter
 */
public class ChatAdapter extends ArrayAdapter<Message> {

    private final String TAG = "ChatAdapter";
    private Context mContext;
    private int resourceId;
    private View view;
    private ViewHolder viewHolder;
    private String mLeftAvatar;
    RequestOptions options = new RequestOptions()
            .centerCrop()
            .transform(new GlideCircleTransform(mContext))
            .diskCacheStrategy(DiskCacheStrategy.ALL);

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ChatAdapter(Context context, int resource, List<Message> objects, String leftAvatar) {
        super(context, resource, objects);
        mContext = context;
        resourceId = resource;
        mLeftAvatar = leftAvatar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.leftMessage = (RelativeLayout) view.findViewById(R.id.leftMessage);
            viewHolder.rightMessage = (RelativeLayout) view.findViewById(R.id.rightMessage);
            viewHolder.leftPanel = (RelativeLayout) view.findViewById(R.id.leftPanel);
            viewHolder.rightPanel = (RelativeLayout) view.findViewById(R.id.rightPanel);
            viewHolder.sending = (ProgressBar) view.findViewById(R.id.sending);
            viewHolder.error = (ImageView) view.findViewById(R.id.sendError);
            viewHolder.sender = (TextView) view.findViewById(R.id.sender);
            viewHolder.rightDesc = (TextView) view.findViewById(R.id.rightDesc);
            viewHolder.systemMessage = (TextView) view.findViewById(R.id.systemMessage);

            viewHolder.leftAvatar = (CircleImageView) view.findViewById(R.id.leftAvatar);
            viewHolder.rightAvatar = (CircleImageView) view.findViewById(R.id.rightAvatar);

            Glide.with(mContext)
                    .load(Data.getAvatar())
                    .apply(options)
                    .into(viewHolder.rightAvatar);

            view.setTag(viewHolder);
        }
        if (position < getCount()) {
            final Message data = getItem(position);
            data.showMessage(viewHolder, getContext());

            mLeftAvatar = Data.getLeftAvatar();
            Log.e(TAG, " haha " + mLeftAvatar);
            if(mLeftAvatar != null) {
                Glide.with(mContext)
                        .load(mLeftAvatar)
                        .apply(options)
                        .into(viewHolder.leftAvatar);
            }
        }
        return view;
    }

    public class ViewHolder {
        public RelativeLayout leftMessage;
        public RelativeLayout rightMessage;
        public RelativeLayout leftPanel;
        public RelativeLayout rightPanel;
        public ProgressBar sending;
        public ImageView error;
        public TextView sender;
        public TextView systemMessage;
        public TextView rightDesc;

        public CircleImageView leftAvatar;
        public CircleImageView rightAvatar;
    }
}
