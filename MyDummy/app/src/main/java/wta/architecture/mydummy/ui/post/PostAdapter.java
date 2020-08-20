package wta.architecture.mydummy.ui.post;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import wta.architecture.mydummy.util.ViewBindingHolder;

/**
 * Created by jongkook on 2020.08.20
 * .
 * ViewBindingHolder를 만들었다면 각 홀더에 포함되는 레이아웃과 RecyclerView.Adapter를 만들어야 한다.
 */
public class PostAdapter extends RecyclerView.Adapter<ViewBindingHolder> {
    
    @NonNull
    @Override
    public ViewBindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewBindingHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
