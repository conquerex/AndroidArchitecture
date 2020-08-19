package com.charlezz.checker;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.ApiKt;
import com.android.tools.lint.detector.api.Issue;

// IssueRegistry : 안드로이드 프로젝트에서 수행할 검사 목록을 제공하는 레지스트리
public class CustomIssueRegistry extends IssueRegistry {

    @Override
    public int getApi() {
        // 이 레지스트리의 검사를 수행할 API 버전을 명시
        return ApiKt.CURRENT_API;
    }

    @NotNull
    @Override
    public List<Issue> getIssues() {
        // 이슈 등록
        return Arrays.asList(
                SetContentViewDetector.ISSUE
        );
    }
}
