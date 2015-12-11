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

	// 用户取消关注粉丝
	public static final String USERCANCLEFOCUSFANSINTERFACE = COMMONURL + "/user/fans/removerelationaboutuserfocusfans.action";

	// 用户查询参加活动列表接口
	public static final String USERPARTICIPATEDEXERCISEINTERFACE = COMMONURL + "/activity/user/findallparticipatedexercisesbyuserid.action";

	// 用户删除参与活动接口
	public static final String DELETEUSERPARTICIPATEDEXERCISE = COMMONURL + "/activity/user/deleteparticipatedexercisesbyid.action";

	// 获取关注的人列表接口
	public static final String FINDFOCUSLISTINTERFACE = COMMONURL + "/user/fans/finduserfocusbyuserid.action";

	// 查询所有活动接口
	public static final String FINDALLEXERCISEINTERCE = COMMONURL + "/activity/findallactivitybypage.action";

	// 参与活动接口
	public static final String PARTICIPATREXERCISEINTERFACE = COMMONURL + "/activity/user/addrelationsbetweenuserandexercise.action";

	// 根据用户id查询用户接口
	public static final String FINDSPECIFICUSERBYUSERIDINTERFACE = COMMONURL + "/user/user/finduserbyuserid.action";

	// 根据用户名查询用户userId（不是模糊查询）
	public static final String FINDUSERIDBYUSERNAMEINTERFACE = COMMONURL + "/user/user/finduserbyusername.action";

	// 查询失物接口（可不加分页参数 start，end）
	public static final String FINDUSERLOSSINFORMATIONINTERFACE = COMMONURL + "/user/loss/findlosswithpagerank.action";

	// 查询二手商品接口（可不加分页参数 start,end）
	public static final String FINDSECONDHANDINFORMATIONINTERFACE = COMMONURL + "/user/secondhand/findallsecondhandinformation.action";

	// 查询兼职招聘接口（可不加分页参数 start，end）
	public static final String FINDRECRUITINFORMATIONINTERFACE = COMMONURL + "/user/recruit/findallrecruitinformation.action";

	// 查询分类文章接口（可不加分页参数 start,end,articleType）
	public static final String FINDARTICLESBYTYPEWITHPAGERANK = COMMONURL + "/article/findarticlesbyinformationtypewithpagerank.action";

	// 根据文章id查询文章接口（articleId，此参数不加默认id为0）
	public static final String FINDARTICLEBYARTICLEIDINTERFACE = COMMONURL + "/article/findarticlesbyarticleid.action";
}
