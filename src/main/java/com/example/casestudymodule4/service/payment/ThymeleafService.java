package com.example.casestudymodule4.service.payment;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Service
public class ThymeleafService {
    private static final String MAIL_TEMPLATE_BASE_NAME = "mail/MailMessages";
    private static final String MAIL_TEMPLATE_PREFIX = "/templates/";
    private static final String MAIL_TEMPLATE_SUFFIX = ".html";
    private static final String UTF_8 = "UTF-8";

    private static final String TEMPLATE_NAME = "payment/ordersuccess";

    private static TemplateEngine templateEngine;

    static {
        templateEngine = emailTemplateEngine();
    }

    private static TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(htmlTemplateResolver());
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }

    private static ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(MAIL_TEMPLATE_BASE_NAME);
        return messageSource;
    }

    private static ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix(MAIL_TEMPLATE_PREFIX);
        templateResolver.setSuffix(MAIL_TEMPLATE_SUFFIX);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(UTF_8);
        templateResolver.setCacheable(false);
        return templateResolver;
    }
public String getContent(String orderInfo, String paymentTime, String transactionId, Double totalPrice) {
    // Sử dụng các tham số để tạo nội dung email
    String content = "<!DOCTYPE html>"
            + "<html xmlns:th=\"http://www.thymeleaf.org\">"
            + "<head>"
            + "<meta charset=\"UTF-8\">"
            + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
            + "<title>Thanh toán thành công</title>"
            + "<style>"
            + "body { font-family: Arial, sans-serif; background-color: #f8f9fa; margin: 0; padding: 0; }"
            + ".py-5 { padding-top: 3rem; padding-bottom: 3rem; }"
            + ".container { max-width: 960px; margin: 0 auto; padding: 0 15px; }"
            + ".content { width: 50%; margin: auto; background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }"
            + ".success-header { color: #c30808; text-align: center; margin-bottom: 1.5rem; }"
            + ".order-details-header { margin-top: 1rem; margin-bottom: 1rem; }"
            + ".order-table { width: 100%; border-collapse: collapse; margin-bottom: 1rem; }"
            + ".order-table td { border: 1px solid #dee2e6; padding: 8px; vertical-align: top; }"
            + ".order-table tr:nth-child(even) { background-color: #f2f2f2; }"
            + ".order-table tr:hover { background-color: #ddd; }"
            + ".order-table td:first-child { font-weight: bold; }"
            + ".btn-success { text-decoration: none; display: inline-block; font-weight: 400; color: #fff; text-align: center; vertical-align: middle; user-select: none; background-color: #c30808; border: 1px solid #1c1c1b; padding: 0.375rem 0.75rem; font-size: 1rem; line-height: 1.5; border-radius: 0.25rem; transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out; }"
            + ".btn-success:hover { background-color: #1c1c1b; border-color: #1c1c1b; }"
            + ".btn-success:focus, .btn-success.focus { box-shadow: 0 0 0 0.2rem rgb(195, 8, 8); }"
            + ".btn-success:disabled, .btn-success.disabled { opacity: 0.65; }"
            + ".btn-success:not(:disabled):not(.disabled):active, .btn-success:not(:disabled):not(.disabled).active, .show > .btn-success.dropdown-toggle { background-color: #c30808; border-color: #1c1c1b; }"
            + ".btn-success:not(:disabled):not(.disabled):active:focus, .btn-success:not(:disabled):not(.disabled).active:focus { box-shadow: 0 0 0 0.2rem rgb(28, 28, 27); }"
            + "</style>"
            + "</head>"
            + "<body>"
            + "<div class=\"body py-5\">"
            + "<div class=\"container\">"
            + "<div class=\"content\">"
            + "<h1 class=\"success-header\">Thanh toán thành công</h1>"
            + "<h2 class=\"order-details-header\">Chi tiết đơn hàng</h2>"
            + "<table class=\"order-table\">"
            + "<tbody>"
            + "<tr><td>Thông tin đơn hàng:</td><td>" + orderInfo + "</td></tr>"
            + "<tr><td>Tổng tiền:</td><td>" + totalPrice/100+"$" + "</td></tr>"
            + "<tr><td>Thời gian thanh toán:</td><td>" + paymentTime + "</td></tr>"
            + "<tr><td>Mã giao dịch:</td><td>" + transactionId + "</td></tr>"
            + "</tbody>"
            + "</table>"

            + "</div>"
            + "</div>"
            + "</div>"
            + "</body>"
            + "</html>";
    return content;
}

}
