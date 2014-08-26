package org.oilers.liferay.activities.hook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;

public class LoginAction extends Action {

	private static final String LOGIN_PROPERTY = "logins";
	private static final Log log = LogFactoryUtil.getLog(LoginAction.class);

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
			throws ActionException {
		log.info("*********************************************");
		log.info("* Login Hook hit.                           *");
		log.info("*********************************************");
		try {
			User user = PortalUtil.getUser(request);
			if(!user.getExpandoBridge().hasAttribute(LOGIN_PROPERTY)) {
				user.getExpandoBridge().addAttribute(LOGIN_PROPERTY, ExpandoColumnConstants.INTEGER, 0);
			}
			Integer logins = (Integer)user.getExpandoBridge().getAttribute(LOGIN_PROPERTY);
			user.getExpandoBridge().setAttribute(LOGIN_PROPERTY, logins + 1);
			UserLocalServiceUtil.updateUser(user);
		} catch (Exception e) {
			log.error(e);
		}
	}


}
