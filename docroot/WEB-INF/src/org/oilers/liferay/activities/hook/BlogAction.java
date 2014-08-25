package org.oilers.liferay.activities.hook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

public class BlogAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
			throws ActionException {
		System.out.println("Blog Hook hit.");
		try {
			User user = PortalUtil.getUser(request);
			Integer blogs = (Integer)user.getExpandoBridge().getAttribute("blogs");
			user.getExpandoBridge().setAttribute("blogs", blogs + 1);
			UserLocalServiceUtil.updateUser(user);
		} catch (PortalException | SystemException e) {
			e.printStackTrace();
		}

	}

}
