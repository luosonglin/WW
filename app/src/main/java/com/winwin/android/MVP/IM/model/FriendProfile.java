package com.winwin.android.MVP.IM.model;

import android.content.Context;

import com.tencent.imsdk.TIMUserProfile;
import com.winwin.android.BaseApplication;
import com.winwin.android.R;
import com.winwin.android.Util.ToastUtils;
import com.winwin.android.im.model.ProfileSummary;

/**
 * 好友资料
 */
public class FriendProfile implements ProfileSummary {

    private TIMUserProfile profile;
    private boolean isSelected;

    public FriendProfile(TIMUserProfile profile){
        this.profile = profile;
    }


    /**
     * 获取头像资源
     */
    @Override
    public int getAvatarRes() {
        return R.drawable.head_other;
    }

    /**
     * 获取头像地址
     */
    @Override
    public String getAvatarUrl() {
//        return null;
        return profile.getFaceUrl();
    }

    /**
     * 获取名字
     */
    @Override
    public String getName() {
        if (!profile.getRemark().equals("")){
            return profile.getRemark();
        }else if (!profile.getNickName().equals("")){
            return profile.getNickName();
        }
        return profile.getIdentifier();
    }

    /**
     * 获取描述信息
     */
    @Override
    public String getDescription() {
        return null;
    }

    /**
     * 显示详情
     *
     * @param context 上下文
     */
    @Override
    public void onClick(Context context) {
//        if (FriendshipInfo.getInstance().isFriend(profile.getIdentifier())){
//            ProfileActivity.navToProfile(context, profile.getIdentifier());
//        }else{
//            Intent person = new Intent(context,AddFriendActivity.class);
//            person.putExtra("id",profile.getIdentifier());
//            person.putExtra("name",getName());
//            context.startActivity(person);
//        }
        ToastUtils.show(context, "跳转ProfileActivity AddFriendActivity");
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * 获取用户ID
     */
    @Override
    public String getIdentify(){
        return profile.getIdentifier();
    }


    /**
     * 获取用户备注名
     */
    public String getRemark(){
        return profile.getRemark();
    }


    /**
     * 获取好友分组
     */
    public String getGroupName(){
//
//        if (profile.getFriendGroups().size() == 0){
//            return BaseApplication.getContext().getString(R.string.default_group_name);
//        }else{
//            return profile.getFriendGroups().get(0);
//        }
        return BaseApplication.getContext().getString(R.string.default_group_name);
    }

}
