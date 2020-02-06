package com.caxcot.imooclistviewdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.app_list_view);

        ArrayList<ChatMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatMessage(1, 2, "刘晓明", "8:20", "你好吗", true));
        chatMessages.add(new ChatMessage(2, 1, "小军", "8:21", "我很好", false));
        chatMessages.add(new ChatMessage(1, 2, "刘晓明", "8:22", "今天天气怎么样", true));
        chatMessages.add(new ChatMessage(2, 1, "小军", "8:23", "热成狗了", false));

        mListView.setAdapter(new ChatMessageAdapter(this, chatMessages));
    }

    public class ChatMessage {
        private int mId;
        private int mFriendId;
        private String mName;
        private String mDate;
        private String mContent;
        private boolean mIsComeMessage;

        public ChatMessage(int mId, int mFriendId, String mName, String mDate, String mContent, boolean mIsComeMessage) {
            this.mId = mId;
            this.mFriendId = mFriendId;
            this.mName = mName;
            this.mDate = mDate;
            this.mContent = mContent;
            this.mIsComeMessage = mIsComeMessage;
        }

        public boolean ismIsComeMessage() {
            return mIsComeMessage;
        }

        public void setmIsComeMessage(boolean mIsComeMessage) {
            this.mIsComeMessage = mIsComeMessage;
        }

        public int getmId() {
            return mId;
        }

        public void setmId(int mId) {
            this.mId = mId;
        }

        public int getmFriendId() {
            return mFriendId;
        }

        public void setmFriendId(int mFriendId) {
            this.mFriendId = mFriendId;
        }

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public String getmDate() {
            return mDate;
        }

        public void setmDate(String mDate) {
            this.mDate = mDate;
        }

        public String getmContent() {
            return mContent;
        }

        public void setmContent(String mContent) {
            this.mContent = mContent;
        }
    }

    public static class ChatMessageAdapter extends BaseAdapter {

        private Context mContext;
        private ArrayList<ChatMessage> mChatMessages = new ArrayList<>();

        interface IMessageViewType {
            int COM_MESSAGE = 0;
            int TO_MESSAGE = 1;
        }

        public ChatMessageAdapter(Context mContext, ArrayList<ChatMessage> mChatMessages) {
            this.mContext = mContext;
            this.mChatMessages = mChatMessages;
        }

        @Override
        public int getCount() {
            return mChatMessages.size();
        }

        @Override
        public Object getItem(int position) {
            return mChatMessages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            ChatMessage chatMessage = mChatMessages.get(position);
            return chatMessage.ismIsComeMessage() ? IMessageViewType.COM_MESSAGE : IMessageViewType.TO_MESSAGE;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ChatMessage entity = mChatMessages.get(position);
            boolean isComMsg = entity.ismIsComeMessage();

            ViewHolder viewHolder;
            if (convertView == null) {
                if (isComMsg) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.chatting_item_msg_text_left, null);
                } else {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.chatting_item_msg_text_right, null);
                }
                viewHolder = new ViewHolder();
                viewHolder.mSendTime = (TextView) convertView.findViewById(R.id.tv_send_time);
                viewHolder.mUserName = (TextView) convertView.findViewById(R.id.tv_username);
                viewHolder.mContent = (TextView) convertView.findViewById(R.id.tv_chat_content);
                viewHolder.mTime = (TextView) convertView.findViewById(R.id.tv_time);
                viewHolder.mUserAvatar = (ImageView) convertView.findViewById(R.id.iv_user_head);
                viewHolder.mIsComMessage = isComMsg;
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.mSendTime.setText(entity.getmDate());
            viewHolder.mContent.setText(entity.getmContent());
            viewHolder.mContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            viewHolder.mTime.setText("");
            viewHolder.mUserName.setText(entity.getmName());
            if (isComMsg) {
                viewHolder.mUserAvatar.setImageResource(R.drawable.avatar);
            } else {
                viewHolder.mUserAvatar.setImageResource(R.mipmap.ic_launcher);
            }
            return convertView;
        }

        class ViewHolder {
            public TextView mSendTime;
            public TextView mUserName;
            public TextView mContent;
            public TextView mTime;
            public ImageView mUserAvatar;
            public boolean mIsComMessage = true;
        }
    }
}
