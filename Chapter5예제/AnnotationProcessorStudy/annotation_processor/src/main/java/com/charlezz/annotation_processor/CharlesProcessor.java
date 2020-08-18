package com.charlezz.annotation_processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import com.charlezz.annotation.CharlesIntent;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/*
    <애노테이션 프로세서>
    컴파일 타임에 애노테이션을 스캔하고 처리하도록 javac에 포함된 도구
    자바 소스 코드를 읽어 또 다른 자바 파일을 출력하는 데 사용
    .
    빠르다
    리플렉션을 사용하지 않는다
    보일러 플레이트 코드 제거
    .
    애노테이션 프로세서를 사용하려면 3개의 안드로이드 스튜디오 모듈이 필요
      --> 애노테이션 모듈, 애노테이션 프로세서 모듈, 애플리케이션 모듈
 */
@AutoService(Processor.class)
public class CharlesProcessor extends AbstractProcessor {

    private static final ClassName intentClass = ClassName.get("android.content", "Intent");
    private static final ClassName contextClass = ClassName.get("android.content", "Context");
    private static final String METHOD_PREFIX_NEW_INTENT = "intentFor";

    ArrayList<MethodSpec> newIntentMethodSpecs = new ArrayList<>();

    private String packageName;

    /*
        1. void init
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        // 프로세싱에 필요한 정보들을 processingEnvironment로부터 가져온다.
    }

    /*
        2. boolean process : 애노테이션을 처리하고 자바파일을 생성하는 코드를 작성
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 이곳에서 애노테이션을 처리한다.

        System.out.println("process");

        final Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(CharlesIntent.class);

        for (Element element : elements) {
            if(packageName==null){
                Element e = element;
                while (!(e instanceof PackageElement)) {
                    e = e.getEnclosingElement();
                }
                packageName = ((PackageElement)e).getQualifiedName().toString();
            }

            if (element.getKind() != ElementKind.CLASS) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "CharlesIntent can only use for classes!");
                return false;
            }
            newIntentMethodSpecs.add(generateMethod((TypeElement) element));
        }

        if (roundEnvironment.processingOver()) {
            try {
                generateJavaFile(newIntentMethodSpecs);
                return true;
            } catch (IOException ex) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, ex.toString());
            }
        }
        return true;
    }

    /*
        3. Set<String> getSupportedAnnotationTypes
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<String>(){
            {
                add(CharlesIntent.class.getCanonicalName());
                // 어떤 애노테이션을 처리할지 Set에 추가한다.
            }
        };
    }

    /*
        4. SourceVersion getSupportedSourceVersion
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
        // 지원하는 자바 버전을 반환한다.
    }

    private MethodSpec generateMethod(TypeElement element) {
        return MethodSpec
                .methodBuilder(METHOD_PREFIX_NEW_INTENT + element.getSimpleName())
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(contextClass, "context")
                .returns(intentClass)
                .addStatement("return new $T($L, $L)", intentClass, "context", element.getQualifiedName() + ".class")
                .build();
    }

    private void generateJavaFile(List<MethodSpec> methodSpecList) throws IOException {
        System.out.println("methodSpecList Count = "+methodSpecList.size());
        final TypeSpec.Builder builder = TypeSpec.classBuilder("Charles");
        builder.addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        for (MethodSpec methodSpec : methodSpecList) {
            builder.addMethod(methodSpec);
        }

        final TypeSpec typeSpec = builder.build();

        JavaFile.builder(packageName, typeSpec)
                .build()
                .writeTo(processingEnv.getFiler());
    }
}