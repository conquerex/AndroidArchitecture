package com.charlezz.checker;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.SourceCodeScanner;
import com.intellij.psi.PsiMethod;

/*
    (p405, Detector)
    Detector는 특정 문제를 검출하는 클래스
 */
public class SetContentViewDetector
        extends Detector
        implements SourceCodeScanner {

    // 이슈 생성 : Issue.create(...)
    public static final Issue ISSUE = Issue.create(
            SetContentViewDetector.class.getSimpleName(),
            "Prohibits usages of setContentView()",
            "Prohibits usages of setContentView(), use DataBindingUtil.setContentView() instead",
            Category.CORRECTNESS,
            5,
            Severity.ERROR,
            new Implementation(SetContentViewDetector.class, Scope.JAVA_FILE_SCOPE)
    );

    @Nullable
    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("setContentView");
    }

    /*
        (p406)
        getApplicableMethodNames 메서드에서 반환된 메서드의 이름이 검출되면 호출된다.
        .
        JavaContext context  : 분석한 자바 파일에 대한 정보들을 가진다.
        UCallExpression node : 호출된메서드의 노드 정보
        PsiMethod method     : 호출된 메서드를 표현
     */
    @Override
    public void visitMethodCall(@NotNull JavaContext context, @NotNull UCallExpression node, @NotNull PsiMethod method) {

        // 데이터 바인딩 클래스의 메서드일 경우 report하지 앟으려면
        // Evaluator로부터 메서드가 DatabindingUtil 클래스에 속하는지 확인해야한다.
        if (context.getEvaluator().isMemberInClass(method, "androidx.databinding.DataBindingUtil")) {
            return;
        }

        /*
            <context.report>
             setContentView 이름을 갖는 메서드가 검출되었다면 context.report(...) 메서드 호출을 통해
             lint에 에러를 알린다.
         */
        context.report(
                ISSUE,
                node,
                context.getLocation(node),
                "Use DataBindingUtil.setContentView() instead"
        );
    }

}
