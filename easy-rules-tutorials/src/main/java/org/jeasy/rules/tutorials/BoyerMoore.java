package org.jeasy.rules.tutorials;

//import org.apache.commons.jexl3.*;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;

import java.beans.Expression;
import java.io.Reader;
import java.io.StringReader;

public class BoyerMoore {
    public static void getRight(String pat, int[] right) {
        for (int i = 0; i < 256; i++){
            right[i] = -1;
        }
        for (int i = 0; i < pat.length(); i++) {
            right[pat.charAt(i)] = i;
        }
    }

    public static int search(final String text, final String pattern) {
        int[] next = calNext(pattern);
        int k = -1;
        int N = text.length();
        int M = pattern.length();
        for (int i = 0; i < N; i++) {
            while(k > -1 && pattern.charAt(k+1) != text.charAt(i)) {
                // 不匹配回溯找最大相同前后缀子字符串
                k = next[k];
            }
            if (pattern.charAt(k+1) == text.charAt(i)) {
                k++;
            }

            if (k == M - 1) {
                return i - M + 1; // 已找到匹配字符
            }
        }
        return -1; // 未找到匹配字符
    }

    private static int[] calNext(final String pattern){
        int M = pattern.length();

        int[] next = new int[M];

        next[0] = -1;

        int k = -1;

        for(int i = 1; i < M; i++){
            while(k > -1 && pattern.charAt(k+1) != pattern.charAt(i)){
                k = next[k];
            }

            if(pattern.charAt(k+1) == pattern.charAt(i)){
                k++;
            }

            next[i] = k;
        }

        return next;
    }

    public static int BoyerMooreSearch(String txt, String pat, int[] right) {
        int M = txt.length();
        int N = pat.length();
        int skip;
        for (int i = 0; i <= M - N; i += skip) {
            skip = 0;
            for (int j = N - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1){
                        skip = 1;
                    }
                    break;
                }
            }
            if (skip == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        for(int i=0;i<10000;i++) {
//            String txt = "检查完成后，遍历pluginsDirectory路径下的文件，遍历过程中，很细心的检查了当前查询路径是否是MacOS或者可能的桌面系统，而不是服务器。如果符合上述条件就跳过该次遍历，不符合的话依然按照加载module的方法那样，取得plugins文件夹下的各个plugin的plugin-descriptor.properties文件，依次加载name，description，version，elasticsearch.version，java.version，has.native.controller，requires.keystore属性，封装成PluginInfo，查找出jar包，最后封装成Bundle对象。\n" +
//                    "\n" +
//                    "\n" +
//                    "\n" +
//                    "作者：飞来来\n" +
//                    "链接：https://www.jianshu.com/p/3373695a2b76\n" +
//                    "来源：简书\n" +
//                    "著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。";
//            String pat = "成Bundle对象";
//            long perStart = System.currentTimeMillis();
//            search(txt, pat);
//            long perEnd = System.currentTimeMillis();
//            System.out.println("单次耗时："+(perEnd-perStart));
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("总耗时为："+(end-start));

//
//        // Create or retrieve an engine
//        JexlEngine jexl = new JexlBuilder().create();
//
//
//        // Create an expression
//        String jexlExp = "foo.innerFoo.bar()";
//
//        JexlExpression e = jexl.createExpression( jexlExp );
//
//        // Create a context and add data
//        JexlContext jc = new MapContext();
//        jc.set("foo", new Foo() );
//
//        // Now evaluate the expression, getting the result
//        Object o = e.evaluate(jc);
        JEP jep = new JEP();
        jep.getFunctionTable().put("and",new And());
        jep.getFunctionTable().put("or",new OR());
//        jep.parseExpression("(张三 and 李四) and (詹蜜 or 李丽)");
        String expression = "张三 and 李四 and 詹蜜 or 李丽";
        String expression1 = expression.replaceAll("\\(","( ");
        String expression2 = expression1.replaceAll("\\)"," )");
        jep.parseExpression(expression2);
        System.out.println(expression2);
//        jep.addVariable()
        jep.getValue();

        Reader reader = new StringReader(expression2);

    }

}
