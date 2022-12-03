package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.user.UserSessionUtils;
import model.*;
import model.service.*;

public class HomeController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/* 로그인 여부 확인 */
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			/* 로그인 X */
            request.setAttribute("noLogin", true);
        } else {
        	/* 로그인 O */
        	request.setAttribute("hasLogin", true);
        	request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));
        }
		
		/* 랭킹 받아옴 */
		RecommendManager manager = RecommendManager.getInstance();
		List<Rank> hotRank = manager.hotRank();
		List<Rank> overallRank = manager.overallRank();
		
		/* 랭킹 결과 전달 */
		request.setAttribute("hotRank", hotRank);
		request.setAttribute("overallRank", overallRank);
		
		return "/home/home.jsp";
	}
}