package com.kozhukhar.carshop_online.util.user_loader;

import com.kozhukhar.carshop_online.db.bean.RegUserBean;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;

public class UserLoaderUtil {

    private HttpServletRequest request;

    private Map<String, Command> commandMap;

    public UserLoaderUtil(HttpServletRequest request) {
        this.request = request;
        commandMap = new HashMap<>();
        initMap();
    }

    private void initMap() {
        commandMap.put(USERNAME, new SetUsernameCommand());
        commandMap.put(REAL_NAME, new SetRealNameCommand());
        commandMap.put(SURNAME, new SetSurnameCommand());
        commandMap.put(EMAIL, new SetEmailCommand());
        commandMap.put(PASSWORD, new SetPasswordCommand());
        commandMap.put(PASSWORD_REP, new SetPasswordRep());
        commandMap.put(CAPTCHA_KEY, new SetCaptchaCommand());
        commandMap.put(NEWS, new SetNewsCommand());
    }

    public RegUserBean initRegUser() throws AppException {
        RegUserBean regUserBean = new RegUserBean();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = upload.parseRequest(request);

            for (Object obj : items) {
                FileItem item = (FileItem) obj;

                if (item.isFormField()) {
                    initRegField(regUserBean, item);
                } else {
                    request.getSession().setAttribute(AVATAR_FILE, obj);
                }
            }
        } catch (FileUploadException e) {
            throw new AppException(Messages.LOAD_DATA_EXCEPTION);
        }
        return regUserBean;
    }

    private void initRegField(RegUserBean regUserBean, FileItem item) throws AppException {
        String name = item.getFieldName();
        String value = item.getString();

        Command cmd = commandMap.get(name);
        if (cmd != null) {
            cmd.init(regUserBean, value);
        }
    }

}
