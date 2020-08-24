package wta.architecture.mydummy.ui.detail;

import wta.architecture.mydummy.data.entity.Comment;
import wta.architecture.mydummy.data.entity.Post;

/**
 * Created by jongkook on 2020.08.25
 */
public class PostDetailCommentItem extends PostDetailItem {

    private Comment comment;

    public PostDetailCommentItem(Comment comment) {
        this.comment = comment;
    }

    @Override
    public Type getType() {
        return Type.COMMENT;
    }

    public String getName() {
        return comment.getName();
    }

    public String getBody() {
        return comment.getBody();
    }
}
