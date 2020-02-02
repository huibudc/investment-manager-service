package investment.utils;

import investment.foundation.modal.Foundation;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailUtils {
    private final static TemplateEngine templateEngine = new TemplateEngine();
    static {
        templateEngine.setTemplateResolver(new ClassLoaderTemplateResolver());
    }

    public static String renderFoundationEmailContent(List<Foundation> foundations) {
        Map<String, Object> params = new HashMap<>();
        params.put("list", foundations);
        Context context = new Context();
        context.setVariables(params);
        return templateEngine.process("/templates/FoundationEmailContent.html", context);
    }
}
