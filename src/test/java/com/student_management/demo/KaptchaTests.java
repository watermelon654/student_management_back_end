package com.student_management.demo;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
@SpringBootTest
public class KaptchaTests {
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    /**
     * 获取验证码
     */
    @Test
    public void captcha() throws IOException {
        // 生成文字验证码
        String text=defaultKaptcha.createText();
        System.out.println("文字验证码为"+text);
        // 生成图片验证码
        ByteArrayOutputStream out = null;
        BufferedImage image = defaultKaptcha.createImage(text);
        out=new ByteArrayOutputStream();
        ImageIO.write(image,"jpg",out);
        // 对字节组Base64编码
//        return ResultVo.success("img", Base64.getEncoder().encodeToString(out.toByteArray()));
        System.out.println(Base64.getEncoder().encodeToString(out.toByteArray()));
    }

}
