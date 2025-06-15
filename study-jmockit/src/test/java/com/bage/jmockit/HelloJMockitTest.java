package com.bage.jmockit;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

//HelloJMockit类的测试类
@RunWith(JMockit.class)
public class HelloJMockitTest {

    // 这是一个测试属性
    @Mocked
    HelloJMockit helloJMockit;

    @Test
    public void test1() {
        // 录制(Record)
        new Expectations() {
            {
                helloJMockit.sayHello();
                // 期待上述调用的返回是"hello,david"，而不是返回"hello,JMockit"
                result = "hello,david";
            }
        };
        // 重放(Replay)
        String msg = helloJMockit.sayHello();
        Assert.assertTrue(msg.equals("hello,david"));
        // 验证(Verification)
        new Verifications() {
            {
                helloJMockit.sayHello();

                times = 1;
            }
        };
    }


}
