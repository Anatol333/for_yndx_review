package com.kozhukhar.carshop_online.util.user_loader;

import com.kozhukhar.carshop_online.db.bean.RegUserBean;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;

public class SetPasswordCommand implements Command {
    @Override
    public void init(Object object, String value) throws AppException {
        try {
            RegUserBean regUserBean = (RegUserBean) object;
            regUserBean.setPassword(value);
        } catch (Exception ex) {
            throw new AppException(Messages.CANNOT_SET_ATTRIBUTE_USER);
        }
    }
}
