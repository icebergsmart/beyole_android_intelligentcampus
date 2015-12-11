package com.beyole.constant;

/**
 * 文章查询返回结果集
 * 
 * @date 2015/12/10
 * @author Iceberg
 * 
 */
public class ArticleConstant {

	// 查询分类文章成功
	public static final int QUERY_FOR_ARTICLE_BY_TYPE_SUCCESS = 15001;
	// 查询分类文章成功|没有结果集
	public static final int QUERY_FOR_ARTICLE_BY_TYPE_SUCCESS_WITHOUT_RESULT = 15002;
	// 查询分类文章失败|处理异常
	public static final int QUERY_FOR_ARTICLE_BY_TYPE_ERROR = 15003;
	// 查询分类文章失败|服务器异常
	public static final int QUERY_FOR_ARTICLE_BY_TYPE_ERROR_WITH_EXCEPTION = 15004;
	// 根据id查询文章成功
	public static final int QUERY_FOR_ARTICLE_BY_ID_SUCCESS = 15005;
	// 根据id查询文章成功|没有此文章
	public static final int QUERY_FOR_ARTICLE_BY_ID_SUCCESS_WITHOUT_RESULT = 15006;
	// 根据id查询文章失败|处理异常
	public static final int QUERY_FOR_ARTICLE_BY_ID_ERROR = 15007;
	// 根据id查询文章失败|服务器异常
	public static final int QUERY_FOR_ARTICLE_BY_ID_ERROR_WITH_EXCEPTION = 15008;
}
