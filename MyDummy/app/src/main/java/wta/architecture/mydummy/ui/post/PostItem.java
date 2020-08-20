package wta.architecture.mydummy.ui.post;

import androidx.annotation.NonNull;

import wta.architecture.mydummy.data.entity.Post;

/**
 * Created by jongkook on 2020.08.20
 * .
 * 도메인 레이어와 프레젠테이션 레이어 분리를 위해 다음과 같은 PostItem 클래스를 정의한다.
 * PostItem 인스턴스는 View에 해당하는 RecyclerView.ViewHolder 인스턴스의 ViewModel 역할을 한다.
 */
public class PostItem {
    @NonNull
    private final Post post;

    public PostItem(@NonNull Post post) {
        this.post = post;
    }

    @NonNull
    public Post getPost() {
        return post;
    }

    public String getTitle() {
        return post.getTitle();
    }
}
