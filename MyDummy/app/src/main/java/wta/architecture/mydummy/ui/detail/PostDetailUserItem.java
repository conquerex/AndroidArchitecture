package wta.architecture.mydummy.ui.detail;

import wta.architecture.mydummy.data.entity.User;
import wta.architecture.mydummy.ui.post.PostItem;

/**
 * Created by jongkook on 2020.08.24
 */
public class PostDetailUserItem extends PostDetailItem {

    private User user;
    private EventListener eventListener;

    public PostDetailUserItem(User user, EventListener eventListener) {
        this.user = user;
        this.eventListener = eventListener;
    }

    public String getName() {
        return user.getName();
    }

    public long getUserId() {
        return user.getId();
    }

    public EventListener getEventListener() {
        return eventListener;
    }

    @Override
    public Type getType() {
        return Type.USER;
    }

    public interface EventListener {
        void onUserClick(long userId);
    }
}
