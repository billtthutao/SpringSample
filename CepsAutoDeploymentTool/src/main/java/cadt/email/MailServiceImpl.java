package cadt.email;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService {

	@Autowired
    private JavaMailSender mailSender;
	private String from = "billtt@163.com";
	
	@Override
	public void sendSimpleMail(String[] to, String[] cc, String subject, String content) {
		// TODO Auto-generated method stub
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);//收信人
        message.setCc(cc);//抄送人
        message.setSubject(subject);//主题
        message.setText(content);//内容
        message.setFrom(from);//发信人
        
        mailSender.send(message);
	}

	@Override
	public void sendHtmlMail(String[] to, String[] cc, String subject, String content) {
		// TODO Auto-generated method stub
        //logger.info("发送HTML邮件开始：{},{},{}", to, subject, content);
        //使用MimeMessage，MIME协议
        MimeMessage message = mailSender.createMimeMessage();
        
        MimeMessageHelper helper;
        //MimeMessageHelper帮助我们设置更丰富的内容
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(content, true);//true代表支持html
            mailSender.send(message);
            //logger.info("发送HTML邮件成功");
        } catch (MessagingException e) {
            //logger.error("发送HTML邮件失败：", e);
        } 
	}

	@Override
	public void sendAttachmentMail(String[] to, String[] cc, String subject, String content, String filePath) {
		// TODO Auto-generated method stub
        //logger.info("发送带附件邮件开始：{},{},{},{}", to, subject, content, filePath);
        MimeMessage message = mailSender.createMimeMessage();
        
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            //true代表支持多组件，如附件，图片等
            helper.setFrom(from);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);//添加附件，可多次调用该方法添加多个附件  
            mailSender.send(message);
            //logger.info("发送带附件邮件成功");
        } catch (MessagingException e) {
            //logger.error("发送带附件邮件失败", e);
        }
	}

	@Override
	public void sendInlineResourceMail(String[] to, String[] cc, String subject, String content, String rscPath, String rscId) {
		// TODO Auto-generated method stub
        //logger.info("发送带图片邮件开始：{},{},{},{},{}", to, subject, content, rscPath, rscId);
        MimeMessage message = mailSender.createMimeMessage();
        
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);//重复使用添加多个图片
            mailSender.send(message);
            //logger.info("发送带图片邮件成功");
        } catch (MessagingException e) {
            //logger.error("发送带图片邮件失败", e);
        }
	}

}
