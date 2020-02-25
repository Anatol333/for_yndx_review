package com.kozhukhar.carshop_online.web.controller;

import com.kozhukhar.carshop_online.db.bean.RegUserBean;
import com.kozhukhar.carshop_online.db.dto.User;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.util.user_loader.UserLoaderUtil;
import com.kozhukhar.carshop_online.util.validate.UserValidator;
import com.kozhukhar.carshop_online.web.resource_tag.PagePaths;
import com.kozhukhar.carshop_online.web.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.kozhukhar.carshop_online.web.resource_tag.FieldTags.*;
import static com.kozhukhar.carshop_online.web.resource_tag.JspPaths.JSP_REG;

@WebServlet(PagePaths.REG_PAGE)
@MultipartConfig
public class RegController extends HttpServlet {

    private UserService userService;

    private static final String USERNAME_REG = "usernameReg";
    private static final String EMAIL_REG = "emailReg";
    private static final Logger LOG = Logger.getLogger(RegController.class);


    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute(USER_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        HttpSession session = req.getSession();
        RegUserBean userBean;

        try {
            userBean = new UserLoaderUtil(req).initRegUser();

            saveRegFieldsToSession(session, userBean);
            validateUser(userBean, req);
            addUser(userBean.getUserEntity(), req);

            session.setAttribute(SUCCESS_MSG, Messages.ACCOUNT_WAS_CREATED);
        } catch (AppException ex) {
            LOG.error(ex.getMessage());
            session.setAttribute(ERROR_MSG, ex.getMessage());
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            session.setAttribute(ERROR_MSG, Messages.UNKNOWN_EXCEPTION_REGISTRATION);
        }

        res.sendRedirect(req.getContextPath() + PagePaths.REG_PAGE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        req.getRequestDispatcher(JSP_REG).forward(req, res);

        HttpSession session = req.getSession();
        session.removeAttribute(ERROR_MSG);
        session.removeAttribute(SUCCESS_MSG);
    }

    private void saveRegFieldsToSession(HttpSession session, RegUserBean userBean) {
        session.setAttribute(USERNAME_REG, userBean.getUsername());
        session.setAttribute(EMAIL_REG, userBean.getEmail());
        session.setAttribute(REAL_NAME, userBean.getName());
        session.setAttribute(SURNAME, userBean.getSurname());
    }

    private void validateUser(RegUserBean userBean, HttpServletRequest req) throws AppException {
        UserValidator validator = new UserValidator(userBean, req);
        List<String> errors = validator.validateRegUser();

        if (errors.size() > 0) {
            throw new AppException(String.join(SPACE_STR, errors));
        }
    }

    private void addUser(User userEntity, HttpServletRequest request) throws AppException {
        try {
            userService.saveUser(userEntity);
            saveAvatar(userEntity, request);

        } catch (Exception ex) {
            throw new AppException(Messages.DB_USER_NOT_REGISTERED);
        }
    }

    private void saveAvatar(User userEntity, HttpServletRequest request) throws Exception {
        FileItem item = (FileItem) request.getSession().getAttribute(AVATAR_FILE);
        if (!item.isFormField()) {
            String name = userEntity.getUsername();
            String path = request.getServletContext().getRealPath(AVATAR_DIR + name + IMG_FORMAT);

            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            item.write(file);
        }
    }

}
