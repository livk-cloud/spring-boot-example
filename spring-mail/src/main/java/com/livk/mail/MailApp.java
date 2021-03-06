package com.livk.mail;

import com.livk.spring.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * MailApp
 * </p>
 *
 * @author livk
 * @date 2022/2/8
 */
@SpringBootApplication
public class MailApp {

    public static void main(String[] args) {
        LivkSpring.run(MailApp.class, args);
    }

}
