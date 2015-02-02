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
import com.kaltura.client.types.*;
import com.kaltura.client.enums.*;
import org.w3c.dom.Element;
import com.kaltura.client.utils.ParseUtils;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import com.kaltura.client.KalturaFiles;
import com.kaltura.client.KalturaFile;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Tue, 09 Apr 13 06:52:58 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Live Stream service lets you manage live stream channels    */
public class KalturaLiveStreamService extends KalturaServiceBase {
    public KalturaLiveStreamService(KalturaClient client) {
        this.kalturaClient = client;
    }

    public KalturaLiveStreamAdminEntry add(KalturaLiveStreamAdminEntry liveStreamEntry) throws KalturaApiException {
        return this.add(liveStreamEntry, null);
    }

	/**  Adds new live stream entry.   The entry will be queued for provision.     */
    public KalturaLiveStreamAdminEntry add(KalturaLiveStreamAdminEntry liveStreamEntry, KalturaSourceType sourceType) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("liveStreamEntry", liveStreamEntry);
        kparams.add("sourceType", sourceType);
        this.kalturaClient.queueServiceCall("livestream", "add", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaLiveStreamAdminEntry.class, resultXmlElement);
    }

    public KalturaLiveStreamEntry get(String entryId) throws KalturaApiException {
        return this.get(entryId, -1);
    }

	/**  Get live stream entry by ID.     */
    public KalturaLiveStreamEntry get(String entryId, int version) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("entryId", entryId);
        kparams.add("version", version);
        this.kalturaClient.queueServiceCall("livestream", "get", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaLiveStreamEntry.class, resultXmlElement);
    }

	/**  Update live stream entry. Only the properties that were set will be updated.     */
    public KalturaLiveStreamAdminEntry update(String entryId, KalturaLiveStreamAdminEntry liveStreamEntry) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("entryId", entryId);
        kparams.add("liveStreamEntry", liveStreamEntry);
        this.kalturaClient.queueServiceCall("livestream", "update", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaLiveStreamAdminEntry.class, resultXmlElement);
    }

	/**  Delete a live stream entry.     */
    public void delete(String entryId) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("entryId", entryId);
        this.kalturaClient.queueServiceCall("livestream", "delete", kparams);
        if (this.kalturaClient.isMultiRequest())
            return ;
        this.kalturaClient.doQueue();
    }

    public KalturaLiveStreamListResponse list() throws KalturaApiException {
        return this.list(null);
    }

    public KalturaLiveStreamListResponse list(KalturaLiveStreamEntryFilter filter) throws KalturaApiException {
        return this.list(filter, null);
    }

	/**  List live stream entries by filter with paging support.     */
    public KalturaLiveStreamListResponse list(KalturaLiveStreamEntryFilter filter, KalturaFilterPager pager) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("filter", filter);
        kparams.add("pager", pager);
        this.kalturaClient.queueServiceCall("livestream", "list", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaLiveStreamListResponse.class, resultXmlElement);
    }

    public KalturaLiveStreamEntry updateOfflineThumbnailJpeg(String entryId, File fileData) throws KalturaApiException {
        return this.updateOfflineThumbnailJpeg(entryId, new KalturaFile(fileData));
    }

    public KalturaLiveStreamEntry updateOfflineThumbnailJpeg(String entryId, InputStream fileData, String fileDataName, long fileDataSize) throws KalturaApiException {
        return this.updateOfflineThumbnailJpeg(entryId, new KalturaFile(fileData, fileDataName, fileDataSize));
    }

    public KalturaLiveStreamEntry updateOfflineThumbnailJpeg(String entryId, FileInputStream fileData, String fileDataName) throws KalturaApiException {
        return this.updateOfflineThumbnailJpeg(entryId, new KalturaFile(fileData, fileDataName));
    }

	/**  Update live stream entry thumbnail using a raw jpeg file     */
    public KalturaLiveStreamEntry updateOfflineThumbnailJpeg(String entryId, KalturaFile fileData) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("entryId", entryId);
        KalturaFiles kfiles = new KalturaFiles();
        kfiles.add("fileData", fileData);
        this.kalturaClient.queueServiceCall("livestream", "updateOfflineThumbnailJpeg", kparams, kfiles);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaLiveStreamEntry.class, resultXmlElement);
    }

	/**  Update entry thumbnail using url     */
    public KalturaLiveStreamEntry updateOfflineThumbnailFromUrl(String entryId, String url) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("entryId", entryId);
        kparams.add("url", url);
        this.kalturaClient.queueServiceCall("livestream", "updateOfflineThumbnailFromUrl", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaLiveStreamEntry.class, resultXmlElement);
    }

	/**  New action delivering the status of a live stream (on-air/offline) if it is
	  possible     */
    public boolean isLive(String id, KalturaPlaybackProtocol protocol) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("id", id);
        kparams.add("protocol", protocol);
        this.kalturaClient.queueServiceCall("livestream", "isLive", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }
}
