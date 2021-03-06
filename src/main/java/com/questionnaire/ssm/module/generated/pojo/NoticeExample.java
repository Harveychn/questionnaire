package com.questionnaire.ssm.module.generated.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoticeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table notice
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table notice
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table notice
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbggenerated
     */
    public NoticeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table notice
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andNoticeIdIsNull() {
            addCriterion("notice_id is null");
            return (Criteria) this;
        }

        public Criteria andNoticeIdIsNotNull() {
            addCriterion("notice_id is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeIdEqualTo(Long value) {
            addCriterion("notice_id =", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdNotEqualTo(Long value) {
            addCriterion("notice_id <>", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdGreaterThan(Long value) {
            addCriterion("notice_id >", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("notice_id >=", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdLessThan(Long value) {
            addCriterion("notice_id <", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdLessThanOrEqualTo(Long value) {
            addCriterion("notice_id <=", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdIn(List<Long> values) {
            addCriterion("notice_id in", values, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdNotIn(List<Long> values) {
            addCriterion("notice_id not in", values, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdBetween(Long value1, Long value2) {
            addCriterion("notice_id between", value1, value2, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdNotBetween(Long value1, Long value2) {
            addCriterion("notice_id not between", value1, value2, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleIsNull() {
            addCriterion("notice_title is null");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleIsNotNull() {
            addCriterion("notice_title is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleEqualTo(String value) {
            addCriterion("notice_title =", value, "noticeTitle");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleNotEqualTo(String value) {
            addCriterion("notice_title <>", value, "noticeTitle");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleGreaterThan(String value) {
            addCriterion("notice_title >", value, "noticeTitle");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleGreaterThanOrEqualTo(String value) {
            addCriterion("notice_title >=", value, "noticeTitle");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleLessThan(String value) {
            addCriterion("notice_title <", value, "noticeTitle");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleLessThanOrEqualTo(String value) {
            addCriterion("notice_title <=", value, "noticeTitle");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleLike(String value) {
            addCriterion("notice_title like", value, "noticeTitle");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleNotLike(String value) {
            addCriterion("notice_title not like", value, "noticeTitle");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleIn(List<String> values) {
            addCriterion("notice_title in", values, "noticeTitle");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleNotIn(List<String> values) {
            addCriterion("notice_title not in", values, "noticeTitle");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleBetween(String value1, String value2) {
            addCriterion("notice_title between", value1, value2, "noticeTitle");
            return (Criteria) this;
        }

        public Criteria andNoticeTitleNotBetween(String value1, String value2) {
            addCriterion("notice_title not between", value1, value2, "noticeTitle");
            return (Criteria) this;
        }

        public Criteria andUserTelIsNull() {
            addCriterion("user_tel is null");
            return (Criteria) this;
        }

        public Criteria andUserTelIsNotNull() {
            addCriterion("user_tel is not null");
            return (Criteria) this;
        }

        public Criteria andUserTelEqualTo(String value) {
            addCriterion("user_tel =", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelNotEqualTo(String value) {
            addCriterion("user_tel <>", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelGreaterThan(String value) {
            addCriterion("user_tel >", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelGreaterThanOrEqualTo(String value) {
            addCriterion("user_tel >=", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelLessThan(String value) {
            addCriterion("user_tel <", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelLessThanOrEqualTo(String value) {
            addCriterion("user_tel <=", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelLike(String value) {
            addCriterion("user_tel like", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelNotLike(String value) {
            addCriterion("user_tel not like", value, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelIn(List<String> values) {
            addCriterion("user_tel in", values, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelNotIn(List<String> values) {
            addCriterion("user_tel not in", values, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelBetween(String value1, String value2) {
            addCriterion("user_tel between", value1, value2, "userTel");
            return (Criteria) this;
        }

        public Criteria andUserTelNotBetween(String value1, String value2) {
            addCriterion("user_tel not between", value1, value2, "userTel");
            return (Criteria) this;
        }

        public Criteria andNoticeCreateTimeIsNull() {
            addCriterion("notice_create_time is null");
            return (Criteria) this;
        }

        public Criteria andNoticeCreateTimeIsNotNull() {
            addCriterion("notice_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeCreateTimeEqualTo(Date value) {
            addCriterion("notice_create_time =", value, "noticeCreateTime");
            return (Criteria) this;
        }

        public Criteria andNoticeCreateTimeNotEqualTo(Date value) {
            addCriterion("notice_create_time <>", value, "noticeCreateTime");
            return (Criteria) this;
        }

        public Criteria andNoticeCreateTimeGreaterThan(Date value) {
            addCriterion("notice_create_time >", value, "noticeCreateTime");
            return (Criteria) this;
        }

        public Criteria andNoticeCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("notice_create_time >=", value, "noticeCreateTime");
            return (Criteria) this;
        }

        public Criteria andNoticeCreateTimeLessThan(Date value) {
            addCriterion("notice_create_time <", value, "noticeCreateTime");
            return (Criteria) this;
        }

        public Criteria andNoticeCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("notice_create_time <=", value, "noticeCreateTime");
            return (Criteria) this;
        }

        public Criteria andNoticeCreateTimeIn(List<Date> values) {
            addCriterion("notice_create_time in", values, "noticeCreateTime");
            return (Criteria) this;
        }

        public Criteria andNoticeCreateTimeNotIn(List<Date> values) {
            addCriterion("notice_create_time not in", values, "noticeCreateTime");
            return (Criteria) this;
        }

        public Criteria andNoticeCreateTimeBetween(Date value1, Date value2) {
            addCriterion("notice_create_time between", value1, value2, "noticeCreateTime");
            return (Criteria) this;
        }

        public Criteria andNoticeCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("notice_create_time not between", value1, value2, "noticeCreateTime");
            return (Criteria) this;
        }

        public Criteria andIsDoneIsNull() {
            addCriterion("is_done is null");
            return (Criteria) this;
        }

        public Criteria andIsDoneIsNotNull() {
            addCriterion("is_done is not null");
            return (Criteria) this;
        }

        public Criteria andIsDoneEqualTo(Byte value) {
            addCriterion("is_done =", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneNotEqualTo(Byte value) {
            addCriterion("is_done <>", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneGreaterThan(Byte value) {
            addCriterion("is_done >", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_done >=", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneLessThan(Byte value) {
            addCriterion("is_done <", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneLessThanOrEqualTo(Byte value) {
            addCriterion("is_done <=", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneIn(List<Byte> values) {
            addCriterion("is_done in", values, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneNotIn(List<Byte> values) {
            addCriterion("is_done not in", values, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneBetween(Byte value1, Byte value2) {
            addCriterion("is_done between", value1, value2, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneNotBetween(Byte value1, Byte value2) {
            addCriterion("is_done not between", value1, value2, "isDone");
            return (Criteria) this;
        }

        public Criteria andNoticeLaunchTimeIsNull() {
            addCriterion("notice_launch_time is null");
            return (Criteria) this;
        }

        public Criteria andNoticeLaunchTimeIsNotNull() {
            addCriterion("notice_launch_time is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeLaunchTimeEqualTo(Date value) {
            addCriterion("notice_launch_time =", value, "noticeLaunchTime");
            return (Criteria) this;
        }

        public Criteria andNoticeLaunchTimeNotEqualTo(Date value) {
            addCriterion("notice_launch_time <>", value, "noticeLaunchTime");
            return (Criteria) this;
        }

        public Criteria andNoticeLaunchTimeGreaterThan(Date value) {
            addCriterion("notice_launch_time >", value, "noticeLaunchTime");
            return (Criteria) this;
        }

        public Criteria andNoticeLaunchTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("notice_launch_time >=", value, "noticeLaunchTime");
            return (Criteria) this;
        }

        public Criteria andNoticeLaunchTimeLessThan(Date value) {
            addCriterion("notice_launch_time <", value, "noticeLaunchTime");
            return (Criteria) this;
        }

        public Criteria andNoticeLaunchTimeLessThanOrEqualTo(Date value) {
            addCriterion("notice_launch_time <=", value, "noticeLaunchTime");
            return (Criteria) this;
        }

        public Criteria andNoticeLaunchTimeIn(List<Date> values) {
            addCriterion("notice_launch_time in", values, "noticeLaunchTime");
            return (Criteria) this;
        }

        public Criteria andNoticeLaunchTimeNotIn(List<Date> values) {
            addCriterion("notice_launch_time not in", values, "noticeLaunchTime");
            return (Criteria) this;
        }

        public Criteria andNoticeLaunchTimeBetween(Date value1, Date value2) {
            addCriterion("notice_launch_time between", value1, value2, "noticeLaunchTime");
            return (Criteria) this;
        }

        public Criteria andNoticeLaunchTimeNotBetween(Date value1, Date value2) {
            addCriterion("notice_launch_time not between", value1, value2, "noticeLaunchTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table notice
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table notice
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}