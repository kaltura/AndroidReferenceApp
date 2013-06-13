// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platfroms allow them to do with
// text.
//
// Copyright (C) 2006-2011  Kaltura Inc.
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as
// published by the Free Software Foundation, either version 3 of the
// License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
// @ignore
// ===================================================================================================
package com.kaltura.client.types;

import org.w3c.dom.Element;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.enums.KalturaBatchJobType;
import com.kaltura.client.enums.KalturaBatchJobStatus;
import com.kaltura.client.enums.KalturaBatchJobErrorTypes;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Tue, 09 Apr 13 06:52:58 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public abstract class KalturaBatchJobBaseFilter extends KalturaFilter {
    public int idEqual = Integer.MIN_VALUE;
    public int idGreaterThanOrEqual = Integer.MIN_VALUE;
    public int partnerIdEqual = Integer.MIN_VALUE;
    public String partnerIdIn;
    public String partnerIdNotIn;
    public int createdAtGreaterThanOrEqual = Integer.MIN_VALUE;
    public int createdAtLessThanOrEqual = Integer.MIN_VALUE;
    public int updatedAtGreaterThanOrEqual = Integer.MIN_VALUE;
    public int updatedAtLessThanOrEqual = Integer.MIN_VALUE;
    public int lockExpirationGreaterThanOrEqual = Integer.MIN_VALUE;
    public int lockExpirationLessThanOrEqual = Integer.MIN_VALUE;
    public int executionAttemptsGreaterThanOrEqual = Integer.MIN_VALUE;
    public int executionAttemptsLessThanOrEqual = Integer.MIN_VALUE;
    public int lockVersionGreaterThanOrEqual = Integer.MIN_VALUE;
    public int lockVersionLessThanOrEqual = Integer.MIN_VALUE;
    public String entryIdEqual;
    public KalturaBatchJobType jobTypeEqual;
    public String jobTypeIn;
    public String jobTypeNotIn;
    public int jobSubTypeEqual = Integer.MIN_VALUE;
    public String jobSubTypeIn;
    public String jobSubTypeNotIn;
    public KalturaBatchJobStatus statusEqual;
    public String statusIn;
    public String statusNotIn;
    public int abortEqual = Integer.MIN_VALUE;
    public int checkAgainTimeoutGreaterThanOrEqual = Integer.MIN_VALUE;
    public int checkAgainTimeoutLessThanOrEqual = Integer.MIN_VALUE;
    public int priorityGreaterThanOrEqual = Integer.MIN_VALUE;
    public int priorityLessThanOrEqual = Integer.MIN_VALUE;
    public int priorityEqual = Integer.MIN_VALUE;
    public String priorityIn;
    public String priorityNotIn;
    public int bulkJobIdEqual = Integer.MIN_VALUE;
    public String bulkJobIdIn;
    public String bulkJobIdNotIn;
    public int parentJobIdEqual = Integer.MIN_VALUE;
    public String parentJobIdIn;
    public String parentJobIdNotIn;
    public int rootJobIdEqual = Integer.MIN_VALUE;
    public String rootJobIdIn;
    public String rootJobIdNotIn;
    public int queueTimeGreaterThanOrEqual = Integer.MIN_VALUE;
    public int queueTimeLessThanOrEqual = Integer.MIN_VALUE;
    public int finishTimeGreaterThanOrEqual = Integer.MIN_VALUE;
    public int finishTimeLessThanOrEqual = Integer.MIN_VALUE;
    public KalturaBatchJobErrorTypes errTypeEqual;
    public String errTypeIn;
    public String errTypeNotIn;
    public int errNumberEqual = Integer.MIN_VALUE;
    public String errNumberIn;
    public String errNumberNotIn;
    public int estimatedEffortLessThan = Integer.MIN_VALUE;
    public int estimatedEffortGreaterThan = Integer.MIN_VALUE;
    public int schedulerIdEqual = Integer.MIN_VALUE;
    public String schedulerIdIn;
    public String schedulerIdNotIn;
    public int workerIdEqual = Integer.MIN_VALUE;
    public String workerIdIn;
    public String workerIdNotIn;
    public int batchIndexEqual = Integer.MIN_VALUE;
    public String batchIndexIn;
    public String batchIndexNotIn;
    public int lastSchedulerIdEqual = Integer.MIN_VALUE;
    public String lastSchedulerIdIn;
    public String lastSchedulerIdNotIn;
    public int lastWorkerIdEqual = Integer.MIN_VALUE;
    public String lastWorkerIdIn;
    public String lastWorkerIdNotIn;
    public int dcEqual = Integer.MIN_VALUE;
    public String dcIn;
    public String dcNotIn;

    public KalturaBatchJobBaseFilter() {
    }

    public KalturaBatchJobBaseFilter(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("idEqual")) {
                this.idEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("idGreaterThanOrEqual")) {
                this.idGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("partnerIdEqual")) {
                this.partnerIdEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("partnerIdIn")) {
                this.partnerIdIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("partnerIdNotIn")) {
                this.partnerIdNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("createdAtGreaterThanOrEqual")) {
                this.createdAtGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("createdAtLessThanOrEqual")) {
                this.createdAtLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("updatedAtGreaterThanOrEqual")) {
                this.updatedAtGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("updatedAtLessThanOrEqual")) {
                this.updatedAtLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("lockExpirationGreaterThanOrEqual")) {
                this.lockExpirationGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("lockExpirationLessThanOrEqual")) {
                this.lockExpirationLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("executionAttemptsGreaterThanOrEqual")) {
                this.executionAttemptsGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("executionAttemptsLessThanOrEqual")) {
                this.executionAttemptsLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("lockVersionGreaterThanOrEqual")) {
                this.lockVersionGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("lockVersionLessThanOrEqual")) {
                this.lockVersionLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("entryIdEqual")) {
                this.entryIdEqual = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("jobTypeEqual")) {
                this.jobTypeEqual = KalturaBatchJobType.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("jobTypeIn")) {
                this.jobTypeIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("jobTypeNotIn")) {
                this.jobTypeNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("jobSubTypeEqual")) {
                this.jobSubTypeEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("jobSubTypeIn")) {
                this.jobSubTypeIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("jobSubTypeNotIn")) {
                this.jobSubTypeNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("statusEqual")) {
                this.statusEqual = KalturaBatchJobStatus.get(ParseUtils.parseInt(txt));
                continue;
            } else if (nodeName.equals("statusIn")) {
                this.statusIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("statusNotIn")) {
                this.statusNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("abortEqual")) {
                this.abortEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("checkAgainTimeoutGreaterThanOrEqual")) {
                this.checkAgainTimeoutGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("checkAgainTimeoutLessThanOrEqual")) {
                this.checkAgainTimeoutLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("priorityGreaterThanOrEqual")) {
                this.priorityGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("priorityLessThanOrEqual")) {
                this.priorityLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("priorityEqual")) {
                this.priorityEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("priorityIn")) {
                this.priorityIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("priorityNotIn")) {
                this.priorityNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("bulkJobIdEqual")) {
                this.bulkJobIdEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("bulkJobIdIn")) {
                this.bulkJobIdIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("bulkJobIdNotIn")) {
                this.bulkJobIdNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("parentJobIdEqual")) {
                this.parentJobIdEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("parentJobIdIn")) {
                this.parentJobIdIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("parentJobIdNotIn")) {
                this.parentJobIdNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("rootJobIdEqual")) {
                this.rootJobIdEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("rootJobIdIn")) {
                this.rootJobIdIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("rootJobIdNotIn")) {
                this.rootJobIdNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("queueTimeGreaterThanOrEqual")) {
                this.queueTimeGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("queueTimeLessThanOrEqual")) {
                this.queueTimeLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("finishTimeGreaterThanOrEqual")) {
                this.finishTimeGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("finishTimeLessThanOrEqual")) {
                this.finishTimeLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("errTypeEqual")) {
                this.errTypeEqual = KalturaBatchJobErrorTypes.get(ParseUtils.parseInt(txt));
                continue;
            } else if (nodeName.equals("errTypeIn")) {
                this.errTypeIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("errTypeNotIn")) {
                this.errTypeNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("errNumberEqual")) {
                this.errNumberEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("errNumberIn")) {
                this.errNumberIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("errNumberNotIn")) {
                this.errNumberNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("estimatedEffortLessThan")) {
                this.estimatedEffortLessThan = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("estimatedEffortGreaterThan")) {
                this.estimatedEffortGreaterThan = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("schedulerIdEqual")) {
                this.schedulerIdEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("schedulerIdIn")) {
                this.schedulerIdIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("schedulerIdNotIn")) {
                this.schedulerIdNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("workerIdEqual")) {
                this.workerIdEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("workerIdIn")) {
                this.workerIdIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("workerIdNotIn")) {
                this.workerIdNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("batchIndexEqual")) {
                this.batchIndexEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("batchIndexIn")) {
                this.batchIndexIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("batchIndexNotIn")) {
                this.batchIndexNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("lastSchedulerIdEqual")) {
                this.lastSchedulerIdEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("lastSchedulerIdIn")) {
                this.lastSchedulerIdIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("lastSchedulerIdNotIn")) {
                this.lastSchedulerIdNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("lastWorkerIdEqual")) {
                this.lastWorkerIdEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("lastWorkerIdIn")) {
                this.lastWorkerIdIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("lastWorkerIdNotIn")) {
                this.lastWorkerIdNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("dcEqual")) {
                this.dcEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("dcIn")) {
                this.dcIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("dcNotIn")) {
                this.dcNotIn = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaBatchJobBaseFilter");
        kparams.add("idEqual", this.idEqual);
        kparams.add("idGreaterThanOrEqual", this.idGreaterThanOrEqual);
        kparams.add("partnerIdEqual", this.partnerIdEqual);
        kparams.add("partnerIdIn", this.partnerIdIn);
        kparams.add("partnerIdNotIn", this.partnerIdNotIn);
        kparams.add("createdAtGreaterThanOrEqual", this.createdAtGreaterThanOrEqual);
        kparams.add("createdAtLessThanOrEqual", this.createdAtLessThanOrEqual);
        kparams.add("updatedAtGreaterThanOrEqual", this.updatedAtGreaterThanOrEqual);
        kparams.add("updatedAtLessThanOrEqual", this.updatedAtLessThanOrEqual);
        kparams.add("lockExpirationGreaterThanOrEqual", this.lockExpirationGreaterThanOrEqual);
        kparams.add("lockExpirationLessThanOrEqual", this.lockExpirationLessThanOrEqual);
        kparams.add("executionAttemptsGreaterThanOrEqual", this.executionAttemptsGreaterThanOrEqual);
        kparams.add("executionAttemptsLessThanOrEqual", this.executionAttemptsLessThanOrEqual);
        kparams.add("lockVersionGreaterThanOrEqual", this.lockVersionGreaterThanOrEqual);
        kparams.add("lockVersionLessThanOrEqual", this.lockVersionLessThanOrEqual);
        kparams.add("entryIdEqual", this.entryIdEqual);
        kparams.add("jobTypeEqual", this.jobTypeEqual);
        kparams.add("jobTypeIn", this.jobTypeIn);
        kparams.add("jobTypeNotIn", this.jobTypeNotIn);
        kparams.add("jobSubTypeEqual", this.jobSubTypeEqual);
        kparams.add("jobSubTypeIn", this.jobSubTypeIn);
        kparams.add("jobSubTypeNotIn", this.jobSubTypeNotIn);
        kparams.add("statusEqual", this.statusEqual);
        kparams.add("statusIn", this.statusIn);
        kparams.add("statusNotIn", this.statusNotIn);
        kparams.add("abortEqual", this.abortEqual);
        kparams.add("checkAgainTimeoutGreaterThanOrEqual", this.checkAgainTimeoutGreaterThanOrEqual);
        kparams.add("checkAgainTimeoutLessThanOrEqual", this.checkAgainTimeoutLessThanOrEqual);
        kparams.add("priorityGreaterThanOrEqual", this.priorityGreaterThanOrEqual);
        kparams.add("priorityLessThanOrEqual", this.priorityLessThanOrEqual);
        kparams.add("priorityEqual", this.priorityEqual);
        kparams.add("priorityIn", this.priorityIn);
        kparams.add("priorityNotIn", this.priorityNotIn);
        kparams.add("bulkJobIdEqual", this.bulkJobIdEqual);
        kparams.add("bulkJobIdIn", this.bulkJobIdIn);
        kparams.add("bulkJobIdNotIn", this.bulkJobIdNotIn);
        kparams.add("parentJobIdEqual", this.parentJobIdEqual);
        kparams.add("parentJobIdIn", this.parentJobIdIn);
        kparams.add("parentJobIdNotIn", this.parentJobIdNotIn);
        kparams.add("rootJobIdEqual", this.rootJobIdEqual);
        kparams.add("rootJobIdIn", this.rootJobIdIn);
        kparams.add("rootJobIdNotIn", this.rootJobIdNotIn);
        kparams.add("queueTimeGreaterThanOrEqual", this.queueTimeGreaterThanOrEqual);
        kparams.add("queueTimeLessThanOrEqual", this.queueTimeLessThanOrEqual);
        kparams.add("finishTimeGreaterThanOrEqual", this.finishTimeGreaterThanOrEqual);
        kparams.add("finishTimeLessThanOrEqual", this.finishTimeLessThanOrEqual);
        kparams.add("errTypeEqual", this.errTypeEqual);
        kparams.add("errTypeIn", this.errTypeIn);
        kparams.add("errTypeNotIn", this.errTypeNotIn);
        kparams.add("errNumberEqual", this.errNumberEqual);
        kparams.add("errNumberIn", this.errNumberIn);
        kparams.add("errNumberNotIn", this.errNumberNotIn);
        kparams.add("estimatedEffortLessThan", this.estimatedEffortLessThan);
        kparams.add("estimatedEffortGreaterThan", this.estimatedEffortGreaterThan);
        kparams.add("schedulerIdEqual", this.schedulerIdEqual);
        kparams.add("schedulerIdIn", this.schedulerIdIn);
        kparams.add("schedulerIdNotIn", this.schedulerIdNotIn);
        kparams.add("workerIdEqual", this.workerIdEqual);
        kparams.add("workerIdIn", this.workerIdIn);
        kparams.add("workerIdNotIn", this.workerIdNotIn);
        kparams.add("batchIndexEqual", this.batchIndexEqual);
        kparams.add("batchIndexIn", this.batchIndexIn);
        kparams.add("batchIndexNotIn", this.batchIndexNotIn);
        kparams.add("lastSchedulerIdEqual", this.lastSchedulerIdEqual);
        kparams.add("lastSchedulerIdIn", this.lastSchedulerIdIn);
        kparams.add("lastSchedulerIdNotIn", this.lastSchedulerIdNotIn);
        kparams.add("lastWorkerIdEqual", this.lastWorkerIdEqual);
        kparams.add("lastWorkerIdIn", this.lastWorkerIdIn);
        kparams.add("lastWorkerIdNotIn", this.lastWorkerIdNotIn);
        kparams.add("dcEqual", this.dcEqual);
        kparams.add("dcIn", this.dcIn);
        kparams.add("dcNotIn", this.dcNotIn);
        return kparams;
    }

}

