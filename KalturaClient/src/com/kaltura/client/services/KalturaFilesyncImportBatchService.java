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
package com.kaltura.client.services;

import com.kaltura.client.KalturaClient;
import com.kaltura.client.KalturaServiceBase;
import java.util.List;
import org.w3c.dom.Element;
import com.kaltura.client.utils.ParseUtils;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.types.*;
import java.util.ArrayList;
import com.kaltura.client.enums.*;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Fri, 17 Aug 12 06:33:26 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class KalturaFilesyncImportBatchService extends KalturaServiceBase {
    public KalturaFilesyncImportBatchService(KalturaClient client) {
        this.kalturaClient = client;
    }

    public List<KalturaBatchJob> getExclusiveFileSyncImportJobs(KalturaExclusiveLockKey lockKey, int maxExecutionTime, int numberOfJobs) throws KalturaApiException {
        return this.getExclusiveFileSyncImportJobs(lockKey, maxExecutionTime, numberOfJobs, null);
    }

	/**  batch getExclusiveFileSyncImportJob action allows to get a BatchJob of type
	  FILESYNC_IMPORT      */
    public List<KalturaBatchJob> getExclusiveFileSyncImportJobs(KalturaExclusiveLockKey lockKey, int maxExecutionTime, int numberOfJobs, KalturaBatchJobFilter filter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("lockKey", lockKey);
        kparams.add("maxExecutionTime", maxExecutionTime);
        kparams.add("numberOfJobs", numberOfJobs);
        kparams.add("filter", filter);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "getExclusiveFileSyncImportJobs", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseArray(KalturaBatchJob.class, resultXmlElement);
    }

    public KalturaBulkUploadResult addBulkUploadResult(KalturaBulkUploadResult bulkUploadResult) throws KalturaApiException {
        return this.addBulkUploadResult(bulkUploadResult, null);
    }

	/**  batch addBulkUploadResultAction action adds KalturaBulkUploadResult to the DB     */
    public KalturaBulkUploadResult addBulkUploadResult(KalturaBulkUploadResult bulkUploadResult, ArrayList<KalturaBulkUploadPluginData> pluginDataArray) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("bulkUploadResult", bulkUploadResult);
        kparams.add("pluginDataArray", pluginDataArray);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "addBulkUploadResult", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaBulkUploadResult.class, resultXmlElement);
    }

	/**  batch getBulkUploadLastResultAction action returns the last result of the bulk
	  upload     */
    public KalturaBulkUploadResult getBulkUploadLastResult(int bulkUploadJobId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("bulkUploadJobId", bulkUploadJobId);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "getBulkUploadLastResult", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaBulkUploadResult.class, resultXmlElement);
    }

    public int countBulkUploadEntries(int bulkUploadJobId) throws KalturaApiException {
        return this.countBulkUploadEntries(bulkUploadJobId, null);
    }

	/**  Returns total created entries count     */
    public int countBulkUploadEntries(int bulkUploadJobId, KalturaBulkUploadObjectType bulkUploadObjectType) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("bulkUploadJobId", bulkUploadJobId);
        kparams.add("bulkUploadObjectType", bulkUploadObjectType);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "countBulkUploadEntries", kparams);
        if (this.kalturaClient.isMultiRequest())
            return 0;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseInt(resultText);
    }

	/**  batch updateBulkUploadResults action adds KalturaBulkUploadResult to the DB     */
    public int updateBulkUploadResults(int bulkUploadJobId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("bulkUploadJobId", bulkUploadJobId);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "updateBulkUploadResults", kparams);
        if (this.kalturaClient.isMultiRequest())
            return 0;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseInt(resultText);
    }

    public KalturaBatchJob updateExclusiveConvertCollectionJob(int id, KalturaExclusiveLockKey lockKey, KalturaBatchJob job) throws KalturaApiException {
        return this.updateExclusiveConvertCollectionJob(id, lockKey, job, null);
    }

	/**  batch updateExclusiveConvertCollectionJobAction action updates a BatchJob of
	  type CONVERT_PROFILE that was claimed using the getExclusiveConvertJobs     */
    public KalturaBatchJob updateExclusiveConvertCollectionJob(int id, KalturaExclusiveLockKey lockKey, KalturaBatchJob job, ArrayList<KalturaConvertCollectionFlavorData> flavorsData) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("lockKey", lockKey);
        kparams.add("job", job);
        kparams.add("flavorsData", flavorsData);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "updateExclusiveConvertCollectionJob", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaBatchJob.class, resultXmlElement);
    }

	/**  batch updateExclusiveConvertJobSubType action updates the sub type for a
	  BatchJob of type CONVERT that was claimed using the getExclusiveConvertJobs     */
    public KalturaBatchJob updateExclusiveConvertJobSubType(int id, KalturaExclusiveLockKey lockKey, int subType) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("lockKey", lockKey);
        kparams.add("subType", subType);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "updateExclusiveConvertJobSubType", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaBatchJob.class, resultXmlElement);
    }

	/**  batch addMediaInfoAction action saves a media info object     */
    public KalturaMediaInfo addMediaInfo(KalturaMediaInfo mediaInfo) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("mediaInfo", mediaInfo);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "addMediaInfo", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaMediaInfo.class, resultXmlElement);
    }

    public KalturaBatchGetExclusiveNotificationJobsResponse getExclusiveNotificationJobs(KalturaExclusiveLockKey lockKey, int maxExecutionTime, int numberOfJobs) throws KalturaApiException {
        return this.getExclusiveNotificationJobs(lockKey, maxExecutionTime, numberOfJobs, null);
    }

	/**  batch getExclusiveNotificationJob action allows to get a BatchJob of type
	  NOTIFICATION      */
    public KalturaBatchGetExclusiveNotificationJobsResponse getExclusiveNotificationJobs(KalturaExclusiveLockKey lockKey, int maxExecutionTime, int numberOfJobs, KalturaBatchJobFilter filter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("lockKey", lockKey);
        kparams.add("maxExecutionTime", maxExecutionTime);
        kparams.add("numberOfJobs", numberOfJobs);
        kparams.add("filter", filter);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "getExclusiveNotificationJobs", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaBatchGetExclusiveNotificationJobsResponse.class, resultXmlElement);
    }

	/**  batch resetJobExecutionAttempts action resets the execution attempts of the job 
	      */
    public void resetJobExecutionAttempts(int id, KalturaExclusiveLockKey lockKey, KalturaBatchJobType jobType) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("lockKey", lockKey);
        kparams.add("jobType", jobType);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "resetJobExecutionAttempts", kparams);
        if (this.kalturaClient.isMultiRequest())
            return ;
        this.kalturaClient.doQueue();
    }

    public KalturaFreeJobResponse freeExclusiveJob(int id, KalturaExclusiveLockKey lockKey, KalturaBatchJobType jobType) throws KalturaApiException {
        return this.freeExclusiveJob(id, lockKey, jobType, false);
    }

	/**  batch freeExclusiveJobAction action allows to get a generic BatchJob      */
    public KalturaFreeJobResponse freeExclusiveJob(int id, KalturaExclusiveLockKey lockKey, KalturaBatchJobType jobType, boolean resetExecutionAttempts) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("lockKey", lockKey);
        kparams.add("jobType", jobType);
        kparams.add("resetExecutionAttempts", resetExecutionAttempts);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "freeExclusiveJob", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaFreeJobResponse.class, resultXmlElement);
    }

	/**  batch getQueueSize action get the queue size for job type      */
    public int getQueueSize(KalturaWorkerQueueFilter workerQueueFilter) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("workerQueueFilter", workerQueueFilter);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "getQueueSize", kparams);
        if (this.kalturaClient.isMultiRequest())
            return 0;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseInt(resultText);
    }

    public List<KalturaBatchJob> getExclusiveJobs(KalturaExclusiveLockKey lockKey, int maxExecutionTime, int numberOfJobs) throws KalturaApiException {
        return this.getExclusiveJobs(lockKey, maxExecutionTime, numberOfJobs, null);
    }

    public List<KalturaBatchJob> getExclusiveJobs(KalturaExclusiveLockKey lockKey, int maxExecutionTime, int numberOfJobs, KalturaBatchJobFilter filter) throws KalturaApiException {
        return this.getExclusiveJobs(lockKey, maxExecutionTime, numberOfJobs, filter, null);
    }

	/**  batch getExclusiveJobsAction action allows to get a BatchJob      */
    public List<KalturaBatchJob> getExclusiveJobs(KalturaExclusiveLockKey lockKey, int maxExecutionTime, int numberOfJobs, KalturaBatchJobFilter filter, KalturaBatchJobType jobType) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("lockKey", lockKey);
        kparams.add("maxExecutionTime", maxExecutionTime);
        kparams.add("numberOfJobs", numberOfJobs);
        kparams.add("filter", filter);
        kparams.add("jobType", jobType);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "getExclusiveJobs", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseArray(KalturaBatchJob.class, resultXmlElement);
    }

    public List<KalturaBatchJob> getExclusiveAlmostDone(KalturaExclusiveLockKey lockKey, int maxExecutionTime, int numberOfJobs) throws KalturaApiException {
        return this.getExclusiveAlmostDone(lockKey, maxExecutionTime, numberOfJobs, null);
    }

    public List<KalturaBatchJob> getExclusiveAlmostDone(KalturaExclusiveLockKey lockKey, int maxExecutionTime, int numberOfJobs, KalturaBatchJobFilter filter) throws KalturaApiException {
        return this.getExclusiveAlmostDone(lockKey, maxExecutionTime, numberOfJobs, filter, null);
    }

	/**  batch getExclusiveAlmostDone action allows to get a BatchJob that wait for
	  remote closure      */
    public List<KalturaBatchJob> getExclusiveAlmostDone(KalturaExclusiveLockKey lockKey, int maxExecutionTime, int numberOfJobs, KalturaBatchJobFilter filter, KalturaBatchJobType jobType) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("lockKey", lockKey);
        kparams.add("maxExecutionTime", maxExecutionTime);
        kparams.add("numberOfJobs", numberOfJobs);
        kparams.add("filter", filter);
        kparams.add("jobType", jobType);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "getExclusiveAlmostDone", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseArray(KalturaBatchJob.class, resultXmlElement);
    }

	/**  batch updateExclusiveJobAction action updates a BatchJob of extended type that
	  was claimed using the getExclusiveJobs     */
    public KalturaBatchJob updateExclusiveJob(int id, KalturaExclusiveLockKey lockKey, KalturaBatchJob job) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("lockKey", lockKey);
        kparams.add("job", job);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "updateExclusiveJob", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaBatchJob.class, resultXmlElement);
    }

	/**  batch cleanExclusiveJobs action mark as fatal error all expired jobs        */
    public int cleanExclusiveJobs() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "cleanExclusiveJobs", kparams);
        if (this.kalturaClient.isMultiRequest())
            return 0;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseInt(resultText);
    }

	/**  Add the data to the flavor asset conversion log, creates it if doesn't exists     */
    public void logConversion(String flavorAssetId, String data) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("flavorAssetId", flavorAssetId);
        kparams.add("data", data);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "logConversion", kparams);
        if (this.kalturaClient.isMultiRequest())
            return ;
        this.kalturaClient.doQueue();
    }

	/**  batch checkFileExists action check if the file exists     */
    public KalturaFileExistsResponse checkFileExists(String localPath, float size) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("localPath", localPath);
        kparams.add("size", size);
        this.kalturaClient.queueServiceCall("multicenters_filesyncimportbatch", "checkFileExists", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaFileExistsResponse.class, resultXmlElement);
    }
}
