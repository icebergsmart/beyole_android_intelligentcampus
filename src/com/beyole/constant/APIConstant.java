package com.beyole.constant;

public class APIConstant {

	public static final String COMMONURL = Constant.REMOTESERVER + ":" + Constant.REMOTESERVERPORT + "/" + Constant.REMOTESERVERPROJECTNAME;
	// 登录访问接口
	public static final String LOGININTERFACE = COMMONURL + "/user/login.action";

	// 注册访问接口
	public static final String REGISTERINTERFACE = COMMONURL + "/user/register.action";

	// 首页新闻接口
	public static final String HOMEARTICLEINTERFACE = COMMONURL + "/article/findapparticles.action";

	// 获取用户粉丝数和关注数
	public static final String FINDUSERSFANSANDFOCUSNUMBER = COMMONURL + "/user/fans/findusersfansandfocus.action";

	// 获取公告信息
	public static final String GETNOTIFICATIONNOTICE = COMMONURL + "/notification/findallcommonusernotifications.action";

	// 获取用户参加活动数
	public static final String GETUSERACTIVITYCOUNT = COMMONURL + "/activity/user/findactivityusersbyuserid.action";

	// 意见反馈接口
	public static final String DEVICEFEEDBACKINTERFACE = COMMONURL + "/feedback/device/adddevicefeedback.action";

	// 获取粉丝列表
	public static final String FINDFANSLISTINTERFACE = COMMONURL + "/user/fans/finduserfansbyuserid.action";

	// 用户关注粉丝
	public static final String USERFOCUSFANSINTERFACE = COMMONURL + "/user/fans/addrelationaboutuserfocusfans.action";

	//用户取消关注粉丝
	public static final String USERCANCLEFOCUSFANSINTERFACE =COMMONURL+"/user/fans/removerelationaboutuserfocusfans.action";
}
