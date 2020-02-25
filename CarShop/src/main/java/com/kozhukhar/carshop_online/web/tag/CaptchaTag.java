package com.kozhukhar.carshop_online.web.tag;

import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.ContextDataUtil;
import com.kozhukhar.carshop_online.util.captcha.factory.CaptchaAbstractFactory;
import com.kozhukhar.carshop_online.util.captcha.factory.CaptchaProducer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CaptchaTag extends SimpleTagSupport {

    private static final Logger LOG = Logger.getLogger(CaptchaTag.class);

    public CaptchaTag() {
    }

    @Override
    public void doTag() throws IOException {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            ContextDataUtil contextData = new ContextDataUtil(request.getServletContext());

            String captchaType = contextData.getCaptchaTypeFromContext();

            CaptchaProducer captchaProducer = new CaptchaProducer();
            CaptchaAbstractFactory captchaExecutor = captchaProducer.createFactory(captchaType);
            captchaExecutor.startCaptchaExec(request, getJspContext().getOut());

        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            getJspContext().getOut().write(Messages.CAPTCHA_WAS_NOT_LOADED);
        }
    }
}
