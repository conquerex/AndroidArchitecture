package wta.architecture.mydummy.ui.detail;

/**
 * Created by jongkook on 2020.08.24
 */
public abstract class PostDetailItem {
    public abstract Type getType();

    public enum Type {
        USER,   // 사용자 정보
        POST,   // 게시 글
        COMMENT // 댓글
    }
}
