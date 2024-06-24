package com.frame.template.service.email.service;


import com.frame.template.service.email.enums.EmailTypeEnum;
import com.frame.template.service.email.pojo.entity.Email;
import com.frame.template.service.email.pojo.vo.UserDto;
import com.frame.template.service.email.repository.EmailRepository;
import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@CacheConfig(cacheNames = "invite")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Resource
    private EmailRepository emailRepository;

    //  @Value(value = "${invitation.app-url}")
    @Value("http://192.168.0.41:8005")
    private String appUrl;
    @Value(value = "${spring.mail.email}")
    private String senderEmil;

    @Override
    public void inviteUser(UserDto userDto) {
        Email email = new Email();
        email.setType(2);
        email.setReceiverEmail(userDto.getEmail());
        email.setSenderEmail(senderEmil);
        email.setSubject("Welcome");
        email.setBody("Welcome");
        email.setToken(userDto.getActivateToken());

        Context context = new Context();
        context.setVariable("token", userDto.getActivateToken());
        context.setVariable("userEmail", userDto.getEmail());
        context.setVariable("username", userDto.getFirstName() + " " + userDto.getLastName());
        context.setVariable("emailAddress", senderEmil);
        context.setVariable("customerName", userDto.getCustomerName());
        context.setVariable("password", userDto.getPassword());
        context.setVariable("url", "/user/registered?");
        try {
            this.sendEmail(email, context, "Welcome", EmailTypeEnum.Welcome);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmail(Email email, Context context, String templateName, EmailTypeEnum emailTypeEnum) throws Exception {

        context.setVariable("webUrl", appUrl);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(email.getSenderEmail());
        helper.setTo(email.getReceiverEmail());
        helper.setSubject(email.getSubject());
        helper.setBcc(email.getSenderEmail());

        String content = templateEngine.process(templateName, context);


        helper.setText(content, true);

        email = emailRepository.save(email);
        javaMailSender.send(message);
    }

    @Override
    public Email findByTokenAndType(String token, int type) {
        return emailRepository.findByTokenAndType(token, type);
    }

    @Override
    public Email findByTokenAndReceiverEmail(String token, String email) {
        return emailRepository.findByTokenAndReceiverEmail(token, email);
    }


}
