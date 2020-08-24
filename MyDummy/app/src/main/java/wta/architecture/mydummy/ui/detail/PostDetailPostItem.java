package wta.architecture.mydummy.ui.detail;

import wta.architecture.mydummy.data.entity.Post;

/**
 * Created by jongkook on 2020.08.25
 */
public class PostDetailPostItem extends PostDetailItem {

    private Post post;

    public PostDetailPostItem(Post post) {
        this.post = post;
    }

    @Override
    public Type getType() {
        return Type.POST;
    }

    public String getTitle() {
        return post.getTitle();
    }

    public String getBody() {
        return post.getBody();
    }
}
