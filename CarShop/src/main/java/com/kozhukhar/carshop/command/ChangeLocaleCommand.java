package com.kozhukhar.carshop.command;

import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop.util.LocaleMessageUtil;

public class ChangeLocaleCommand implements Command {

    private String localization;

    private ServiceContext serviceContext;

    public ChangeLocaleCommand(ServiceContext serviceContext, String secondCommand) {
        this.serviceContext = serviceContext;
        localization = secondCommand;
    }

    @Override
    public String execute() throws AppException {
        LocaleMessageUtil localeMessageUtil = new LocaleMessageUtil();
        localeMessageUtil.setLocale(localization);
        serviceContext.setLocaleMessageUtil(localeMessageUtil);
        serviceContext.getCreatorAnnotation().setLocaleMessageUtil(localeMessageUtil);
        return serviceContext.getLocaleMessageUtil().localizeMessage(Messages.LOCALE_TEST);
    }
}
