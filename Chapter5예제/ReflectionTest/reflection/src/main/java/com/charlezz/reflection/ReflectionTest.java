package com.charlezz.reflection;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTest {
    public static final String TAG = ReflectionTest.class.getSimpleName();

    void simpleExample() {
        Log.d(TAG, ">>>>>> simpleExample");
        try {
            Class c = Class.forName("java.lang.String");
            Method m[] = c.getDeclaredMethods();
            Log.e(TAG, "---- length ::: " + m.length);
            for (int i = 0; i < m.length; i++)
                Log.e(TAG, m[i].toString());
        } catch (Throwable e) {
            Log.e(TAG, e.toString());
        }
    }

    void settingUP() throws ClassNotFoundException {
        Log.d(TAG, ">>>>>> settingUP");
        Class c = Class.forName("java.lang.String");
        Class intClass = int.class;
        Class type = Integer.TYPE;

        Method[] methods = c.getDeclaredMethods();
    }

    void simulateInstanceOf() {
        Log.d(TAG, ">>>>>> simulateInstanceOf");
        try {
            Class cls = Class.forName("java.lang.String");
            boolean b1 = cls.isInstance(3);
            Log.e(TAG, "b1 " + b1);
            boolean b2 = cls.isInstance("Test");
            Log.e(TAG, "b2 " + b2);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    void findoutMethods() {
        Log.d(TAG, ">>>>>> findoutMethods");
        try {
            Class cls = Class.forName("java.lang.String");
            Method methods[] = cls.getDeclaredMethods();
            Log.e(TAG, "---- length ::: " + methods.length);
            for (int i = 0; i < methods.length; i++) {
                Method m = methods[i];
                Log.e(TAG, "메소드 이름 = " + m.getName());
                Log.e(TAG, "정의된 클래스이름 = " + m.getDeclaringClass());

                Class pvec[] = m.getParameterTypes();
                for (int j = 0; j < pvec.length; j++) {
                    Log.e(TAG, " param #" + j + " " + pvec[j]);
                }

                Class evec[] = m.getExceptionTypes();
                for (int j = 0; j < evec.length; j++) {
                    Log.e(TAG, "exc #" + j + " " + evec[j]);
                }

                Log.e(TAG, "return type = " + m.getReturnType());
                Log.e(TAG, "-----");
            }
        } catch (Throwable e) {
            Log.e(TAG, e.toString());
        }
    }

    void findConstructors() {
        Log.d(TAG, ">>>>>> findConstructors");
        try {
            Class cls = Class.forName("java.lang.String");
            Constructor ctorlist[] = cls.getDeclaredConstructors();
            Log.e(TAG, "---- length ::: " + ctorlist.length);
            for (int i = 0; i < ctorlist.length; i++) {
                Constructor ct = ctorlist[i];
                Log.e(TAG, "생성자 이름 = " + ct.getName());
                Log.e(TAG, "정의된 클래스이름 = " + ct.getDeclaringClass());
                Class pvec[] = ct.getParameterTypes();
                for (int j = 0; j < pvec.length; j++) {
                    Log.e(TAG, "param #" + j + " " + pvec[j]);
                }
                Class evec[] = ct.getExceptionTypes();

                for (int j = 0; j < evec.length; j++) {
                    Log.e(TAG, "exc #" + j + " " + evec[j]);
                }
                Log.e(TAG, "-----");
            }
        } catch (Throwable e) {
            Log.e(TAG, e.toString());
        }
    }


    void findoutFields() {
        Log.d(TAG, ">>>>>> findoutFields");
        try {
            Class cls = Class.forName("java.lang.String");

            Field fieldlist[] = cls.getDeclaredFields();
            Log.e(TAG, "---- length ::: " + fieldlist.length);
            for (int i = 0; i < fieldlist.length; i++) {
                Field fld = fieldlist[i];
                Log.e(TAG, "필드명 = " + fld.getName());
                Log.e(TAG, "정의된클래스 = " + fld.getDeclaringClass());
                Log.e(TAG, "필드타입 = " + fld.getType());
                int mod = fld.getModifiers();
                Log.e(TAG, "접근제어자 = " + Modifier.toString(mod));
                Log.e(TAG, "-----");
            }
        } catch (Throwable e) {
            Log.e(TAG, e.toString());
        }
    }

    void invoke() {
        Log.d(TAG, ">>>>>> invoke");
        try {
            Class cls = Class.forName("java.lang.String");
            String data = "Hello World";
            Method lengthMethod = cls.getMethod("length");
            int length = (int) lengthMethod.invoke(data);
            Log.e(TAG, "length=" + length); //length=11

            Method substringMethod = cls.getMethod("substring", int.class, int.class);
            String subStr = (String) substringMethod.invoke(data, 0, 5);
            Log.e(TAG, "subStr=" + subStr);//Hello
        } catch (Throwable e) {
            Log.e(TAG, e.toString());
        }
    }

    void invokePrivate() {
        Log.d(TAG, ">>>>>> invokePrivate");
        try {
            A a = new A();
            //메소드가 private하여 a.show() 찾을 수가 없음
            Method showMethod = a.getClass().getDeclaredMethod("show");
            // private이라서 setAccessible로 세팅
            showMethod.setAccessible(true);
            showMethod.invoke(a);

        }catch (Exception e){
            Log.e(TAG,e.toString());
        }
    }

    void creatingObject(){
        Log.d(TAG, ">>>>>> creatingObject");
        try {
            Class personClass = Class.forName("com.charlezz.reflection.Person");
            Constructor personConstructor = personClass.getConstructor(String.class, int.class);
            Person person = (Person) personConstructor.newInstance("Charles",20);
            person.sayMyName();
        }
        catch (Throwable e) {
            Log.e(TAG,e.toString());
        }
    }

    void changeValuesOfFields(){
        Log.d(TAG, ">>>>>> changeValuesOfFields");
        try {
            Class cls = Class.forName("com.charlezz.reflection.Person");
//            Field ageField = cls.getField("age"); // getField 사용시 Exception 발생
            Field ageField = cls.getDeclaredField("age");
            Person person = new Person("Charles", 20);
            Log.e(TAG,"person.age = " + person.age);
            ageField.setInt(person, 10);
            Log.e(TAG,"person.age = " + person.age);
        }
        catch (Throwable e) {
            Log.e(TAG,e.toString());
        }
    }
}
