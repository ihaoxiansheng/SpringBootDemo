package com.hao.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.hao.constant.FieldTypeConstant;
import com.hao.dto.es.CommonSearchDto;
import com.hao.dto.es.UserSearchDto;
import com.hao.entity.es.UserEntity;
import com.hao.service.EsUserService;
import com.hao.util.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户es操作service实现类
 */
@Service
@Slf4j
public class EsUserServiceImpl implements EsUserService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private RequestOptions requestOptions;

    @Override
    public String save(UserEntity user, String indexName) {
        try {
            user.setCreateTime(new Date());
            BulkRequest bulkRequest = new BulkRequest();
            // 往哪个索引下新增数据
            IndexRequest indexRequest = new IndexRequest(indexName);
            indexRequest.id(user.getId());
            // 注意：使用JSONObject.toJSONStringWithDateFormat方法防止将对象的date日期类型字段转为时间戳
            String userStr = JSONObject.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss");
            indexRequest.source(userStr, XContentType.JSON);
            //将index请求放入bulkRequest中
            bulkRequest.add(indexRequest);
            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, requestOptions);
            log.info("新增es数据响应：{}", JSONObject.toJSONString(bulkResponse));
        } catch (Exception e) {
            log.error("新增es数据错误：{}", ExceptionUtils.getStackTrace(e));
        }
        return user.getId();
    }

    @Override
    public Page<UserEntity> page(UserSearchDto dto) {
        // 创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        // 指定索引
        searchRequest.indices(dto.getIndexName());
        // 指定DSL检索条件, 构建查询语句
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 模糊查询，查询userName或remark, 此处的5是在创建索引时设置的分词大小
        BoolQueryBuilder nameQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotBlank(dto.getSearchKey()) && dto.getSearchKey().length() >= 5) {
            // 当查询的userName的长度大于等于5时，用long_char分词
            MatchPhraseQueryBuilder userName = QueryBuilders.matchPhraseQuery("userName.long_char", dto.getSearchKey());
            MatchPhraseQueryBuilder remark = QueryBuilders.matchPhraseQuery("remark.long_char", dto.getSearchKey());
            nameQueryBuilder.should(userName).should(remark);
        } else if (StringUtils.isNotBlank(dto.getSearchKey()) && dto.getSearchKey().length() < 5) {
            // 当查询的userName的长度大于等于5时，用short_char分词
            TermQueryBuilder userName = QueryBuilders.termQuery("userName.short_char", dto.getSearchKey());
            TermQueryBuilder remark = QueryBuilders.termQuery("remark.short_char", dto.getSearchKey());
            nameQueryBuilder.should(userName).should(remark);
        }
        // 创建时间查询
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("createTime");
        // 开始时间
        if (StringUtils.isNotEmpty(dto.getStartTime())) {
            // gte：大于等于；gt：大于
            rangeQuery.gte(dto.getStartTime());
        }
        // 结束时间
        if (StringUtils.isNotEmpty(dto.getEndTime())) {
            // lte:小于等于；lt:小于
            rangeQuery.lte(dto.getEndTime());
        }
        // 若开始时间和结束时间字段是必传的时候可以使用下面的查询语句
//        rangeQuery.from(dto.getStartTime()).to(dto.getEndTime());

        // 根据主键id字段进行in查询
        if (CollectionUtils.isNotEmpty(dto.getIds())) {
            BoolQueryBuilder idInQuery = QueryBuilders.boolQuery();
            idInQuery.must(QueryBuilders.termsQuery("id", dto.getIds()));
            // 将idInQuery放入最终的查询构建器中
            boolQueryBuilder.must(idInQuery);
        }
        boolQueryBuilder.must(nameQueryBuilder);
        boolQueryBuilder.must(rangeQuery);
        sourceBuilder.query(boolQueryBuilder);

        // 根据createTime排序（DESC：降序，ASC：升序）
        sourceBuilder.sort("createTime", SortOrder.DESC);
        // 分页参数
        if (ObjectUtils.isEmpty(dto.getSize()) || ObjectUtils.isEmpty(dto.getCurrent())) {
            throw new GlobalException("分页参数不可为空");
        }
        int from = dto.getSize() * (dto.getCurrent() - 1);
        sourceBuilder.from(from).size(dto.getSize());
        // sourceBuilder放入请求中
        searchRequest.source(sourceBuilder);
        // 执行检索, 拿到响应
        List<UserEntity> list = Lists.newArrayList();
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, requestOptions);
            // 打印请求响应
            log.info("es查询响应：{}", searchResponse);
            SearchHits hits = searchResponse.getHits();
            //真正命中的所有记录
            SearchHit[] searchHits = hits.getHits();
            long total = searchResponse.getHits().getTotalHits().value;
            log.info("分页数据总数：{}", total);
            for (SearchHit hit : searchHits) {
                String sourceAsString = hit.getSourceAsString();
                UserEntity entity = JSON.parseObject(sourceAsString, UserEntity.class);
                list.add(entity);
            }

            Page<UserEntity> pageVO = new Page<>();
            pageVO.setRecords(list);
            pageVO.setTotal(total);
            pageVO.setSize(dto.getSize());
            pageVO.setCurrent(dto.getCurrent());
            pageVO.setSize(total / dto.getSize());
            return pageVO;

        } catch (Exception e) {
            log.error("分页查询异常：{}", ExceptionUtils.getStackTrace(e));
        }
        return new Page<>();
    }

    @Override
    public Boolean deleteById(CommonSearchDto dto) {
        if (ObjectUtils.isEmpty(dto.getId())) {
            throw new GlobalException("id不能为空");
        }
        try {
            // 根据id先删除es数据
            DeleteRequest request = new DeleteRequest(dto.getIndexName());
            request.id(dto.getId());
            DeleteResponse delete = restHighLevelClient.delete(request, requestOptions);
            // 打印请求响应
            log.info("删除es数据响应：{}", delete);
            return true;
        } catch (Exception e) {
            log.error("删除es数据异常：{}", ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    @Override
    public Boolean update(UserEntity user, String indexName) {
        if (ObjectUtils.isEmpty(user)) {
            throw new GlobalException("更新的数据不可为空");
        }
        try {
            // 根据id修改es数据
            UpdateRequest request = new UpdateRequest();
            request.index(indexName).id(user.getId());
            request.doc(JSONObject.toJSONStringWithDateFormat(user, FieldTypeConstant.DateFormat.BASIC_FORMAT), XContentType.JSON);
            UpdateResponse updateResponse = restHighLevelClient.update(request, requestOptions);
            log.info("更新es用户数据响应：{}", updateResponse);
            return true;
        } catch (Exception e) {
            log.error("更新es中org数据失败：{}", ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    @Override
    public UserEntity getById(CommonSearchDto dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new GlobalException("id不能为空");
        }
        try {
            // 创建检索请求
            SearchRequest searchRequest = new SearchRequest();
            // 指定索引
            searchRequest.indices(dto.getIndexName());
            // 指定DSL检索条件, 构建查询语句
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            // 根据主键id，精准查询
            TermQueryBuilder builder = QueryBuilders.termQuery("id", dto.getId());
            sourceBuilder.query(builder);
            searchRequest.source(sourceBuilder);
            SearchResponse response = restHighLevelClient.search(searchRequest, requestOptions);
            log.info("请求es响应：{}", JSONObject.toJSONString(response));
            SearchHit[] hits = response.getHits().getHits();
            UserEntity entity = new UserEntity();
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                entity = JSON.parseObject(sourceAsString, UserEntity.class);
            }
            return entity;
        } catch (Exception e) {
            log.error("根据id获取用户信息异常：{}", e.getMessage());
            return new UserEntity();
        }

    }

    @Override
    public Boolean createIndexByRest(String indexName) {

        try {
            // 设置索引分词器
            XContentBuilder setBuilder = XContentFactory.jsonBuilder();
            setBuilder.startObject();
            {
                // 设置ngram分词器的最大分词数
                setBuilder.field("index.max_ngram_diff", 5);
                setBuilder.startObject("analysis");
                {
                    setBuilder.startObject("analyzer");
                    {
                        // 设置ngram_analyzer_short分词器
                        setBuilder.startObject("ngram_analyzer_short");
                        {
                            // 设置过滤器
                            setBuilder.field("filter", "lowercase");
                            // 设置分词器
                            setBuilder.field("tokenizer", "ngram_tokenizer_short");
                        }
                        setBuilder.endObject();

                        // 设置ngram_analyzer_long分词器
                        setBuilder.startObject("ngram_analyzer_long");
                        {
                            // 设置过滤器
                            setBuilder.field("filter", "lowercase");
                            // 设置分词
                            setBuilder.field("tokenizer", "ngram_tokenizer_long");
                        }
                        setBuilder.endObject();
                    }
                    setBuilder.endObject();

                    setBuilder.startObject("tokenizer");
                    {
                        // 配置ngram_tokenizer_short分词器
                        setBuilder.startObject("ngram_tokenizer_short");
                        {
                            // 设置分词器类型
                            setBuilder.field("type", "ngram");
                            //  设置分词最小粒度
                            setBuilder.field("min_gram", 1);
                            //  设置分词最大粒度
                            setBuilder.field("max_gram", 4);
                        }
                        setBuilder.endObject();

                        // 配置ngram_tokenizer_long分词器
                        setBuilder.startObject("ngram_tokenizer_long");
                        {
                            // 设置分词器类型
                            setBuilder.field("type", "ngram");
                            //  设置分词最小粒度
                            setBuilder.field("min_gram", 5);
                            //  设置分词最大粒度
                            setBuilder.field("max_gram", 5);
                        }
                        setBuilder.endObject();
                    }
                    setBuilder.endObject();
                }
                setBuilder.endObject();
            }
            setBuilder.endObject();
            // 设置映射属性
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                // 设置映射关系
                builder.startObject("mappings");
                {
                    // 设置映射属性，即设置需要的es字段
                    builder.startObject("properties");
                    {
                        // 设置主键
                        builder.startObject("id");
                        {
                            // 设置字段类型为keyword
                            builder.field("type", FieldTypeConstant.KEYWORD);
                        }
                        builder.endObject();

                        // 设置userName字段
                        builder.startObject("userName");
                        {
                            // 设置字段类型keyword
                            builder.field("type", FieldTypeConstant.KEYWORD);
                            builder.startObject("fields");
                            {
                                // 为userName设置分词
                                builder.startObject("long_char");
                                {
                                    builder.field("type", FieldTypeConstant.TEXT);
                                    // 设置分词器为ngram_analyzer_long
                                    builder.field("analyzer", "ngram_analyzer_long");
                                }
                                builder.endObject();

                                builder.startObject("short_char");
                                {
                                    builder.field("type", FieldTypeConstant.TEXT);
                                    builder.field("analyzer", "ngram_analyzer_short");
                                }
                                builder.endObject();
                            }
                            builder.endObject();
                        }
                        builder.endObject();

                        // 设置password字段
                        builder.startObject("password");
                        {
                            // 设置字段类型
                            builder.field("type", FieldTypeConstant.KEYWORD);
                        }
                        builder.endObject();

                        //设置age字段
                        builder.startObject("age");
                        {
                            // 设置字段类型
                            builder.field("type", FieldTypeConstant.INTEGER);
                        }
                        builder.endObject();

                        // 设置isLeader字段
                        builder.startObject("isLeader");
                        {
                            // 设置字段类型
                            builder.field("type", FieldTypeConstant.BOOLEAN);
                        }
                        builder.endObject();

                        // 设置角色名称集合roles字段
                        // 此字段会存储List<RoleEntity>对象
                        builder.startObject("roles");
                        {
                            // 设置字段类型
                            builder.field("type", FieldTypeConstant.OBJECT);
                        }
                        builder.endObject();

                        // 设置机构org字段
                        // 此字段会存储OrgEntity对象
                        builder.startObject("org");
                        {
                            // 设置字段类型
                            builder.field("type", FieldTypeConstant.OBJECT);
                        }
                        builder.endObject();

                        //设置remark字段
                        builder.startObject("remark");
                        {
                            // 设置字段类型
                            builder.field("type", FieldTypeConstant.KEYWORD);
                            builder.startObject("fields");
                            {
                                // 为remark设置分词
                                builder.startObject("long_char");
                                {
                                    builder.field("type", FieldTypeConstant.TEXT);
                                    builder.field("analyzer", "ngram_analyzer_long");
                                }
                                builder.endObject();

                                builder.startObject("short_char");
                                {
                                    builder.field("type", FieldTypeConstant.TEXT);
                                    builder.field("analyzer", "ngram_analyzer_short");
                                }
                                builder.endObject();
                            }
                            builder.endObject();
                        }
                        builder.endObject();

                        // 设置创建时间字段
                        builder.startObject("createTime");
                        {
                            // 设置时间类型的格式
                            builder.field("format", FieldTypeConstant.DateFormat.GROUP_FORMAT);
                            // 设置字段类型
                            builder.field("type", FieldTypeConstant.DATE);
                        }
                        builder.endObject();

                    }
                    builder.endObject();
                }
                builder.endObject();
            }
            builder.endObject();
            // 创建索引的名称
            CreateIndexRequest index = new CreateIndexRequest(indexName);
            index.settings(setBuilder);
            index.source(builder);
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(index, requestOptions);
            log.info("创建索引es返回的响应：{}", JSONObject.toJSONString(createIndexResponse));
            // 判断索引创建是否成功
            boolean acknowledged = createIndexResponse.isAcknowledged();
            return acknowledged;
        } catch (Exception e) {
            log.error("创建索引异常：{}", ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    @Override
    public Boolean existUserIndex(String indexName) {
        try {
            GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
            boolean exists = restHighLevelClient.indices().exists(getIndexRequest, requestOptions);
            log.info("索引是否存在响应：{}", exists);
            return exists;
        } catch (Exception e) {
            log.error("判断索引是否存在异常：{}", ExceptionUtils.getStackTrace(e));
            return false;
        }

    }

    @Override
    public Boolean deleteIndex(String indexName) {
        try {
            DeleteIndexRequest request = new DeleteIndexRequest(indexName);
            AcknowledgedResponse response = restHighLevelClient.indices().delete(request, requestOptions);
            boolean acknowledged = response.isAcknowledged();
            log.info("删除索引响应：{}", response);
            return acknowledged;
        } catch (Exception e) {
            log.error("删除索引异常：{}", ExceptionUtils.getStackTrace(e));
            return false;
        }

    }
}
