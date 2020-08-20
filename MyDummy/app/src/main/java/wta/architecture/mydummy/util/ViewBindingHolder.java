package wta.architecture.mydummy.util;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by jongkook on 2020.08.20
 * .
 * RecyclerView.Adapter를 만들기 전에 ViewDataBinding 클래스 사용을 강제하는 ViewHolder를 만든다.
 */
public class ViewBindingHolder<VDB extends ViewDataBinding>
        extends RecyclerView.ViewHolder {
    private VDB binding;

    public ViewBindingHolder(@NonNull VDB binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ViewBindingHolder(Context context, @LayoutRes int layoutId) {
        this(DataBindingUtil.inflate(
                LayoutInflater.from(context),
                layoutId,
                null,
                false
        ));
    }

    public VDB getBinding() {
        return binding;
    }
}
