import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@RestController
public class MailSender {
    @Autowired
    private JavaMailSender mailSender;

    public void mailSender() {
        String getId = "이메일을 받을 사람의 이메일@email.com";

        // 이메일에 들어갈 내용
        String htmlContent = "이메일 내용";

        // 이메일 전송

        // MimeMessage 객체 생성
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // MimeMessageHelper 객체를 사용하여 MimeMessage 설정
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            // 이메일의 보내는 사람 주소 설정
            helper.setFrom("보내는 사람 란에 들어갈 이메일@email.net");

            // 이메일의 수신자 주소 설정
            helper.setTo(getId);

            // 이메일의 제목 설정
            helper.setSubject("이메일 제목");

            // 이메일의 본문 설정 (HTML 형식)
            helper.setText(htmlContent, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // 이메일 전송
        mailSender.send(mimeMessage);
    }
}
