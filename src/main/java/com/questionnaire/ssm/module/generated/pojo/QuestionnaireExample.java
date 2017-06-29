package com.questionnaire.ssm.module.generated.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionnaireExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table questionnaire
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table questionnaire
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table questionnaire
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questionnaire
     *
     * @mbggenerated
     */
    public QuestionnaireExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questionnaire
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questionnaire
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questionnaire
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questionnaire
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questionnaire
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questionnaire
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questionnaire
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
     * This method corresponds to the database table questionnaire
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
     * This method corresponds to the database table questionnaire
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questionnaire
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
     * This class corresponds to the database table questionnaire
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

        public Criteria andQuestionnaireIdIsNull() {
            addCriterion("questionnaire_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdIsNotNull() {
            addCriterion("questionnaire_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdEqualTo(Long value) {
            addCriterion("questionnaire_id =", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdNotEqualTo(Long value) {
            addCriterion("questionnaire_id <>", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdGreaterThan(Long value) {
            addCriterion("questionnaire_id >", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdGreaterThanOrEqualTo(Long value) {
            addCriterion("questionnaire_id >=", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdLessThan(Long value) {
            addCriterion("questionnaire_id <", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdLessThanOrEqualTo(Long value) {
            addCriterion("questionnaire_id <=", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdIn(List<Long> values) {
            addCriterion("questionnaire_id in", values, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdNotIn(List<Long> values) {
            addCriterion("questionnaire_id not in", values, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdBetween(Long value1, Long value2) {
            addCriterion("questionnaire_id between", value1, value2, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdNotBetween(Long value1, Long value2) {
            addCriterion("questionnaire_id not between", value1, value2, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleIsNull() {
            addCriterion("questionnaire_title is null");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleIsNotNull() {
            addCriterion("questionnaire_title is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleEqualTo(String value) {
            addCriterion("questionnaire_title =", value, "questionnaireTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleNotEqualTo(String value) {
            addCriterion("questionnaire_title <>", value, "questionnaireTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleGreaterThan(String value) {
            addCriterion("questionnaire_title >", value, "questionnaireTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleGreaterThanOrEqualTo(String value) {
            addCriterion("questionnaire_title >=", value, "questionnaireTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleLessThan(String value) {
            addCriterion("questionnaire_title <", value, "questionnaireTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleLessThanOrEqualTo(String value) {
            addCriterion("questionnaire_title <=", value, "questionnaireTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleLike(String value) {
            addCriterion("questionnaire_title like", value, "questionnaireTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleNotLike(String value) {
            addCriterion("questionnaire_title not like", value, "questionnaireTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleIn(List<String> values) {
            addCriterion("questionnaire_title in", values, "questionnaireTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleNotIn(List<String> values) {
            addCriterion("questionnaire_title not in", values, "questionnaireTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleBetween(String value1, String value2) {
            addCriterion("questionnaire_title between", value1, value2, "questionnaireTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireTitleNotBetween(String value1, String value2) {
            addCriterion("questionnaire_title not between", value1, value2, "questionnaireTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleIsNull() {
            addCriterion("questionnaire_subtitle is null");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleIsNotNull() {
            addCriterion("questionnaire_subtitle is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleEqualTo(String value) {
            addCriterion("questionnaire_subtitle =", value, "questionnaireSubtitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleNotEqualTo(String value) {
            addCriterion("questionnaire_subtitle <>", value, "questionnaireSubtitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleGreaterThan(String value) {
            addCriterion("questionnaire_subtitle >", value, "questionnaireSubtitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleGreaterThanOrEqualTo(String value) {
            addCriterion("questionnaire_subtitle >=", value, "questionnaireSubtitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleLessThan(String value) {
            addCriterion("questionnaire_subtitle <", value, "questionnaireSubtitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleLessThanOrEqualTo(String value) {
            addCriterion("questionnaire_subtitle <=", value, "questionnaireSubtitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleLike(String value) {
            addCriterion("questionnaire_subtitle like", value, "questionnaireSubtitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleNotLike(String value) {
            addCriterion("questionnaire_subtitle not like", value, "questionnaireSubtitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleIn(List<String> values) {
            addCriterion("questionnaire_subtitle in", values, "questionnaireSubtitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleNotIn(List<String> values) {
            addCriterion("questionnaire_subtitle not in", values, "questionnaireSubtitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleBetween(String value1, String value2) {
            addCriterion("questionnaire_subtitle between", value1, value2, "questionnaireSubtitle");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireSubtitleNotBetween(String value1, String value2) {
            addCriterion("questionnaire_subtitle not between", value1, value2, "questionnaireSubtitle");
            return (Criteria) this;
        }

        public Criteria andIsTemplateIsNull() {
            addCriterion("is_template is null");
            return (Criteria) this;
        }

        public Criteria andIsTemplateIsNotNull() {
            addCriterion("is_template is not null");
            return (Criteria) this;
        }

        public Criteria andIsTemplateEqualTo(Boolean value) {
            addCriterion("is_template =", value, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateNotEqualTo(Boolean value) {
            addCriterion("is_template <>", value, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateGreaterThan(Boolean value) {
            addCriterion("is_template >", value, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_template >=", value, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateLessThan(Boolean value) {
            addCriterion("is_template <", value, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateLessThanOrEqualTo(Boolean value) {
            addCriterion("is_template <=", value, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateIn(List<Boolean> values) {
            addCriterion("is_template in", values, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateNotIn(List<Boolean> values) {
            addCriterion("is_template not in", values, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateBetween(Boolean value1, Boolean value2) {
            addCriterion("is_template between", value1, value2, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_template not between", value1, value2, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsShareIsNull() {
            addCriterion("is_share is null");
            return (Criteria) this;
        }

        public Criteria andIsShareIsNotNull() {
            addCriterion("is_share is not null");
            return (Criteria) this;
        }

        public Criteria andIsShareEqualTo(Boolean value) {
            addCriterion("is_share =", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareNotEqualTo(Boolean value) {
            addCriterion("is_share <>", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareGreaterThan(Boolean value) {
            addCriterion("is_share >", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_share >=", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareLessThan(Boolean value) {
            addCriterion("is_share <", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareLessThanOrEqualTo(Boolean value) {
            addCriterion("is_share <=", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareIn(List<Boolean> values) {
            addCriterion("is_share in", values, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareNotIn(List<Boolean> values) {
            addCriterion("is_share not in", values, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareBetween(Boolean value1, Boolean value2) {
            addCriterion("is_share between", value1, value2, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_share not between", value1, value2, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsVisibleIsNull() {
            addCriterion("is_visible is null");
            return (Criteria) this;
        }

        public Criteria andIsVisibleIsNotNull() {
            addCriterion("is_visible is not null");
            return (Criteria) this;
        }

        public Criteria andIsVisibleEqualTo(Boolean value) {
            addCriterion("is_visible =", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleNotEqualTo(Boolean value) {
            addCriterion("is_visible <>", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleGreaterThan(Boolean value) {
            addCriterion("is_visible >", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_visible >=", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleLessThan(Boolean value) {
            addCriterion("is_visible <", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleLessThanOrEqualTo(Boolean value) {
            addCriterion("is_visible <=", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleIn(List<Boolean> values) {
            addCriterion("is_visible in", values, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleNotIn(List<Boolean> values) {
            addCriterion("is_visible not in", values, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleBetween(Boolean value1, Boolean value2) {
            addCriterion("is_visible between", value1, value2, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_visible not between", value1, value2, "isVisible");
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

        public Criteria andIsDoneEqualTo(Boolean value) {
            addCriterion("is_done =", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneNotEqualTo(Boolean value) {
            addCriterion("is_done <>", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneGreaterThan(Boolean value) {
            addCriterion("is_done >", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_done >=", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneLessThan(Boolean value) {
            addCriterion("is_done <", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneLessThanOrEqualTo(Boolean value) {
            addCriterion("is_done <=", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneIn(List<Boolean> values) {
            addCriterion("is_done in", values, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneNotIn(List<Boolean> values) {
            addCriterion("is_done not in", values, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneBetween(Boolean value1, Boolean value2) {
            addCriterion("is_done between", value1, value2, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_done not between", value1, value2, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsReleaseIsNull() {
            addCriterion("is_release is null");
            return (Criteria) this;
        }

        public Criteria andIsReleaseIsNotNull() {
            addCriterion("is_release is not null");
            return (Criteria) this;
        }

        public Criteria andIsReleaseEqualTo(Boolean value) {
            addCriterion("is_release =", value, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseNotEqualTo(Boolean value) {
            addCriterion("is_release <>", value, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseGreaterThan(Boolean value) {
            addCriterion("is_release >", value, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_release >=", value, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseLessThan(Boolean value) {
            addCriterion("is_release <", value, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseLessThanOrEqualTo(Boolean value) {
            addCriterion("is_release <=", value, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseIn(List<Boolean> values) {
            addCriterion("is_release in", values, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseNotIn(List<Boolean> values) {
            addCriterion("is_release not in", values, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseBetween(Boolean value1, Boolean value2) {
            addCriterion("is_release between", value1, value2, "isRelease");
            return (Criteria) this;
        }

        public Criteria andIsReleaseNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_release not between", value1, value2, "isRelease");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table questionnaire
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
     * This class corresponds to the database table questionnaire
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