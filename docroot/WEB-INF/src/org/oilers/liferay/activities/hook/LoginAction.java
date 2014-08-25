package org.oilers.liferay.activities.hook;

import java.util.Enumeration;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

public class LoginAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
			throws ActionException {
		System.out.println("Login Hook hit.");
		try {
			User user = PortalUtil.getUser(request);
			Integer logins = (Integer)user.getExpandoBridge().getAttribute("logins");
			user.getExpandoBridge().setAttribute("logins", logins + 1);
			UserLocalServiceUtil.updateUser(user);
		} catch (PortalException | SystemException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void run(RenderRequest renderRequest, RenderResponse renderResponse)
			throws ActionException {
		super.run(renderRequest, renderResponse);
	}

}
