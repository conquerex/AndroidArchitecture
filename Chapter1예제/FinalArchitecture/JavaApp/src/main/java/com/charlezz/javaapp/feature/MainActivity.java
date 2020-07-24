package com.charlezz.javaapp.feature;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.charlezz.javaapp.databinding.ActivityMainBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    ActivityMainBinding binding;

    @Inject
    MainViewModel viewModel;

    @Inject
    MainPageAdapter adapter;

    @Inject
    CompositeDisposable disposables;

    private final int REQUEST_PERMISSION_CODE = 1;

    private String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reqPermissions(getPermissionList(permissions));
    }

    private void init(){
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    private void reqPermissions(Single<List<String>> list) {
        Disposable disposal = list.subscribe((strings, throwable) -> {
            if (!strings.isEmpty()) {
                String[] tempPermissions = new String[strings.size()];
                strings.toArray(tempPermissions);
                ActivityCompat.requestPermissions(MainActivity.this, tempPermissions, REQUEST_PERMISSION_CODE);
            } else {
                init();
            }
        });
        disposables.add(disposal);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_PERMISSION_CODE){
            for(int grantResult : grantResults){
                if(grantResult == PackageManager.PERMISSION_DENIED){
                    finish();
                    return;
                }
            }
            init();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!disposables.isDisposed()){
            disposables.dispose();
        }
    }

    private Single<List<String>> getPermissionList(String[] permissions)  {
        return Observable.fromArray(permissions).filter(permission -> PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(MainActivity.this, permission)).toList();
    }
}
