package com.questionnaire.ssm.module.resultAnalysis.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 郑晓辉 on 2017/7/23.
 * Description: 导出的文本数据DTO类
 */
public class ExportTxtDataDTO {

    // excel Sheet Data数据结构
    private transient Map<KeySetNode, List<ValueNode>> sheetDataMap;

    public ExportTxtDataDTO(List<AnswerTxtExportDO> answerTxtExportDOList) {
        this.sheetDataMap = new HashMap<>();
        orgData(answerTxtExportDOList);
    }

    /**
     * 组织数据为Map结构
     *
     * @param doList
     */
    private void orgData(List<AnswerTxtExportDO> doList) {
        if (doList == null || doList.size() <= 0) {
            return;
        }
        KeySetNode keySetNode = null;
        ValueNode curValueNode = null;
        List<ValueNode> valueNodeList = null;
        for (AnswerTxtExportDO curObj : doList) {
            KeySetNode curKeyNode = new KeySetNode(curObj.getQuestionId(), curObj.getQuestionContext());
            curValueNode = new ValueNode(curObj.getAnswerPaperId(), curObj.getAnswerStr());

            if (keySetNode == null) {
                keySetNode = curKeyNode;
                valueNodeList = new ArrayList<>();
                valueNodeList.add(curValueNode);
                continue;
            }

            //equals判断条件为 qesID以及qesContent相同
            if (keySetNode.equals(curKeyNode)) {
                valueNodeList.add(curValueNode);
                continue;
            }

            sheetDataMap.put(keySetNode, valueNodeList);
            keySetNode = curKeyNode;
            valueNodeList = new ArrayList<>();
            valueNodeList.add(curValueNode);
        }
    }

    public Map<KeySetNode, List<ValueNode>> getSheetDataMap() {
        return sheetDataMap;
    }

    public int getSize() {
        return sheetDataMap.size();
    }

    //Map的kay Set元素
    public class KeySetNode {
        private Long qesID;
        private String qesContent;

        public KeySetNode(Long qesID, String qesContent) {
            this.qesID = qesID;
            this.qesContent = qesContent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            KeySetNode keyNode = (KeySetNode) o;

            if (qesID != null ? !qesID.equals(keyNode.qesID) : keyNode.qesID != null) return false;
            return qesContent != null ? qesContent.equals(keyNode.qesContent) : keyNode.qesContent == null;
        }

        @Override
        public int hashCode() {
            int result = qesID != null ? qesID.hashCode() : 0;
            result = 31 * result + (qesContent != null ? qesContent.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "KeySetNode{" +
                    "qesID=" + qesID +
                    ", qesContent='" + qesContent + '\'' +
                    '}';
        }

        public Long getQesID() {
            return qesID;
        }

        public void setQesID(Long qesID) {
            this.qesID = qesID;
        }

        public String getQesContent() {
            return qesContent;
        }

        public void setQesContent(String qesContent) {
            this.qesContent = qesContent;
        }
    }

    //值节点
    public class ValueNode {
        private Long answerPaperID;
        private String answerStr;

        public ValueNode(Long answerPaperID, String answerStr) {
            this.answerPaperID = answerPaperID;
            this.answerStr = answerStr;
        }

        @Override
        public String toString() {
            return "ValueNode{" +
                    "answerPaperID=" + answerPaperID +
                    ", answerStr='" + answerStr + '\'' +
                    '}';
        }

        public Long getAnswerPaperID() {
            return answerPaperID;
        }

        public void setAnswerPaperID(Long answerPaperID) {
            this.answerPaperID = answerPaperID;
        }

        public String getAnswerStr() {
            return answerStr;
        }

        public void setAnswerStr(String answerStr) {
            this.answerStr = answerStr;
        }
    }

}
