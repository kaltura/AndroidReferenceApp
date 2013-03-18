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
 * @date Fri, 17 Aug 12 06:33:26 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  System user service    */
public class KalturaFileSyncService extends KalturaServiceBase {
    public KalturaFileSyncService(KalturaClient client) {
        this.kalturaClient = client;
    }

    public KalturaFileSyncListResponse list() throws KalturaApiException {
        return this.list(null);
    }

    public KalturaFileSyncListResponse list(KalturaFileSyncFilter filter) throws KalturaApiException {
        return this.list(filter, null);
    }

	/**  List file syce objects by filter and pager     */
    public KalturaFileSyncListResponse list(KalturaFileSyncFilter filter, KalturaFilterPager pager) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("filter", filter);
        kparams.add("pager", pager);
        this.kalturaClient.queueServiceCall("filesync_filesync", "list", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaFileSyncListResponse.class, resultXmlElement);
    }

    public KalturaFileSync sync(int fileSyncId, File fileData) throws KalturaApiException {
        return this.sync(fileSyncId, new KalturaFile(fileData));
    }

    public KalturaFileSync sync(int fileSyncId, InputStream fileData, String fileDataName, long fileDataSize) throws KalturaApiException {
        return this.sync(fileSyncId, new KalturaFile(fileData, fileDataName, fileDataSize));
    }

    public KalturaFileSync sync(int fileSyncId, FileInputStream fileData, String fileDataName) throws KalturaApiException {
        return this.sync(fileSyncId, new KalturaFile(fileData, fileDataName));
    }

    public KalturaFileSync sync(int fileSyncId, KalturaFile fileData) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("fileSyncId", fileSyncId);
        KalturaFiles kfiles = new KalturaFiles();
        kfiles.add("fileData", fileData);
        this.kalturaClient.queueServiceCall("filesync_filesync", "sync", kparams, kfiles);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaFileSync.class, resultXmlElement);
    }
}
